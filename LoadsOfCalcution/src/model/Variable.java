package model;

import util.BufferEntry;

/**
 * Represents a mathematical variable. It may have multiple values, but not necessary.
 * @author Shadoka
 *
 */
public class Variable extends AbstractExpression {
	
	private Variable() {
		super();
	}
	
	public static Variable create() {
		return new Variable();
	}
	
	@Override
	public boolean contains(Expression e) {
		return this.equals(e);
	}
	
	/**
	 * This method adds a entry to the outputbuffer.
	 * Because this class represents just a placeholder it can be done without calculation.
	 * @param value : BufferEntry
	 */
	public void add(BufferEntry value) {
		this.getOutput().put(value);
	}

	@Override
	public BufferEntry calculate() {
		return this.getOutput().get();
	}

	@Override
	public void run() {
		this.setAlreadyRunned(true);
	}

}
