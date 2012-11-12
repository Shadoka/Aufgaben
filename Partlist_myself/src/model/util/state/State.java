package model.util.state;

import model.main.Materialmap;
import model.util.event.Event;
import model.util.visitor.MaterialPriceStateVisitor;
import model.util.visitor.MaterialStateVisitor;

public interface State {

	public void handleNotification(Event e);
	public Materialmap accept(MaterialStateVisitor v);
	public int accept(MaterialPriceStateVisitor v);
}
