package model.util.visitor;

import model.main.Materialmap;
import model.util.state.MaterialCached;
import model.util.state.MaterialPriceCached;
import model.util.state.NotCached;

public interface MaterialStateVisitor {

	public Materialmap visit(NotCached state);
	public Materialmap visit(MaterialCached state);
	public Materialmap visit(MaterialPriceCached state);
}
