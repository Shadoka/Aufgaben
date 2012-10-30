package model;

import util.IntegerWrapper;

/**
 * Represents the mathematical multiplication.
 * @author Shadoka
 *
 */
public class Multiplication extends AbstractComposite {

	private Multiplication(AbstractExpression left, AbstractExpression right) throws HierarchyException {
		super(left, right);
	}
	
	public static Multiplication create(AbstractExpression left, AbstractExpression right) throws HierarchyException {
		return new Multiplication(left, right);
	}

	@Override
	public IntegerWrapper calculateTransitively(IntegerWrapper left, IntegerWrapper right) {
		return IntegerWrapper.create(left.getValue() * right.getValue());
	}

}
