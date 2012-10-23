package model;

import observer.BufferEntry;

public interface Expression extends BufferEntry{

	public boolean contains(Expression e);
}
