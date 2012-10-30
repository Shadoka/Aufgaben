package util;

/**
 * Wrapperclass for the wrapperclass of "int"...lol
 * Needed to have a "int" implementing "BufferEntry"
 * @author Admin
 *
 */
public class IntegerWrapper implements BufferEntry{

	private final Integer value;
	
	private IntegerWrapper(Integer value) {
		this.value = value;
	}
	
	public static IntegerWrapper create(Integer value) {
		return new IntegerWrapper(value);
	}
	
	public Integer getValue() {
		return this.value;
	}

	@Override
	public BufferEntry accept(BufferEntryCalculationVisitor v) {
		return v.visit(this);
	}

	@Override
	public boolean accept(BufferEntryStopVisitor v) {
		return v.visit(this);
	}
}
