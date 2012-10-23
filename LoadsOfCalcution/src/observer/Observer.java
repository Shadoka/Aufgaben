package observer;


public abstract class Observer implements Runnable {
	
	private Thread thread;
	private Buffer<Integer> buffer;

	/**Operation that each observer needs to implement in 
	 * order to receive asynchronous notifications
	 */
	public Observer() {
		this.buffer = new Buffer<Integer>();
		this.thread = new Thread(this,"Updater");
		this.thread.start();
	}
	
	protected void update(int value){
		this.buffer.put(new Integer(value));
	}
	
	public void run() {
		while (true){
			int nextValue = this.buffer.get();
			this.updateToSubClass(nextValue);
		}
	}

	protected abstract void updateToSubClass(int value);
}
