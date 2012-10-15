package state;

import model.RectangularPartCollection;

public interface StateVisitor {
	public RectangularPartCollection visit(Cached state);

	public RectangularPartCollection visit(NotCached state);
}
