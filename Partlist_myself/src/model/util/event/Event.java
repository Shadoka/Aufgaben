package model.util.event;

import model.util.visitor.EventVisitor;

public interface Event {

	public void accept(EventVisitor v);
}
