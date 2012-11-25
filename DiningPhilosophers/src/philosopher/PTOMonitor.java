package philosopher;

import java.util.Iterator;
import java.util.Vector;

public class PTOMonitor {

	private static PTOMonitor instance = null;

	private Vector<Philosopher> thinking;
	private Vector<Philosopher> eating;
	private long waitingTime;
	private boolean isRunning;

	private PTOMonitor() {
		this.thinking = new Vector<>();
		this.eating = new Vector<>();
		this.waitingTime = 5000;
		this.isRunning = false;
	}

	public static PTOMonitor getInstance() {
		if (instance == null) {
			instance = new PTOMonitor();
		}
		return instance;
	}

	public void addPhilosophers(int count) {
		for (int i = 0; i < count; i++) {
			if (this.getThinking().isEmpty()) {
				Token left = Token.create();
				Token right = Token.create();
				Philosopher phil = Philosopher.create(left, right);
				this.getThinking().add(phil);
			} else {
				Philosopher before = this.getThinking().get(
						this.getThinking().size() - 1);
				Token right = Token.create();
				Philosopher next = Philosopher.create(before.getLeft(), right);
				this.getThinking().add(next);
			}
		}
	}

	public void startAllPhilosophers(boolean tokenCare) {
		if (tokenCare) {

		} else {
			Iterator<Philosopher> i = this.getThinking().iterator();
			while (i.hasNext()) {
				Philosopher current = i.next();
				current.start();
			}
		}
	}

	public void addThinking(Philosopher p) {
		if (this.getEating().contains(p)) {
			this.getEating().remove(p);
		}
		this.getThinking().add(p);
	}

	public void addEating(Philosopher p) {
		if (this.getThinking().contains(p)) {
			this.getThinking().remove(p);
		}
		this.getEating().add(p);
		this.checkPTO();
	}

	private void checkPTO() {

	}

	/************** Getter and Setter **********/

	public Vector<Philosopher> getEating() {
		return eating;
	}

	public void setEating(Vector<Philosopher> eating) {
		this.eating = eating;
	}

	public Vector<Philosopher> getThinking() {
		return thinking;
	}

	public void setThinking(Vector<Philosopher> thinking) {
		this.thinking = thinking;
	}

	public long getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(long waitingTime) {
		this.waitingTime = waitingTime;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
