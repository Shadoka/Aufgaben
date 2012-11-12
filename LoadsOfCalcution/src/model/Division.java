package model;

import util.IntegerWrapper;

/**
 * Represents the mathematical division.
 * @author Shadoka
 *
 */
public class Division extends AbstractComposite {

	private Division(AbstractExpression left, AbstractExpression right) throws HierarchyException {
		super(left, right);
	}
	
	public static Division create(AbstractExpression left, AbstractExpression right) throws HierarchyException {
		return new Division(left, right);
	}

	@Override
	public IntegerWrapper calculateTransitively(IntegerWrapper left, IntegerWrapper right) {
		return IntegerWrapper.create(left.getValue() / right.getValue());
	}

}
