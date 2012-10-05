package model;

public class PartMemberException extends Exception {

	private static final long serialVersionUID = 6061304311326103542L;
	private final static String message = "Is not member of part";

	public PartMemberException() {
		super(message);
	}

}
