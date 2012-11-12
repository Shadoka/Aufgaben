package state;

import util.BufferEntry;
import util.OutputBuffer;

public interface State {
	
	public boolean accept(IsNotCachedStateVisitor v);
	public void add(BufferEntry entry);
	public BufferEntry get(int pointer);
	public int getAsIntIfPossible(int pointer);
	public OutputBuffer getOutput();
}
