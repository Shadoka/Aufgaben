package philosopher;

import java.util.Iterator;
import java.util.Vector;

import behaviour.DontCareBehaviour;
import behaviour.TokenCareBehaviour;

public class PhiloManager {

	private static PhiloManager instance = null;

	private Vector<Philosopher> thinking;
	private Vector<Philosopher> eating;
	private long waitingTime;
	private boolean isRunning;

	private PhiloManager() {
		this.thinking = new Vector<>();
		this.eating = new Vector<>();
		this.waitingTime = 5000;
		this.isRunning = false;
	}

	public static PhiloManager getInstance() {
		if (instance == null) {
			instance = new PhiloManager();
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
				if (i == count - 1) {
					Philosopher before = this.getThinking().get(i - 1);
					Philosopher next = Philosopher.create(this.getThinking()
							.get(0).getRight(), before.getLeft());
					this.getThinking().add(next);
				} else {
					Philosopher before = this.getThinking().get(i - 1);
					Token left = Token.create();
					Philosopher next = Philosopher.create(left,
							before.getLeft());
					this.getThinking().add(next);
				}
			}
		}
	}

	public void startAllPhilosophers(boolean tokenCare) {
		if (tokenCare) {
			Iterator<Philosopher> i = this.getThinking().iterator();
			while (i.hasNext()) {
				Philosopher current = i.next();
				current.setBehaviour(TokenCareBehaviour.create());
				current.start();
			}
		} else {
			Iterator<Philosopher> i = this.getThinking().iterator();
			while (i.hasNext()) {
				Philosopher current = i.next();
				current.setBehaviour(DontCareBehaviour.create());
				current.start();
			}
		}
	}

	public void addThinking(Philosopher p) {
		if (this.getEating().contains(p)) {
			this.getEating().remove(p);
		}
		this.getThinking().add(p);
		PTOMonitor.getInstance().getEating().remove(p);
	}

	public void addEating(Philosopher p) {
		if (this.getThinking().contains(p)) {
			this.getThinking().remove(p);
		}
		this.getEating().add(p);
		PTOMonitor.getInstance().getEaters().put(p);
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
