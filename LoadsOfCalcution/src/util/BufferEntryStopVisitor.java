package util;

/**
 * Visitor for BufferEntries, needed to notice when to stop.
 * @author Admin
 *
 */
public interface BufferEntryStopVisitor {

	/**
	 * If BufferEntry is a StopCommand --> true.
	 * @param stop
	 * @return
	 */
	public boolean visit(StopCommand stop);
	
	/**
	 * If BufferEntry is a IntegerWrapper --> false.
	 * @param value
	 * @return
	 */
	public boolean visit(IntegerWrapper value);
}
