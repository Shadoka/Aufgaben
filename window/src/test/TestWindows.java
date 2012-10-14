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
	RectangularPart part6;
	RectangularPart part7;

	@Before
	public void setUp() throws Exception {
		part1 = new RectangularPart(new Point(0, 0), 100, 100);
		part2 = new RectangularPart(new Point(50, 50), 100, 100);
		part3 = new RectangularPart(new Point(20, 20), 50, 50);
		part4 = new RectangularPart(new Point(101, 101), 10, 10);
		part5 = new RectangularPart(new Point(100, 100), 10, 10);
		part6 = new RectangularPart(new Point(10, 30), 70, 10);
		part7 = new RectangularPart(new Point(100, 0), 100, 100);
	}

	@Test
	public void testOverlaps() {
		assertEquals(true, part1.overlaps(part2));
		assertEquals(true, part2.overlaps(part1));
		assertEquals(true, part3.overlaps(part1));
		assertEquals(true, part1.overlaps(part3));
		assertEquals(false, part4.overlaps(part1));
		assertEquals(false, part1.overlaps(part5));
		assertEquals(false, part1.overlaps(part7));
	}

	@Test
	public void testCalculateSpaceWest() {
		assertEquals(0, part1.calculateSpaceWest(part2).getLeftUpperCorner()
				.getX());
		assertEquals(0, part1.calculateSpaceWest(part2).getLeftUpperCorner()
				.getY());
		assertEquals(50, part1.calculateSpaceWest(part2).getWidth());
		assertEquals(100, part1.calculateSpaceWest(part2).getHeight());
	}

	@Test
	public void testCalculateSpaceEast() {
		assertEquals(70, part1.calculateSpaceEast(part3).getLeftUpperCorner()
				.getX());
		assertEquals(0, part1.calculateSpaceEast(part3).getLeftUpperCorner()
				.getY());
		assertEquals(30, part1.calculateSpaceEast(part3).getWidth());
		assertEquals(100, part1.calculateSpaceEast(part3).getHeight());
	}

	@Test
	public void testCalculateSpaceNorth() {
		assertEquals(20, part1.calculateSpaceNorth(part3).getLeftUpperCorner()
				.getX());
		assertEquals(0, part1.calculateSpaceNorth(part3).getLeftUpperCorner()
				.getY());
		assertEquals(50, part1.calculateSpaceNorth(part3).getWidth());
		assertEquals(20, part1.calculateSpaceNorth(part3).getHeight());

		assertEquals(20, part3.calculateSpaceNorth(part6).getLeftUpperCorner()
				.getX());
		assertEquals(20, part3.calculateSpaceNorth(part6).getLeftUpperCorner()
				.getY());
		assertEquals(50, part3.calculateSpaceNorth(part6).getWidth());
		assertEquals(10, part3.calculateSpaceNorth(part6).getHeight());
	}

	@Test
	public void testCalculateSpaceSouth() {
		assertEquals(20, part3.calculateSpaceSouth(part6).getLeftUpperCorner()
				.getX());
		assertEquals(40, part3.calculateSpaceSouth(part6).getLeftUpperCorner()
				.getY());
		assertEquals(50, part3.calculateSpaceSouth(part6).getWidth());
		assertEquals(30, part3.calculateSpaceSouth(part6).getHeight());
	}

}
