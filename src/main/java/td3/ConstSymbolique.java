package td3;

public abstract class ConstSymbolique implements ExpressionArithmetique {

	private final String nomConstSymbolique;
	private final double valeur;

	public ConstSymbolique(String parNom, double parValeur) {
		this.nomConstSymbolique = parNom;
		this.valeur = parValeur;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		if (this instanceof Pi) {
			return new Pi();
		} else {
			return new Exponentielle();
		}
	}

	@Override
	public ExpressionArithmetique simplifier(int i) {
		return this.simplifier();
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) {
		return this.valeur;
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) {
		return this.valeur;
	}

	public String toString() {
		return "(" + this.nomConstSymbolique + ")";
	}
}
