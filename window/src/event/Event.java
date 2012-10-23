package event;

public interface Event {
	public void accept(EventVisitor v);
}
