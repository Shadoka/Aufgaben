package util;

import java.util.Vector;

import lock.AbstractLock;
import lock.Lock;

public class OutputBuffer {

	private Vector<BufferEntry> output;
	private AbstractLock lock;
	private AbstractLock criticalSection;
	
	private OutputBuffer() {
		this.output = new Vector<BufferEntry>();
		this.lock = new Lock(true);
		this.criticalSection = new Lock(false);
	}
	
	public static OutputBuffer create() {
		return new OutputBuffer();
	}
	
	public void add(BufferEntry entry) {
		this.criticalSection.lock();
		this.getOutput().add(entry);
		this.lock.unlock();
		this.criticalSection.unlock();
	}
	
	public BufferEntry get(int pointer) {
		while(pointer >= this.getOutput().size()) {
			this.lock.lock();
		}
		this.criticalSection.lock();
		BufferEntry result = this.getOutput().get(pointer);
		this.lock.unlock();
		this.criticalSection.unlock();
		return result;
	}

	public Vector<BufferEntry> getOutput() {
		return output;
	}

	public void setOutput(Vector<BufferEntry> output) {
		this.output = output;
	}

	public AbstractLock getLock() {
		return lock;
	}

	public void setLock(AbstractLock lock) {
		this.lock = lock;
	}
}
