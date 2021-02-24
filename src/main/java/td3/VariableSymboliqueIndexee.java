package td3;

import java.util.Map;

public class VariableSymboliqueIndexee extends VariableSymbolique {

	private int index;

	public VariableSymboliqueIndexee(int index) {
		super("a");
		this.index = index;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	private int getIndex() {
		return this.index;
	}

	public ExpressionArithmetique simplifier(int i) {
		return new VariableSymboliqueIndexee(i);
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		ExpressionArithmetique value = (ExpressionArithmetique) map.getMapVariableSymbolique().get(this);
		return value.calculer(map);
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		for (Map.Entry<VariableSymbolique, ExpressionArithmetique> entry : ((Map<VariableSymbolique, ExpressionArithmetique>) map
				.getMapVariableSymbolique()).entrySet()) {
			if (entry.getKey() instanceof VariableSymboliqueIndexee && ((VariableSymboliqueIndexee) entry.getKey())
					.getIndex() == ((ExpressionArithmetique) map.getMapVariableSymbolique().get(i)).calculer(map)) {
				return entry.getValue().calculer(map);
			}
		}
		return 0; // exeption
	}

	@Override
	public String toString() {
		return "a" + index;
	}
}
