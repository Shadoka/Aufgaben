package util;

/**
 * Implementation of "BufferEntryStopVisitor".
 * @author Admin
 *
 */
public class ConcreteBufferEntryStopVisitor implements BufferEntryStopVisitor{

	private ConcreteBufferEntryStopVisitor() {
		
	}
	
	public static ConcreteBufferEntryStopVisitor create() {
		return new ConcreteBufferEntryStopVisitor();
	}
	
	@Override
	public boolean visit(StopCommand stop) {
		return true;
	}

	@Override
	public boolean visit(IntegerWrapper value) {
		return false;
	}
}
