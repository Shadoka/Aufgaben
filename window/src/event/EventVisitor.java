package event;

public interface EventVisitor {

	public void visit(CalculationNeededEvent e);
}
