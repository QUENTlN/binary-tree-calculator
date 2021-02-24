package td3;

public class Produit extends OperationQuaternaire {

	public Produit(ExpressionArithmetique terme, VariableSymbolique i, ConstEntiere indiceDebut,
			ConstEntiere indiceFin) {
		super(terme, i, indiceDebut, indiceFin);
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique terme, VariableSymbolique i,
			ConstEntiere indiceDebut, ConstEntiere indiceFin) throws Exception {
		ExpressionArithmetique produit = new ConstEntiere(0);
		for (int j = indiceDebut.getEntier(); j <= indiceFin.getEntier(); j++) {
			if (j == indiceDebut.getEntier()) {
				produit = terme.simplifier(j);
			} else {
				produit = new Multiplication(produit, terme.simplifier(j));
			}
		}
		return produit;
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		int produit = 1;
		for (map.getMapVariableSymbolique().put(this.i,new ConstEntiere(this.indiceDebut.getEntier())); ((ConstEntiere) map.getMapVariableSymbolique().get(i)).getEntier() <= this.indiceFin.getEntier(); map.getMapVariableSymbolique().replace(this.i,new ConstEntiere(((ConstEntiere) map.getMapVariableSymbolique().get(i)).getEntier() + 1))) {
			produit *= terme.calculer(map, this.i);
		}
		return produit;
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		return this.calculer(map);
	}
}
