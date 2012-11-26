package philosopher;

import java.util.Vector;

import util.Buffer;
import view.PhiloView;

public class PTOMonitor implements Runnable {

	private static PTOMonitor instance = null;

	private Buffer<Philosopher> eaters;
	private Vector<Philosopher> eating;

	private PTOMonitor() {
		this.eaters = new Buffer<>();
		this.eating = new Vector<>();
	}

	public static PTOMonitor getInstance() {
		if (instance == null) {
			instance = new PTOMonitor();
		}
		return instance;
	}

	@Override
	public void run() {
		while (PhiloManager.getInstance().isRunning()) {
			Philosopher beginningEater = this.getEaters().get();
			for (int i = 0; i < this.getEating().size(); i++) {
				Philosopher current = this.getEating().get(i);
				if (current.getMyId() == beginningEater.getMyId() - 1
						|| current.getMyId() == beginningEater.getMyId() + 1) {
					PhiloView.getInstance().reportBadBehaviour(
							beginningEater.getMyId(), current.getMyId());
				}
			}
			this.getEating().add(beginningEater);
		}
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public Buffer<Philosopher> getEaters() {
		return eaters;
	}

	public void setEaters(Buffer<Philosopher> eaters) {
		this.eaters = eaters;
	}

	public Vector<Philosopher> getEating() {
		return eating;
	}

	public void setEating(Vector<Philosopher> eating) {
		this.eating = eating;
	}
}
