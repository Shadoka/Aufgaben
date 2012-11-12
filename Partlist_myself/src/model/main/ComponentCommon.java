package model.main;

import model.util.event.PriceChangedEvent;
import model.util.observer.Observee;
import model.util.state.AbstractState;
import model.util.state.NotCached;


public abstract class ComponentCommon extends Observee implements Component {

	private final String name;
	private int price;
	private AbstractState state;
	
	protected ComponentCommon(String name, int price) {
		this.name = name;
		this.price = price;
		this.state = NotCached.createNotCached(this);
	}

	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int newPrice) {
		this.price = newPrice;
		PriceChangedEvent e = PriceChangedEvent.createPriceChangedEvent();
		this.getState().handleNotification(e);
		this.notifyObservers(e);
	}
	
	public String getName() {
		return name;
	}
	public String toString(){
		return this.getName();
	}

	public AbstractState getState() {
		return state;
	}

	public void setState(AbstractState state) {
		this.state = state;
	}

}
