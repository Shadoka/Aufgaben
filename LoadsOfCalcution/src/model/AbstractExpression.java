package model;

import state.NotCompletelyCached;
import state.State;
import util.BufferEntry;

/**
 * Class represents the abstract superclass of all expressions.
 * Contains some additional fields all expressions have in common.
 * @author Shadoka
 *
 */
public abstract class AbstractExpression implements Expression, Runnable{

	protected State state;
	private boolean alreadyRunned = false;
	private int outputPointer = 0;
	
	protected AbstractExpression() {
		this.state = NotCompletelyCached.create(this);
		this.start();
	}
	
	/**
	 * This method starts a new Thread containing itself as Runnable.
	 */
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public boolean isAlreadyRunned() {
		return alreadyRunned;
	}

	public void setAlreadyRunned(boolean alreadyRunned) {
		this.alreadyRunned = alreadyRunned;
	}
	
	public abstract BufferEntry get(int pointer);

	public int getOutputPointer() {
		return outputPointer;
	}

	public void setOutputPointer(int outputPointer) {
		this.outputPointer = outputPointer;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
