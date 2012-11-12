package util;

/**
 * Visitor for BufferEntries, needed at the calculation of expressions.
 * @author Admin
 *
 */
public interface BufferEntryCalculationVisitor {

	/**
	 * Can never be reached, cause of "Invariante" --> calculate() in AbstractComposite.
	 * @param stop
	 * @return
	 */
	public BufferEntry visit(StopCommand stop);
	
	/**
	 * Returns a BufferEntry.
	 * @param value
	 * @return
	 */
	public BufferEntry visit(IntegerWrapper value);
}
