package model;

import util.IntegerWrapper;

/**
 * Represents the mathematical subtraction.
 * @author Shadoka
 *
 */
public class Subtraction extends AbstractComposite {

	private Subtraction(AbstractExpression left, AbstractExpression right) throws HierarchyException {
		super(left, right);
	}
	
	public static Subtraction create(AbstractExpression left, AbstractExpression right) throws HierarchyException {
		return new Subtraction(left, right);
	}

	@Override
	public IntegerWrapper calculateTransitively(IntegerWrapper left, IntegerWrapper right) {
		return IntegerWrapper.create(left.getValue() - right.getValue());
	}

}
