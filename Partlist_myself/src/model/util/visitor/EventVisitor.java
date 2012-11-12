package model.util.visitor;

import model.util.event.PriceChangedEvent;
import model.util.event.StructureChangedEvent;

public interface EventVisitor {

	public void visit(PriceChangedEvent e);
	public void visit(StructureChangedEvent e);
}
