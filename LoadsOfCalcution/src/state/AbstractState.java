package state;

import model.Expression;
import util.BufferEntry;
import util.IntegerWrapper;
import util.OutputBuffer;

public abstract class AbstractState implements State{

	protected OutputBuffer output;
	protected Expression master;
	
	protected AbstractState(Expression master) {
		this.output = OutputBuffer.create();
		this.master = master;
	}
	
	public BufferEntry get(int pointer) {
		return this.getOutput().get(pointer);
	}
	
	public int getAsIntIfPossible(int pointer) {
		BufferEntry result = this.get(pointer);
		if (result instanceof IntegerWrapper) {
			return ((IntegerWrapper) result).getValue(); 
		} else {
			return -1;
		}
	}
	
	public OutputBuffer getOutput() {
		return this.output;
	}

	public Expression getMaster() {
		return master;
	}

	public void setMaster(Expression master) {
		this.master = master;
	}
}
