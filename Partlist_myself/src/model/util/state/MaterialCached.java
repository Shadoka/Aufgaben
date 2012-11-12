package model.util.state;

import model.main.ComponentCommon;
import model.main.Materialmap;
import model.util.event.Event;
import model.util.event.PriceChangedEvent;
import model.util.event.StructureChangedEvent;
import model.util.visitor.EventVisitor;
import model.util.visitor.MaterialPriceStateVisitor;
import model.util.visitor.MaterialStateVisitor;

public class MaterialCached extends Cached{
	
	private MaterialCached(ComponentCommon master, Materialmap materials) {
		super(master, materials);
	}
	
	public static MaterialCached createMaterialCached(ComponentCommon master, Materialmap materials) {
		return new MaterialCached(master, materials);
	}

	@Override
	public void handleNotification(Event e) {
		e.accept(new EventVisitor() {

			@Override
			public void visit(PriceChangedEvent e) {
				// Nothing left to do here.
				
			}

			@Override
			public void visit(StructureChangedEvent e) {
				MaterialCached.this.getMaster().setState(NotCached.createNotCached(MaterialCached.this.getMaster()));
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
