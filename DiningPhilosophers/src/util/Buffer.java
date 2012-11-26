package util;

import java.util.List;
import java.util.Vector;

import lock.AbstractLock;
import lock.Lock;

public class Buffer<X> {

	private List<X> data;
	private AbstractLock lock;
	private AbstractLock criticalSection;

	public Buffer() {
		this.data = new Vector<X>();
		this.lock = new Lock(true);
		this.criticalSection = new Lock(false);
	}

	public void put(X object) {
		this.criticalSection.lock();
		this.data.add(object); // 1
		this.lock.unlock(); // 2*
		this.criticalSection.unlock();
	}

	public X get() {
		this.lock.lock();
		this.criticalSection.lock();
		X result = this.data.get(0); // 5
		this.data.remove(0);
		if (!data.isEmpty())
			this.lock.unlock(); // 6
		this.criticalSection.unlock();
		return result; // 7
	}

	public boolean isEmpty() {
		return this.data.isEmpty();
	}
}
