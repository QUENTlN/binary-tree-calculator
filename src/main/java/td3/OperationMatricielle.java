package td3;

import java.util.Arrays;

public abstract class OperationMatricielle implements ExpressionMatricielle {
	private ExpressionMatricielle eaMLeft;
	private ExpressionMatricielle eaMRight;

	protected OperationMatricielle(ExpressionMatricielle eaMLeft, ExpressionMatricielle eaMRight) {
		this.eaMLeft = eaMLeft;
		this.eaMRight = eaMRight;
	}

	protected abstract Matrice simplifie(Matrice gauche, Matrice droite) throws Exception;

	public ExpressionMatricielle getEaMLeft() {
		return this.eaMLeft;
	}

	public ExpressionMatricielle getEaMRight() {
		return this.eaMRight;
	}

	public Matrice simplifier() throws Exception {
		Matrice gauche = (Matrice) this.eaMLeft.simplifier();
		Matrice droite = (Matrice) this.eaMRight.simplifier();
		return simplifie(gauche, droite);
	}

	public abstract double[][] calculer(VariableSymboliqueMapping map) throws Exception;

	public static String affichercalcul(double[][] matRes) {
		StringBuilder strB = new StringBuilder();
		for (double[] ligne : matRes) {
			strB.append(Arrays.toString(ligne) + "\n");
		}
		return strB.toString();
	}
}
