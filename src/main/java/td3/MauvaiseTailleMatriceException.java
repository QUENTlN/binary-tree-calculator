package td3;

public class MauvaiseTailleMatriceException extends Exception {
	
	public MauvaiseTailleMatriceException() {
		super("La matrice n'est pas de bonne taille");
	}
}
