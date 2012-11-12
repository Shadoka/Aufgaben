package model.main;

import java.util.Vector;

public interface Component {

	/**
	 * Operation adds a part(part) to an existing whole(this) with a given amount(amount). 
	 * @param part
	 * @param amount
	 * @throws Exception
	 */
	public void addPart(ComponentCommon part, int amount) throws Exception;
	
	/**
	 * Operation checks whether a Component(this) contains another Component(component) or not.
	 * @param component
	 * @return
	 */
	public boolean contains(Component component);
	
	/**
	 * Operation returns subcomponents of itself.
	 * @return
	 */
	public Vector<QuantifiedComponent> getDirectParts();
	
	/**
	 * Operation returns the number of Materials needed for this Component.
	 * @return
	 */
	public int getNumberOfMaterials();
	
	/**
	 * Operation returns the name of the Component itself.
	 * @return
	 */
	public String getName();
	
	/**
	 * Operation returns the price of the Component itself.
	 * @return
	 */
	public int getPrice();
	
	/**
	 * Operation returns a Vector of all Materials needed for this component.
	 * @return
	 */
	public Materialmap getMaterialList();
	
	/**
	 * Operation returns the complete price of the Component itself, including all subcomponents.
	 * @return
	 */
	public int getOverallPrice();
	
	/**
	 * Operation sets the price of the Component itself.
	 * @param newPrice
	 */
	public void setPrice(int newPrice);
}
