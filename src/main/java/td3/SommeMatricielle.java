package td3;

public class SommeMatricielle extends OperationMatricielle {
	
	public SommeMatricielle(ExpressionMatricielle eaMLeft, ExpressionMatricielle eaMRight) {
		super(eaMLeft,eaMRight);
	}
	
	@Override
	protected Matrice simplifie(Matrice gauche,Matrice droite) throws Exception {
		
		if (gauche.getMatrice().length == droite.getMatrice().length && gauche.getMatrice()[0].length == droite.getMatrice()[0].length) {
			 //throw new MauvaiseTailleMatriceException();
		}
		
        ExpressionArithmetique[][] res = new ExpressionArithmetique[gauche.getMatrice().length][gauche.getMatrice()[0].length];
        for(int i = 0; i < gauche.getMatrice().length; i++) {
            for (int j = 0; j < gauche.getMatrice()[0].length; j++) {
            	res[i][j] = (new Addition(gauche.getMatrice()[i][j], droite.getMatrice()[i][j])).simplifier();
            }
        }
        return new Matrice(res);
	}

	
	public double[][] calculer(VariableSymboliqueMapping map) throws Exception {
		double[][] gauche = this.getEaMLeft().calculer(map);
		double[][] droite = this.getEaMRight().calculer(map);
		
		if (gauche.length == droite.length && gauche[0].length == droite[0].length) {
			 //throw new MauvaiseTailleMatriceException();
		}
		
		double[][] res = new double[gauche.length][gauche[0].length];
        for(int i = 0; i < gauche.length; i++) {
            for (int j = 0; j < gauche[0].length; j++) {
            	res[i][j] = gauche[i][j] + droite[i][j];
            }
        }
        return res;
	}

}
