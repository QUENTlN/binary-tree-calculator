package td3;

public class EgaliteEquation {

	public static boolean egalite(ExpressionArithmetique ex1, ExpressionArithmetique ex2) throws Exception {
		return (ex1.simplifier().toString().equals(ex2.simplifier().toString()));
	}
}
