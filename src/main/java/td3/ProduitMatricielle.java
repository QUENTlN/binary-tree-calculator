package td3;

public class ProduitMatricielle extends OperationMatricielle {

	public ProduitMatricielle(ExpressionMatricielle eaMLeft, ExpressionMatricielle eaMRight) {
		super(eaMLeft, eaMRight);
	}

	@Override
	protected Matrice simplifie(Matrice gauche, Matrice droite) throws Exception {
		ExpressionArithmetique[][] res;

		int l, w;

		if (gauche.getMatrice()[0].length != droite.getMatrice().length) {
			throw new MauvaiseTailleMatriceException();
		}

		if (gauche.getMatrice().length * gauche.getMatrice()[0].length < droite.getMatrice().length
				* droite.getMatrice()[0].length) {
			l = droite.getMatrice().length;
			w = droite.getMatrice()[0].length;
		} else {
			l = gauche.getMatrice().length;
			w = gauche.getMatrice()[0].length;
		}

		res = new ExpressionArithmetique[l][w];

		l = 0;
		for (int i = 0; i < gauche.getMatrice().length; i++) {
			w = 0;
			for (int n = 0; n < droite.getMatrice()[0].length; n++) {

				ExpressionArithmetique calcul = new ConstEntiere(0);
				for (int m = 0; m < droite.getMatrice().length; m++) {
					calcul = (new Addition(calcul,
							new Multiplication(gauche.getMatrice()[i][m], droite.getMatrice()[m][n]))).simplifier();
				}
				res[l][w] = calcul;
				w++;
			}
			l++;
		}

		return new Matrice(res);
	}

	@Override
	public double[][] calculer(VariableSymboliqueMapping map) throws Exception {
		double[][] res;
		double[][] gauche = this.getEaMLeft().calculer(map);
		double[][] droite = this.getEaMRight().calculer(map);

		int l, w;

		if (gauche[0].length != droite.length) {
			throw new MauvaiseTailleMatriceException();
		}

		if (gauche.length * gauche[0].length < droite.length * droite[0].length) {
			l = droite.length;
			w = droite[0].length;
		} else {
			l = gauche.length;
			w = gauche[0].length;
		}

		res = new double[l][w];

		l = 0;
		for (int i = 0; i < gauche.length; i++) {
			w = 0;
			for (int n = 0; n < droite[0].length; n++) {

				double calcul = 0;
				for (int m = 0; m < droite.length; m++) {
					calcul += gauche[i][m] * droite[m][n];
				}
				res[l][w] = calcul;
				w++;
			}
			l++;
		}

		return res;
	}

}
