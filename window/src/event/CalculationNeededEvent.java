package event;

public class CalculationNeededEvent implements Event {

	private CalculationNeededEvent() {

	}

	public static CalculationNeededEvent create() {
		return new CalculationNeededEvent();
	}

	@Override
	public void accept(EventVisitor v) {
		v.visit(this);
	}
}
