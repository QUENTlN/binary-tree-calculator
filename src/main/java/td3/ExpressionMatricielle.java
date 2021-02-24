package td3;

public interface ExpressionMatricielle {

	ExpressionMatricielle simplifier() throws Exception;

	double[][] calculer(VariableSymboliqueMapping map) throws Exception;
	
}
