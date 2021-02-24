package td3;

public class NonAssociatifException extends Exception{

	public NonAssociatifException() {
		super("Cette expression n'est pas associative");
	}
}
