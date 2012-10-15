package test;

import static org.junit.Assert.assertEquals;
import model.Point;
import model.RectangularPart;
import model.Window;

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
	RectangularPart part8;
	RectangularPart part9;
	RectangularPart part10;

	Window win1;

	@Before
	public void setUp() throws Exception {
		part1 = new RectangularPart(new Point(0, 0), 100, 100);
		part2 = new RectangularPart(new Point(50, 50), 100, 100);
		part3 = new RectangularPart(new Point(20, 20), 50, 50);
		part4 = new RectangularPart(new Point(101, 101), 10, 10);
		part5 = new RectangularPart(new Point(100, 100), 10, 10);
		part6 = new RectangularPart(new Point(10, 30), 70, 10);
		part7 = new RectangularPart(new Point(100, 0), 100, 100);
		part8 = new RectangularPart(new Point(0, 0), 200, 100);
		part9 = new RectangularPart(new Point(100, 100), 200, 100);
		part10 = new RectangularPart(new Point(50, 50), 100, 100);

		Window win1 = new Window();
		win1.move(100, 100);
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
		assertEquals(false, part9.overlaps(part8));
		assertEquals(false, part8.overlaps(part9));
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

		assertEquals(50, part1.calculateSpaceNorth(part10).getWidth());
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

	@Test
	public void testIsSpaceEast() {
		assertEquals(true, part1.isSpaceEast(part3));
		assertEquals(false, part1.isSpaceEast(part1));
	}

	@Test
	public void testIsSpaceWest() {
		assertEquals(true, part1.isSpaceWest(part3));
		assertEquals(false, part1.isSpaceWest(part1));
	}

	@Test
	public void testIsSpaceNorth() {
		assertEquals(true, part1.isSpaceNorth(part3));
		assertEquals(false, part1.isSpaceNorth(part7));
	}

}
