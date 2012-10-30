package test;

import static org.junit.Assert.assertEquals;
import model.Addition;
import model.Constant;
import model.HierarchyException;
import model.Multiplication;
import model.Variable;

import org.junit.Before;
import org.junit.Test;

import util.IntegerWrapper;
import util.StopCommand;

public class ExpressionTest {

	Variable var1;
	Variable var2;
	Variable var3;
	Variable var4;
	Variable var5;
	
	Constant const4;
	Constant const9;
	
	Addition add3;
	Addition add25;
	Addition add28;
	Addition add65;
	
	Addition addConstant;
	
	Addition addComplex68;
	Addition addComplex70;
	
	Addition addComplex;
	
	Multiplication mult2;
	
	Multiplication multComplexLow;
	Multiplication multComplexHigh;
	
	StopCommand stop;
	
	@Before
	public void setUp() throws HierarchyException {
		stop = StopCommand.create();
		
		var1 = Variable.create();
		var1.add(IntegerWrapper.create(1));
		var1.add(IntegerWrapper.create(23));
		var1.add(stop);
		
		var2 = Variable.create();
		var2.add(IntegerWrapper.create(2));
		var2.add(IntegerWrapper.create(42));
		var2.add(stop);
		
		var3 = Variable.create();
		var3.add(IntegerWrapper.create(23));
		var3.add(IntegerWrapper.create(1));
		var3.add(stop);
		
		var4 = Variable.create();
		var4.add(IntegerWrapper.create(42));
		var4.add(IntegerWrapper.create(2));
		var4.add(stop);
		
		var5 = Variable.create();
		var5.add(IntegerWrapper.create(5));
		var5.add(IntegerWrapper.create(7));
		var5.add(stop);
		
		const4 = Constant.create(4);
		
		const9 = Constant.create(9);
		
		add3 = Addition.create(var1, var2);
		add25 = Addition.create(var3, var4);
		add28 = Addition.create(var1, var3);
		add65 = Addition.create(var2, var4);
		
		addConstant = Addition.create(var1, const4);
		
		addComplex68 = Addition.create(add3, add25);

		addComplex70 = Addition.create(addComplex68, var5);

		mult2 = Multiplication.create(var1, var2);

		
		addComplex = Addition.create(var1, var2);
		
		multComplexLow = Multiplication.create(var3, var4);
		
		multComplexHigh = Multiplication.create(addComplex, multComplexLow);
	}
	
	@Test
	public void testVariables() {
		assertEquals(1, var1.getOutput().getAsIntIfPossible());
		assertEquals(23, var1.getOutput().getAsIntIfPossible());
		assertEquals(stop, var1.getOutput().get());
		assertEquals(2, var2.getOutput().getAsIntIfPossible());
		assertEquals(42, var2.getOutput().getAsIntIfPossible());
		assertEquals(stop, var2.getOutput().get());
		assertEquals(23, var3.getOutput().getAsIntIfPossible());
		assertEquals(1, var3.getOutput().getAsIntIfPossible());
		assertEquals(stop, var3.getOutput().get());
		assertEquals(42, var4.getOutput().getAsIntIfPossible());
		assertEquals(2, var4.getOutput().getAsIntIfPossible());
		assertEquals(stop, var4.getOutput().get());
	}

	@Test
	public void testAdditionVariables() {
		add3.start();
		assertEquals(3, add3.getOutput().getAsIntIfPossible());
		assertEquals(65, add3.getOutput().getAsIntIfPossible());
		add25.start();
		assertEquals(65, add25.getOutput().getAsIntIfPossible());
		assertEquals(3, add25.getOutput().getAsIntIfPossible());
	}
	
	@Test
	public void testAddConstants() {
		addConstant.start();
		assertEquals(5, addConstant.getOutput().getAsIntIfPossible());
		assertEquals(27, addConstant.getOutput().getAsIntIfPossible());
	}
	
	@Test
	public void testAdditionAbstractExp() {
		addComplex68.start();
		assertEquals(68, addComplex68.getOutput().getAsIntIfPossible());
		assertEquals(68, addComplex68.getOutput().getAsIntIfPossible());
	}
	
	@Test
	public void testAdditionAbstractExp2() {
		addComplex70.start();
		assertEquals(73, addComplex70.getOutput().getAsIntIfPossible());
		assertEquals(75, addComplex70.getOutput().getAsIntIfPossible());
	}
	
	@Test
	public void testMultiplicationConstExp() {
		mult2.start();
		assertEquals(2, mult2.getOutput().getAsIntIfPossible());
		assertEquals(966, mult2.getOutput().getAsIntIfPossible());
	}
	
	@Test
	public void testMultiplicationComplex() {
		multComplexHigh.start();
		assertEquals(2898, multComplexHigh.getOutput().getAsIntIfPossible());
		assertEquals(130, multComplexHigh.getOutput().getAsIntIfPossible());
	}
}
