package philosopher;

import lock.AbstractLock;
import lock.Lock;

public class Token {

	private AbstractLock lock;
	private AbstractLock criticalSection;
	private boolean taken;

	private Token() {
		this.taken = false;
		this.lock = new Lock(false);
		this.criticalSection = new Lock(false);
	}

	public static Token create() {
		return new Token();
	}

	public void get() {
		this.getCriticalSection().lock();
		this.getLock().lock();
		this.setTaken(true);
		this.getCriticalSection().unlock();
	}

	public void put() {
		this.getCriticalSection().lock();
		this.getLock().unlock();
		if (!taken)
			System.err.println("War noch nicht genommen!");
		this.setTaken(false);
		this.getCriticalSection().unlock();
	}

	public AbstractLock getLock() {
		return lock;
	}

	public void setLock(AbstractLock lock) {
		this.lock = lock;
	}

	public AbstractLock getCriticalSection() {
		return criticalSection;
	}

	public void setCriticalSection(AbstractLock criticalSection) {
		this.criticalSection = criticalSection;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}
}
