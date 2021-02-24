package td3;

public abstract class OperationQuaternaire implements ExpressionArithmetique {

	protected ConstEntiere indiceFin;
	protected ConstEntiere indiceDebut;
	protected VariableSymbolique i;
	protected ExpressionArithmetique terme;

	public OperationQuaternaire(ExpressionArithmetique terme, VariableSymbolique i, ConstEntiere indiceDebut,
			ConstEntiere indiceFin) {
		this.terme = terme;
		this.i = i;
		this.indiceDebut = indiceDebut;
		this.indiceFin = indiceFin;
	}

	@Override
	public ExpressionArithmetique simplifier() throws Exception {
		this.terme = terme.simplifier();
		return simplifie(terme, i, indiceDebut, indiceFin);
	}

	@Override
	public ExpressionArithmetique simplifier(int i) throws Exception {
		return this.simplifier();
	}

	protected ExpressionArithmetique simplifie(VariableSymboliqueIndexee terme, VariableSymbolique i,
			ConstEntiere indiceDebut, ConstEntiere indiceFin) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ExpressionArithmetique terme, VariableSymbolique i,
			ConstEntiere indiceDebut, ConstEntiere indiceFin) throws Exception {
		return this;
	}
}
