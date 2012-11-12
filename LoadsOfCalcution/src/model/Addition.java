package model;

import util.IntegerWrapper;

/**
 * Represents the mathematical addition.
 * @author Admin
 *
 */
public class Addition extends AbstractComposite {

	private Addition(AbstractExpression left, AbstractExpression right) throws HierarchyException {
		super(left, right);
	}
	
	public static Addition create(AbstractExpression left, AbstractExpression right) throws HierarchyException {
		return new Addition(left, right);
	}

	@Override
	public IntegerWrapper calculateTransitively(IntegerWrapper left, IntegerWrapper right) {
		return IntegerWrapper.create(left.getValue() + right.getValue());
	}
	
}
