package model;

public abstract class AbstractExpression implements Expression {

	private Expression left;
	private Expression right;
	
	protected AbstractExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		if (this.contains(left)) {
			throw new Error(HierarchyException.create(HierarchyException.getErrormessage()));
		} else {
			this.left = left;
		}
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		if (this.contains(right)) {
			throw new Error(HierarchyException.create(HierarchyException.getErrormessage()));
		} else {
			this.right = right;
		}
	}

	@Override
	public boolean contains(Expression e) {
		return this.getLeft().contains(e) && this.getRight().contains(e);
	}

}
