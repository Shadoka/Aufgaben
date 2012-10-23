package observer;

import model.Expression;

public abstract class Observer implements Runnable {

	private Thread thread;
	private Buffer<Expression> buffer;

	/**
	 * Operation that each observer needs to implement in order to receive
	 * asynchronous notifications
	 */
	public Observer() {
		this.buffer = new Buffer<>();
		this.thread = new Thread(this, "Updater");
		this.thread.start();
	}

	protected void update(Expression expression) {
		this.buffer.put(expression);
	}

	public void run() {
		while (true) {
			Expression nextExpression = this.buffer.get();
			this.updateToSubClass(nextExpression);
		}
	}

	protected abstract void updateToSubClass(Expression expression);
}
