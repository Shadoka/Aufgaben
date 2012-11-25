package philosopher;

import java.util.Vector;

public class PTOMonitor {

	private static PTOMonitor instance = null;

	private Vector<Philosopher> thinking;
	private Vector<Philosopher> eating;
	private long waitingTime;

	private PTOMonitor() {
		this.thinking = new Vector<>();
		this.eating = new Vector<>();
		this.waitingTime = 5000;
	}

	public static PTOMonitor getInstance() {
		if (instance == null) {
			instance = new PTOMonitor();
		}
		return instance;
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
}
