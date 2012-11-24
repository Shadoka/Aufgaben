package philosopher;

import lock.AbstractLock;
import lock.Lock;

public class Token {

	private AbstractLock lock;

	private Token() {
		this.lock = new Lock(false);
	}

	public static Token create() {
		return new Token();
	}

	public Token get() {
		this.getLock().lock();
		return this;
	}

	public void put() {
		this.getLock().unlock();
	}

	public AbstractLock getLock() {
		return lock;
	}

	public void setLock(AbstractLock lock) {
		this.lock = lock;
	}
}
