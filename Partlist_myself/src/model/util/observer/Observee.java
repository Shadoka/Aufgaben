package model.util.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import model.util.event.Event;

public abstract class Observee {

	private Collection<Observer> observers;
	
	public Observee() {
		this.observers = new ArrayList<Observer>();
	}

	public void register(Observer o) {
		this.getObservers().add(o);
	}
	
	public void deregister(Observer o) {
		this.getObservers().remove(o);
	}
	
	public void notifyObservers(Event e) {
		Iterator<Observer> i = this.getObservers().iterator();
		
		while(i.hasNext()) {
			Observer current = i.next();
			current.update(e);
		}
	}
	
	public Collection<Observer> getObservers() {
		return observers;
	}

	public void setObservers(Collection<Observer> observers) {
		this.observers = observers;
	}
}
