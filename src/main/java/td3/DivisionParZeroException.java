package td3;

public class DivisionParZeroException extends Exception {

	public DivisionParZeroException() {
		super("La division par 0 n'est pas supporté");
	}
}
