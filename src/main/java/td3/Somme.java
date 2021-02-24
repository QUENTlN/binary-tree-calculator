package td3;

public class Somme extends OperationQuaternaire {

	public Somme(ExpressionArithmetique terme, VariableSymbolique i, ConstEntiere indiceDebut, ConstEntiere indiceFin) {
		super(terme, i, indiceDebut, indiceFin);
	}
	
	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique terme, VariableSymbolique i, ConstEntiere indiceDebut,
			ConstEntiere indiceFin) throws Exception {
		ExpressionArithmetique somme = new ConstEntiere(0);
		for (int j = indiceDebut.getEntier(); j <= indiceFin.getEntier(); j++) {
			if (j == indiceDebut.getEntier()) {
				somme = terme.simplifier(j);
			} else {
				somme = new Addition(somme, terme.simplifier(j));
			}
		}
		return somme;
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		double somme = 0;
		for (map.getMapVariableSymbolique().put(this.i,new ConstEntiere(this.indiceDebut.getEntier())); 
				((ConstEntiere) map.getMapVariableSymbolique().get(i)).getEntier() <= this.indiceFin.getEntier(); 
				map.getMapVariableSymbolique().replace(this.i,new ConstEntiere(((ConstEntiere) map.getMapVariableSymbolique().get(i)).getEntier() + 1))) {
			somme += terme.calculer(map, this.i);
		}
		return somme;
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		return this.calculer(map);
	}
}
