package test;

import philosopher.Philosopher;
import philosopher.Token;

public class Test {

	public static void main(String[] args) {
		Token token1 = Token.create();
		Token token2 = Token.create();
		Philosopher phil1 = Philosopher.create(token1, token2);
		Philosopher phil2 = Philosopher.create(token2, token1);
		phil1.start();
		phil2.start();
	}
}
