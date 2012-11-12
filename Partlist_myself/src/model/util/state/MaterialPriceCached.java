package model.util.state;

import model.main.ComponentCommon;
import model.main.Materialmap;
import model.util.event.Event;
import model.util.event.PriceChangedEvent;
import model.util.event.StructureChangedEvent;
import model.util.visitor.EventVisitor;
import model.util.visitor.MaterialPriceStateVisitor;
import model.util.visitor.MaterialStateVisitor;

public class MaterialPriceCached extends Cached{

	private int overallPrice;
	
	private MaterialPriceCached(ComponentCommon master, Materialmap materials, int overallPrice) {
		super(master, materials);
		this.overallPrice = overallPrice;
	}
	
	public static MaterialPriceCached createMaterialPriceCached(ComponentCommon master, Materialmap materials, int overallPrice) {
		return new MaterialPriceCached(master, materials, overallPrice);
	}

	public int getOverallPrice() {
		return overallPrice;
	}

	public void setOverallPrice(int overallPrice) {
		this.overallPrice = overallPrice;
	}

	@Override
	public void handleNotification(Event e) {
		e.accept(new EventVisitor() {

			@Override
			public void visit(PriceChangedEvent e) {
				MaterialPriceCached.this.getMaster().setState(NotCached.createNotCached(MaterialPriceCached.this.getMaster()));
			}

			@Override
			public void visit(StructureChangedEvent e) {
				MaterialPriceCached.this.getMaster().setState(NotCached.createNotCached(MaterialPriceCached.this.getMaster()));
			}
			
		});
	}

	@Override
	public Materialmap accept(MaterialStateVisitor v) {
		return v.visit(this);
	}

	@Override
	public int accept(MaterialPriceStateVisitor v) {
		return v.visit(this);
	}
}
