package td3;

public interface ExpressionArithmetique {
	public ExpressionArithmetique simplifier() throws Exception;

	public ExpressionArithmetique simplifier(int i) throws Exception;

	public double calculer(VariableSymboliqueMapping map) throws Exception;

	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception;

	public String toString();
}
