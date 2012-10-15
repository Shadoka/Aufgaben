package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class RectangularPartCollection {

	private Collection<RectangularPart> parts;

	public RectangularPartCollection() {
		this.setParts(new Vector<RectangularPart>());
	}

	public Collection<RectangularPart> getParts() {
		return parts;
	}

	public void setParts(Collection<RectangularPart> parts) {
		this.parts = parts;
	}

	public void add(final RectangularPartCollection parts) {
		final Iterator<RectangularPart> i = parts.getParts().iterator();
		while (i.hasNext()) {
			final RectangularPart current = i.next();
			if (!this.overlaps(current))
				this.add(current);
			else
				System.out.println("Aussortiert!");
		}
	}

	private boolean overlaps(final RectangularPart part) {
		final Iterator<RectangularPart> i = this.getParts().iterator();
		while (i.hasNext()) {
			final RectangularPart current = i.next();

			if (current.overlaps(part))
				return true;
		}
		return false;
	}

	public Vector toVector() {
		return (Vector) this.parts;
	}

	public void add(RectangularPart part) {
		this.getParts().add(part);
	}
}
