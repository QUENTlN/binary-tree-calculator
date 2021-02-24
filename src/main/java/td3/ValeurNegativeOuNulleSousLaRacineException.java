package td3;

public class ValeurNegativeOuNulleSousLaRacineException extends Exception {

	public ValeurNegativeOuNulleSousLaRacineException() {
		super("La valeur sous la racine ne peut pas être négative ou nul");
	}
}
