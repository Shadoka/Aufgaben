package model.main;
import java.util.Vector;



public class Material extends ComponentCommon {


	private static final String UnstructuredMaterialMessage = "Materialien haben kein Struktur!";
	
	public static Material create(String name, int price) {
		return new Material(name, price);
	}
	public Material(String name, int price) {
		super(name, price);
	}
	public void addPart(ComponentCommon part, int amount) throws Exception {
		throw new Exception(UnstructuredMaterialMessage);
	}
	public boolean contains(Component component) {
		return false;
	}
	public Vector<QuantifiedComponent> getDirectParts() {
		return new Vector<QuantifiedComponent>();
	}
	public int getNumberOfMaterials() {
		return 1;
	}

	public Materialmap getMaterialList() {
		Materialmap result = Materialmap.createMaterialmap();
		result.put(this.getName(), QuantifiedComponent.createQuantifiedComponent(1, this));
		return result;
	}

	public int getOverallPrice() {
		return this.getPrice();
	}


}
