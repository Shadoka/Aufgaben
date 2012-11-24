package philosopher;

import java.util.Vector;

public class PTOMonitor {

	private static PTOMonitor instance = null;

	private Vector<Philosopher> thinking;
	private Vector<Philosopher> eating;

	private PTOMonitor() {
		this.thinking = new Vector<>();
		this.eating = new Vector<>();
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
}
