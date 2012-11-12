package test;

import static org.junit.Assert.assertEquals;
import model.main.Material;
import model.main.Product;

import org.junit.Before;
import org.junit.Test;

public class TestPartlist {

	private Material m1, m2, m3, m4;
	private Product p1, p2, p3, p4, p5, p6;

	@Before
	public void setUp() throws Exception {
		m1 = Material.create("Reifen", 50);
		m2 = Material.create("Karosserie", 2000);
		m3 = Material.create("Felgen", 20);
		m4 = Material.create("Lack", 1000);

		p1 = Product.create("Auto", 10000);
		p2 = Product.create("Motorrad", 5000);
		p3 = Product.create("Traktor", 15000);
		p4 = Product.create("Doppelprodukt", 20000);
		p5 = Product.create("Mehrfachprodukt", 2000);
		p6 = Product.create("Multiprodukt", 6000);

		p1.addPart(m1, 4);
		p1.addPart(m2, 1);
		p1.addPart(m3, 4);

		p2.addPart(m1, 2);
		p2.addPart(m2, 1);
		p2.addPart(m3, 2);

		p3.addPart(m1, 4);
		p3.addPart(m2, 1);
		p3.addPart(m3, 4);

		p4.addPart(p1, 2);
		p4.addPart(m2, 1);

		p5.addPart(p4, 1);
		p5.addPart(p1, 2);
		p5.addPart(m3, 3);

		p6.addPart(m4, 1);
		p6.addPart(m2, 2);
		p6.addPart(p4, 1);
		p6.addPart(p3, 1);
	}

	@Test
	public void testGetOverallPrice() {
		assertEquals(12280, p1.getOverallPrice());
		assertEquals(7140, p2.getOverallPrice());
		assertEquals(17280, p3.getOverallPrice());
		assertEquals(46560, p4.getOverallPrice());
		assertEquals(73180, p5.getOverallPrice());
		assertEquals(74840, p6.getOverallPrice());
	}

}