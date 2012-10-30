package util;

/**
 * Implementation of "BufferEntryCalculationVisitor".
 * @author Admin
 *
 */
public class ConcreteBufferEntryVisitor implements BufferEntryCalculationVisitor {

	private ConcreteBufferEntryVisitor() {
		
	}
	
	public static ConcreteBufferEntryVisitor create() {
		return new ConcreteBufferEntryVisitor();
	}

	@Override
	public BufferEntry visit(StopCommand stop) {
		System.out.println("Ich darf eigentlich nie erreicht werden!");
		return null;
	}

	@Override
	public BufferEntry visit(IntegerWrapper value) {
		return value;
	}
}
