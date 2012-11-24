package philosopher;

import java.util.Random;

import state.EatingState;
import state.State;
import state.ThinkingState;
import util.StateVisitor;

public class Philosopher implements Runnable {

	private State state;

	private Philosopher() {
		System.out.println("Wurde als denkend initialisiert");
		this.state = ThinkingState.create(this);
	}

	public static Philosopher create() {
		return new Philosopher();
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

			System.out.println("davor");
			System.out.println("Soll:" + wait);
			long before = System.nanoTime();
			Thread.sleep(wait);
			long slept = System.nanoTime() - before;
			System.out.println("danach");
			System.out.println("Ist:" + slept * 1e-6);// Umrechnen von ns in ms

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
				return ThinkingState.create(Philosopher.this);
			}

			@Override
			public State visit(ThinkingState state) {
				System.out.println("Wechsle in: essend");
				return EatingState.create(Philosopher.this);
			}

		}));
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
