package td3;

import java.util.HashMap;

public class VariableSymbolique implements ExpressionArithmetique, Derivable {

	public final String nomVariableSymbolique;

	public VariableSymbolique(String nomVariableSymbolique) {
		this.nomVariableSymbolique = nomVariableSymbolique;
	}

	public String getNomVariableSymbolique() {
		return this.nomVariableSymbolique;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	@Override
	public ExpressionArithmetique simplifier(int i) {
		if (this.nomVariableSymbolique == "i") {
			return new ConstEntiere(i);
		}
		return this;
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		ExpressionArithmetique value = (ExpressionArithmetique) map.getMapVariableSymbolique().get(this);
		return value.calculer(map);
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		return ((ExpressionArithmetique) map.getMapVariableSymbolique().get(this)).calculer(map);
	}

	@Override
	public ExpressionArithmetique deriver() {
		return new ConstEntiere(1);
	}

	@Override
	public String toString() {
		return nomVariableSymbolique;
	}
}
