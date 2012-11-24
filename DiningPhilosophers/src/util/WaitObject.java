package util;

public class WaitObject {

	private WaitObject() {

	}

	public static WaitObject create() {
		return new WaitObject();
	}

	public synchronized void waitWithTime(long time) {
		try {
			this.wait(time);
		} catch (InterruptedException e) {

		}
	}
}
