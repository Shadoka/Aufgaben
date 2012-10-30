package model;

import util.BufferEntry;
import util.ConcreteBufferEntryStopVisitor;
import util.ConcreteBufferEntryVisitor;
import util.IntegerWrapper;
import util.StopCommand;

/**
 * This class is the superclass of all composed Expression, as there are Addition, Subtraction, 
 * Multiplication and Division.
 * @author Shadoka
 *
 */
public abstract class AbstractComposite extends AbstractExpression {

	private AbstractExpression left;
	private AbstractExpression right;
	
	protected AbstractComposite(AbstractExpression left, AbstractExpression right) throws HierarchyException {
		super();
		if (left.contains(this)) {
			this.throwError();
		}
		if (right.contains(this)) {
			this.throwError();
		}
		this.setLeft(left);
		this.setRight(right);
	}
	
	private void throwError() throws HierarchyException {
		throw HierarchyException.create(HierarchyException.getErrormessage());
	}
	
	@Override
	public void run() {
		this.setAlreadyRunned(true);
		while(true) {
			if (!this.getLeft().isAlreadyRunned()) {
				this.getLeft().start();
			}
			if (!this.getRight().isAlreadyRunned()) {
				this.getRight().start();
			}
			
			BufferEntry result = this.calculate();
			this.getOutput().put(result);
			if (result.accept(ConcreteBufferEntryStopVisitor.create())) {
				System.out.println("ich war auch hier");
				break;
			}
		}
	}
	
	@Override
	public BufferEntry calculate() {
		BufferEntry firstArgument = this.getLeft().getOutput().get();
		BufferEntry secondArgument = this.getRight().getOutput().get();
		
		boolean stopLeft = firstArgument.accept(ConcreteBufferEntryStopVisitor.create());
		boolean stopRight = secondArgument.accept(ConcreteBufferEntryStopVisitor.create());
		
		if (stopLeft || stopRight) {
			return StopCommand.create();
		}
		
		// Cast ist hässlich, aber ungefährlich dank obiger Überprüfung
		IntegerWrapper left = (IntegerWrapper) firstArgument.accept(ConcreteBufferEntryVisitor.create());
		IntegerWrapper right = (IntegerWrapper) secondArgument.accept(ConcreteBufferEntryVisitor.create());
		
		IntegerWrapper result = this.calculateTransitively(left, right);
		
		return result;
	}
	
	/**
	 * Templatemethod, each child of this class will implement the calculation in its own way.
	 * @param left : IntegerWrapper
	 * @param right : IntegerWrapper
	 * @return : IntegerWrapper
	 */
	public abstract IntegerWrapper calculateTransitively(IntegerWrapper left, IntegerWrapper right);

	public AbstractExpression getLeft() {
		return left;
	}

	public void setLeft(AbstractExpression left) {
		this.left = left;
	}

	public AbstractExpression getRight() {
		return right;
	}

	public void setRight(AbstractExpression right) {
		this.right = right;
	}

	@Override
	public boolean contains(Expression e) {
		return this.getLeft().contains(e) || this.getRight().contains(e);
	}

}
