package model;

import state.Cached;
import util.BufferEntry;
import util.ConcreteBufferEntryStopVisitor;

/**
 * Represents a mathematical variable. It may have multiple values, but not necessary.
 * @author Shadoka
 *
 */
public class Variable extends AbstractExpression {
	
	private Variable() {
		super();
		this.setAlreadyRunned(true);
		this.setState(Cached.create(this, this.getState().getOutput()));
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
		this.getState().add(value);
	}
	
	@Override
	public BufferEntry get(int pointer) {
		return this.getState().get(pointer);
	}

	@Override
	public BufferEntry calculate() {
		BufferEntry result = this.get(this.getOutputPointer());
		if (result.accept(ConcreteBufferEntryStopVisitor.create())) {
			this.setOutputPointer(0);
		} else {
			this.setOutputPointer(this.getOutputPointer() + 1);
		}
		return result;
	}

	@Override
	public void run() {
		this.setAlreadyRunned(true);
	}

}
