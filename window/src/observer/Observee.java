package observer;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public abstract class Observee {

	private final Collection<Observer> observers;

	public Observee() {
		this.observers = new Vector<>();
	}

	public void register(Observer o) {
		this.getObservers().add(o);
	}

	public void deregister(Observer o) {
		this.getObservers().remove(o);
	}

	public void notifyObservers() {
		Iterator<Observer> i = this.getObservers().iterator();
		while (i.hasNext()) {
			Observer current = i.next();
			current.update();
		}
	}

	public Collection<Observer> getObservers() {
		return observers;
	}
}
