package model;

public class ConstExpression implements Expression {

	@Override
	public boolean contains(Expression e) {
		return this.equals(e);
	}

}
