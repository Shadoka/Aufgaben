package model;

import util.BufferEntry;

/**
 * Class represents a mathematical expression.
 * @author Shadoka
 *
 */
public interface Expression{

	/**
	 * Returns true if and only if one component of the expression contains itself as an argument.
	 * @param e : Expression
	 * @return : boolean
	 */
	public boolean contains(Expression e);
	
	/**
	 * Calculates the next value based on the inputstream/s, which are/is given to the expression.
	 * In the types of "AbstractComposite" it is checked before calculation whether or not one of the arguments is a "StopCommand".
	 * In the type "Variable" this isnt needed.
	 * @return : BufferEntry
	 */
	public BufferEntry calculate();
}
