package test;

import static org.junit.Assert.assertEquals;
import model.Point;
import model.RectangularPart;

import org.junit.Before;
import org.junit.Test;

public class TestWindows {

	RectangularPart part1;
	RectangularPart part2;
	RectangularPart part3;
	RectangularPart part4;
	RectangularPart part5;

	@Before
	public void setUp() throws Exception {
		part1 = new RectangularPart(new Point(0, 0), 100, 100);
		part2 = new RectangularPart(new Point(50, 50), 100, 100);
		part3 = new RectangularPart(new Point(20, 20), 50, 50);
		part4 = new RectangularPart(new Point(101, 101), 10, 10);
		part5 = new RectangularPart(new Point(100, 100), 10, 10);
	}

	@Test
	public void testIsParentOf() {
		assertEquals(true, part1.isPartOf(part2));
		assertEquals(true, part2.isPartOf(part1));
		assertEquals(true, part3.isPartOf(part1));
		assertEquals(true, part1.isPartOf(part3));
		assertEquals(false, part4.isPartOf(part1));
		assertEquals(true, part1.isPartOf(part5));
	}

}
