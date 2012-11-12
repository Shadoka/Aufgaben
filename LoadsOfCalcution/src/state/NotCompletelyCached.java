package state;

import model.Expression;
import util.BufferEntry;

public class NotCompletelyCached extends AbstractState{

	private NotCompletelyCached(Expression master) {
		super(master);
	}
	
	public static NotCompletelyCached create(Expression master) {
		return new NotCompletelyCached(master);
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
