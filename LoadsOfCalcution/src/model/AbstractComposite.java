package model;

import state.Cached;
import state.ConcreteIsNotCachedStateVisitor;
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
		System.out.println(this.getLeft() + " + " + this.getRight());
		while(true) {
			if (!this.getLeft().isAlreadyRunned()) {
				this.getLeft().start();
			}
			if (!this.getRight().isAlreadyRunned()) {
				this.getRight().start();
			}
			
			while(this.getRight().getState().accept(ConcreteIsNotCachedStateVisitor.create()) ||
					this.getLeft().getState().accept(ConcreteIsNotCachedStateVisitor.create())) { 
				
			}
			System.out.println("Left: " + (this.getLeft().getState() instanceof Cached) + ", Right: "
					 + (this.getRight().getState() instanceof Cached));
			BufferEntry result = this.calculate();
			this.getState().add(result);
			if (result.accept(ConcreteBufferEntryStopVisitor.create())) {
				break;
			}
		}
		this.setState(Cached.create(this, this.getState().getOutput()));
		System.out.println(this.getState() instanceof Cached);
		System.out.println("Bin fertig");
	}
	
	@Override
	public BufferEntry calculate() {
		System.out.println("outputpointer: " + this.getOutputPointer());
		BufferEntry firstArgument = this.getLeft().get(this.getOutputPointer());
		BufferEntry secondArgument = this.getRight().get(this.getOutputPointer());
		
		boolean stopLeft = firstArgument.accept(ConcreteBufferEntryStopVisitor.create());
		boolean stopRight = secondArgument.accept(ConcreteBufferEntryStopVisitor.create());
		
		if (stopLeft || stopRight) {
			this.setOutputPointer(0);
			return StopCommand.create();
		} else {
			this.setOutputPointer(this.getOutputPointer() + 1);
		}
		
		// Cast ist hässlich, aber ungefährlich dank obiger Überprüfung
		IntegerWrapper left = (IntegerWrapper) firstArgument.accept(ConcreteBufferEntryVisitor.create());
		IntegerWrapper right = (IntegerWrapper) secondArgument.accept(ConcreteBufferEntryVisitor.create());
		
		IntegerWrapper result = this.calculateTransitively(left, right);
		
		return result;
	}
	
	@Override
	public BufferEntry get(int pointer) {
		BufferEntry result = this.getState().get(pointer);
		return result;
	}
	
	/**
	 * Templatemethod, all children of this class will implement the calculation in their own way.
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
