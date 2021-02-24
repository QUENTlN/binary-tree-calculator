package td3;

public class Matrice implements ExpressionMatricielle {
	private ExpressionArithmetique[][] matrice;
	
	public Matrice(ExpressionArithmetique[][] matrice) {
		this.matrice = matrice;
	}
	
	public ExpressionArithmetique[][] getMatrice(){
		return this.matrice;
	}
	
	public Matrice simplifier() throws Exception {
		Matrice mat = new Matrice(this.getMatrice());
		for(int i = 0; i < this.getMatrice().length; i++) {
            for (int j = 0; j < this.getMatrice()[0].length; j++) {
            	mat.getMatrice()[i][j] = this.getMatrice()[i][j].simplifier();
            }
        }
		return mat;
	}
	
	public double[][] calculer(VariableSymboliqueMapping map) throws Exception {
		double[][] mat = new double[this.getMatrice().length][this.getMatrice()[0].length];
		for(int i = 0; i < this.getMatrice().length; i++) {
            for (int j = 0; j < this.getMatrice()[0].length; j++) {
            	mat[i][j] = this.getMatrice()[i][j].calculer(map);
            }
        }
		return mat;
	}
	
	@Override
	public String toString() {
		StringBuilder strB = new StringBuilder();
		for(int i = 0; i < this.getMatrice().length; i++) {
        	strB.append("[");
            for (int j = 0; j < this.getMatrice()[0].length; j++) {
            	strB.append(this.getMatrice()[i][j]);
            	if (j != this.getMatrice()[0].length - 1) {
                	strB.append(", ");
            	}
            }
        	strB.append("]\n");
        }
		return strB.toString();
	}
}
