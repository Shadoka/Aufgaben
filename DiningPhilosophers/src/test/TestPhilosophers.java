package test;

import org.junit.Before;
import org.junit.Test;

import philosopher.Philosopher;

public class TestPhilosophers {

	Philosopher phil1;

	@Before
	public void setUp() {
		phil1 = Philosopher.create();
	}

	@Test
	public void test1() {
		phil1.start();
	}

}
