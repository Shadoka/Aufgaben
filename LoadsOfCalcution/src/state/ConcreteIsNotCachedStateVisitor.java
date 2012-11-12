package state;


public class ConcreteIsNotCachedStateVisitor implements IsNotCachedStateVisitor{

	private ConcreteIsNotCachedStateVisitor() {
		
	}
	
	public static ConcreteIsNotCachedStateVisitor create() {
		return new ConcreteIsNotCachedStateVisitor();
	}

	@Override
	public boolean visit(NotCompletelyCached state) {
		return true;
	}

	@Override
	public boolean visit(Cached state) {
		return false;
	}
	
}
