package state;


public interface IsNotCachedStateVisitor {

	public boolean visit(NotCompletelyCached state);
	public boolean visit(Cached state);
}
