package util;

/**
 * A BufferEntry is a element, which can be hold in a "Buffer".
 * @author Admin
 *
 */
public interface BufferEntry {

	/**
	 * Method to get BufferEntry-value needed to calculate with.
	 * @param v
	 * @return
	 */
	public BufferEntry accept(BufferEntryCalculationVisitor v);
	
	/**
	 * Method to notice when to stop the thread.
	 * @param v
	 * @return
	 */
	public boolean accept(BufferEntryStopVisitor v);
}
