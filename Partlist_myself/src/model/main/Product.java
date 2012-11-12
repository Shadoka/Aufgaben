package model.main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import model.main.process.MaterialmapProcess;
import model.util.event.Event;
import model.util.event.StructureChangedEvent;
import model.util.observer.Observer;
import model.util.state.AbstractState;
import model.util.state.MaterialCached;
import model.util.state.MaterialPriceCached;
import model.util.state.NotCached;
import model.util.visitor.MaterialPriceStateVisitor;
import model.util.visitor.MaterialStateVisitor;

public class Product extends ComponentCommon implements Observer {

	private static final String CycleMessage = "Zyklen sind in der Aufbaustruktur nicht erlaubt!";

	public static Product create(String name, int price) {
		return new Product(name, new HashMap<String, QuantifiedComponent>(),
				price);
	}

	private final HashMap<String, QuantifiedComponent> components;

	private Product(String name,
			HashMap<String, QuantifiedComponent> components, int price) {
		super(name, price);
		this.components = components;
	}

	public void addPart(ComponentCommon part, int amount) throws Exception {
		if (part.contains(this))
			throw new Exception(CycleMessage);
		final String partName = part.getName();
		if (this.getComponents().containsKey(partName)) {
			final QuantifiedComponent oldQuantification = this.getComponents()
					.get(partName);
			oldQuantification.setQuantity(oldQuantification.getQuantity()
					+ amount);
			this.notifyObservers(StructureChangedEvent
					.createStructureChangedEvent());
		} else {
			this.getComponents()
					.put(partName,
							QuantifiedComponent.createQuantifiedComponent(
									amount, part));
			part.register(this);
			StructureChangedEvent e = StructureChangedEvent
					.createStructureChangedEvent();
			this.getState().handleNotification(e);
			this.notifyObservers(e);
		}
	}

	private HashMap<String, QuantifiedComponent> getComponents() {
		return this.components;
	}

	public boolean contains(Component component) {
		if (this.equals(component)) {
			return true;
		}
		Iterator<QuantifiedComponent> i = this.getComponents().values()
				.iterator();
		while (i.hasNext()) {
			QuantifiedComponent current = i.next();
			if (current.contains(component)) {
				return true;
			}
		}
		return false;
	}

	public Vector<QuantifiedComponent> getDirectParts() {
		return new Vector<QuantifiedComponent>(this.getComponents().values());
	}

	public int getNumberOfMaterials() {
		int result = 0;
		Iterator<QuantifiedComponent> i = this.getComponents().values()
				.iterator();
		while (i.hasNext()) {
			QuantifiedComponent current = i.next();
			result = result + current.getNumberOfMaterials();
		}
		return result;
	}

	public Materialmap getMaterialList() {
		return this.handleStateForMaterial(this.getState());
	}

	private Materialmap calculateMaterialmap() {
		this.startMaterialThreads();
		Materialmap result = Materialmap.createMaterialmap();
		Materialmap subcomponents = Materialmap.createMaterialmap(this
				.getComponents().values());
		Iterator<QuantifiedComponent> i = subcomponents.values().iterator();

		while (i.hasNext()) {
			QuantifiedComponent current = i.next();

			result = result.merge(current.getComponent().getMaterialList(),
					current.getQuantity());
		}

		return result;
	}

	private void startMaterialThreads() {
		Iterator<QuantifiedComponent> i = this.getComponents().values()
				.iterator();
		while (i.hasNext()) {
			QuantifiedComponent current = i.next();
			MaterialmapProcess process = MaterialmapProcess.create(current
					.getComponent());
			process.run();
		}
	}

	public int getOverallPrice() {
		return this.handleStateForPrice(this.getState());
	}

	private int calculateOverallPrice() {
		int result = this.getPrice();

		Iterator<QuantifiedComponent> i = this.getDirectParts().iterator();
		while (i.hasNext()) {
			QuantifiedComponent current = i.next();
			result += current.getComponent().getOverallPrice()
					* current.getQuantity();
		}

		return result;
	}

	@Override
	public void update(Event e) {
		this.getState().handleNotification(e);
	}

	public Materialmap handleStateForMaterial(AbstractState state) {
		return state.accept(new MaterialStateVisitor() {

			@Override
			public Materialmap visit(NotCached state) {
				Materialmap map = Product.this.calculateMaterialmap();
				Product.this.setState(MaterialCached.createMaterialCached(
						Product.this, map));
				return map;
			}

			@Override
			public Materialmap visit(MaterialCached state) {
				return state.getMaterials();
			}

			@Override
			public Materialmap visit(MaterialPriceCached state) {
				return state.getMaterials();
			}

		});
	}

	public int handleStateForPrice(AbstractState state) {
		return state.accept(new MaterialPriceStateVisitor() {

			@Override
			public int visit(NotCached state) {
				int price = Product.this.calculateOverallPrice();
				// Materialmap map = Product.this.calculateMaterialmap();
				// Product.this.setState(MaterialPriceCached.createMaterialPriceCached(Product.this,
				// map, price));
				return price;
			}

			@Override
			public int visit(MaterialCached state) {
				int price = Product.this.calculateOverallPrice();
				Product.this.setState(MaterialPriceCached
						.createMaterialPriceCached(Product.this,
								Product.this.getMaterialList(), price));
				return price;
			}

			@Override
			public int visit(MaterialPriceCached state) {
				return state.getOverallPrice();
			}

		});
	}
}
