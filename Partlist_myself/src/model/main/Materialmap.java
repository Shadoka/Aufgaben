package model.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Materialmap extends HashMap<String, QuantifiedComponent>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4222984037417899136L;

	private Materialmap() {
		super();
	}

	private Materialmap(Collection<QuantifiedComponent> map) {
		Iterator<QuantifiedComponent> i = map.iterator();
		while (i.hasNext()) {
			QuantifiedComponent current = i.next();
			this.put(current.getComponent().getName(), current);
		}
	}
	
	public static Materialmap createMaterialmap() {
		return new Materialmap();
	}
	
	public static Materialmap createMaterialmap(Collection<QuantifiedComponent> map) {
		return new Materialmap(map);
	}
	
	/**
	 * Operation merges two Materialmaps into a NEW one with the given quantity of the right Materialmap.
	 * @param left
	 * @param right
	 * @param quantity
	 * @return
	 */
	public Materialmap merge(Materialmap right, int quantity) {
		Materialmap result = Materialmap.createMaterialmap();
		ArrayList<QuantifiedComponent> temp = new ArrayList<QuantifiedComponent>(this.values());
		ArrayList<QuantifiedComponent> rightParts = new ArrayList<QuantifiedComponent>(right.values());
		
		for (int i = 0; i < temp.size(); i++) {
			result.put(temp.get(i).getComponent().getName(), temp.get(i));
		}
		for (int x = 0; x < rightParts.size(); x++) {
			if (result.containsKey(rightParts.get(x).getComponent().getName())) {
				result.get(rightParts.get(x).getComponent().getName()).setQuantity(result.get(rightParts.get(x).getComponent().getName()).getQuantity() + rightParts.get(x).getQuantity() * quantity);
			} else {
				rightParts.get(x).setQuantity(rightParts.get(x).getQuantity() * quantity);
				result.put(rightParts.get(x).getComponent().getName(), rightParts.get(x));
			}
		}		
		
		return result;
	}
}
