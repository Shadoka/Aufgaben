package model.util.event;

import model.util.visitor.EventVisitor;

public class StructureChangedEvent extends AbstractEvent{

	private StructureChangedEvent() {
		
	}
	
	public static StructureChangedEvent createStructureChangedEvent() {
		return new StructureChangedEvent();
	}

	@Override
	public void accept(EventVisitor v) {
		v.visit(this);
	}
}
