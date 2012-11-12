package model.util.visitor;

import model.util.state.MaterialCached;
import model.util.state.MaterialPriceCached;
import model.util.state.NotCached;

public interface MaterialPriceStateVisitor {

	public int visit(NotCached state);
	public int visit(MaterialCached state);
	public int visit(MaterialPriceCached state);
}
