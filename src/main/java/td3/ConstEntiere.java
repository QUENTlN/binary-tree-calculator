package td3;

public class ConstEntiere implements ExpressionArithmetique, Derivable {

	private int entier;

	public ConstEntiere(int value) {
		this.entier = value;
	}

	public int getEntier() {
		return entier;
	}

	public void setEntier(int i) {
		this.entier = i;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	@Override
	public ExpressionArithmetique simplifier(int i) {
		return this;
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) {
		return this.getEntier();
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) {
		return this.calculer(map);
	}

	@Override
	public ExpressionArithmetique deriver() {
		return new ConstEntiere(0);
	}

	@Override
	public String toString() {
		return Integer.toString(entier);
	}
}
