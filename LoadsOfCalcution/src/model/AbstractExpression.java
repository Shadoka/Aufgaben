package model;

import util.Buffer;
import util.BufferEntry;

/**
 * Class represents the abstract superclass of all expressions.
 * Contains some additional fields all expressions have in common.
 * @author Shadoka
 *
 */
public abstract class AbstractExpression implements Expression, Runnable{

	protected Buffer<BufferEntry> output;
	private boolean alreadyRunned = false;
	
	protected AbstractExpression() {
		this.output = new Buffer<BufferEntry>();
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
	
	/**
	 * Returns the outputbuffer of the expression.
	 * @return : Buffer
	 */
	public Buffer<BufferEntry> getOutput() {
		return this.output;
	}

	public void setOutput(Buffer<BufferEntry> output) {
		this.output = output;
	}
}
