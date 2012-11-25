package util;

import philosopher.PTOMonitor;
import view.PhiloView;

public class WatchingThread implements Runnable {

	private int sum;
	private int repetition;

	private WatchingThread() {
		this.sum = 0;
		this.repetition = 0;
	}

	public static WatchingThread create() {
		return new WatchingThread();
	}

	@Override
	public void run() {
		while (PTOMonitor.getInstance().isRunning()) {
			this.setRepetition(this.getRepetition() + 1);
			int eating = PTOMonitor.getInstance().getEating().size();
			if (eating > Integer.parseInt(PhiloView.getInstance().getMaxPhilo()
					.getText())) {
				PhiloView.getInstance().updateMaxPhilo(eating);
			}
			this.setSum(this.getSum() + eating);
			PhiloView.getInstance().updateAveragePhilo(
					this.getSum() / this.getRepetition());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

}
