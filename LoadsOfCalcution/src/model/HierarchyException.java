package model;

public class HierarchyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1984128087816321414L;
	private final static String errorMessage = "Hierarchy shall be guaranteed";

	private HierarchyException(String message) {
		super(message);
	}

	public static HierarchyException create(String message) {
		return new HierarchyException(message);
	}

	public static String getErrormessage() {
		return errorMessage;
	}
}
