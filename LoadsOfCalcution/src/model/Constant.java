package model;

import state.Cached;
import util.BufferEntry;
import util.IntegerWrapper;

/**
 * This class represents a constant mathematical value.
 * @author Shadoka
 *
 */
public class Constant extends AbstractExpression{

	private final Integer value;
	
	private Constant(Integer value) {
		this.value = value;
		this.setAlreadyRunned(true);
		this.setState(Cached.create(this, this.getState().getOutput()));
	}
	
	public static Constant create(Integer value) {
		return new Constant(value);
	}
	
	public Integer getValue() {
		return this.value;
	}

	@Override
	public boolean contains(Expression e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BufferEntry calculate() {
		return IntegerWrapper.create(this.getValue());
	}

	@Override
	public void run() {
		this.setAlreadyRunned(true);
	}

	@Override
	public BufferEntry get(int pointer) {
		return IntegerWrapper.create(this.getValue());
	}
	
}
