package model.util.event;

import model.util.visitor.EventVisitor;

public class PriceChangedEvent extends AbstractEvent{

	private PriceChangedEvent() {
		
	}
	
	public static PriceChangedEvent createPriceChangedEvent() {
		return new PriceChangedEvent();
	}
	
	@Override
	public void accept(EventVisitor v) {
		v.visit(this);
	}

}
