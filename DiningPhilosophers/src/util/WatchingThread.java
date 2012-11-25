package util;

import philosopher.PTOMonitor;
import view.PhiloView;

public class WatchingThread implements Runnable {

	private WatchingThread() {

	}

	public static WatchingThread create() {
		return new WatchingThread();
	}

	@Override
	public void run() {
		while (PTOMonitor.getInstance().isRunning()) {
			int eating = PTOMonitor.getInstance().getEating().size();
			if (eating > Integer.parseInt(PhiloView.getInstance().getMaxPhilo()
					.getText())) {
				PhiloView.getInstance().updateMaxPhilo(eating);
			}
		}
	}

}
