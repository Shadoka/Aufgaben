package model.util.state;

import model.main.ComponentCommon;
import model.main.Materialmap;
import model.util.event.Event;
import model.util.event.PriceChangedEvent;
import model.util.event.StructureChangedEvent;
import model.util.visitor.EventVisitor;
import model.util.visitor.MaterialPriceStateVisitor;
import model.util.visitor.MaterialStateVisitor;

public class NotCached extends AbstractState{

	private NotCached(ComponentCommon master) {
		super(master);
	}
	
	public static NotCached createNotCached(ComponentCommon master) {
		return new NotCached(master);
	}

	public void handleNotification(Event e) {
		e.accept(new EventVisitor() {

			@Override
			public void visit(PriceChangedEvent e) {
				// Nothing left to do here.
			}

			@Override
			public void visit(StructureChangedEvent e) {
				// Nothing left to do here.
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
