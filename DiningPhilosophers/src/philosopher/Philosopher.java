package philosopher;

import java.util.Random;

import state.EatingState;
import state.State;
import state.ThinkingState;
import util.StateVisitor;

public class Philosopher implements Runnable {

	private State state;
	private Token left;
	private Token right;

	private Philosopher(Token left, Token right) {
		System.out.println("Wurde als denkend initialisiert");
		this.state = ThinkingState.create(this);
		this.left = left;
		this.right = right;
	}

	public static Philosopher create(Token left, Token right) {
		return new Philosopher(left, right);
	}

	@Override
	public void run() {
		long wait;
		while (true) {
			wait = this.getWaitingTime();
			this.waitWithTime(wait);
			this.changeState();
		}
	}

	private synchronized void waitWithTime(long wait) {
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			System.out.println("bin ich hier?");
		}
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	private long getWaitingTime() {
		Random rnd = new Random();
		long wait = rnd.nextLong() % 5000 + 1;
		if (wait <= 0) {
			wait = wait * -1 + 1;
		}
		return wait;
	}

	private void changeState() {
		this.setState(this.getState().accept(new StateVisitor() {

			@Override
			public State visit(EatingState state) {
				System.out.println("Wechsle in: denkend");
				Philosopher.this.reportMeThinking();
				return ThinkingState.create(Philosopher.this);
			}

			@Override
			public State visit(ThinkingState state) {
				System.out.println("Wechsle in: essend");
				Philosopher.this.reportMeEating();
				return EatingState.create(Philosopher.this);
			}

		}));
	}

	public void reportMeThinking() {
		PTOMonitor.getInstance().addThinking(this);
	}

	public void reportMeEating() {
		PTOMonitor.getInstance().addEating(this);
	}

	/*********** Getter and Setter ***********/

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Token getLeft() {
		return left;
	}

	public void setLeft(Token left) {
		this.left = left;
	}

	public Token getRight() {
		return right;
	}

	public void setRight(Token right) {
		this.right = right;
	}
}
