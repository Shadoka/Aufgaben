package util;

/**
 * Class signalizes the process to stop the calculation.
 * @author Admin
 *
 */
public class StopCommand implements BufferEntry{

	private StopCommand() {
		
	}
	
	public static StopCommand create() {
		return new StopCommand();
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
