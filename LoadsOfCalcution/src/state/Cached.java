package state;

import model.Expression;
import util.BufferEntry;
import util.OutputBuffer;


public class Cached extends AbstractState{
	
	private Cached(Expression master, OutputBuffer output) {
		super(master);
		this.output = output;
	}
	
	public static Cached create(Expression master, OutputBuffer output) {
		return new Cached(master, output);
	}

	@Override
	public boolean accept(IsNotCachedStateVisitor v) {
		return v.visit(this);
	}

	@Override
	public void add(BufferEntry entry) {
		this.getOutput().add(entry);
	}
	
}
