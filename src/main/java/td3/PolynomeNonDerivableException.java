package td3;

public class PolynomeNonDerivableException extends Exception {

	public PolynomeNonDerivableException() {
		super("Ce polynôme ne peut pas être dérivé");
	}
}
