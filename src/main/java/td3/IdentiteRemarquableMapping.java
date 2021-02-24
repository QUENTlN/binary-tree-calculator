package td3;

import java.util.HashMap;
import java.util.Map;

public class IdentiteRemarquableMapping {

	Map<ExpressionArithmetique, ExpressionArithmetique> mapIdentiteRemarquable = new HashMap<>();

	public IdentiteRemarquableMapping() {

		VariableSymbolique a = new VariableSymbolique("a");
		ConstEntiere deux = new ConstEntiere(2);
		Puissance aCarre = new Puissance(a, deux);

		VariableSymbolique b = new VariableSymbolique("b");
		Puissance bCarre = new Puissance(b, deux);

		Multiplication deuxAB = new Multiplication(deux, new Multiplication(a, b));
		Addition identiteRemarquableDeveloppeeAddition = new Addition(aCarre, new Addition(deuxAB, bCarre));
		Puissance identiteRemarquableFactoriseeAddition = new Puissance(new Addition(a, b), deux);
		this.mapIdentiteRemarquable.put(identiteRemarquableDeveloppeeAddition, identiteRemarquableFactoriseeAddition);

		Soustraction identiteRemarquableDeveloppeeSoustraction = new Soustraction(aCarre, new Addition(deuxAB, bCarre));
		Puissance identiteRemarquableFactoriseeSoustraction = new Puissance(new Soustraction(a, b), deux);
		this.mapIdentiteRemarquable.put(identiteRemarquableDeveloppeeSoustraction,identiteRemarquableFactoriseeSoustraction);

		Soustraction aCarreMoinsBCarre = new Soustraction(aCarre, bCarre);
		Addition aPlusB = new Addition(a, b);
		Soustraction aMoinsB = new Soustraction(a, b);
		Multiplication id = new Multiplication(aPlusB, aMoinsB);
		this.mapIdentiteRemarquable.put(aCarreMoinsBCarre, id);
	}

	
	public ExpressionArithmetique getIdentiteRemarquable(ExpressionArithmetique ea) {
		ExpressionArithmetique formeFactorisee = null;
		for (ExpressionArithmetique key : this.mapIdentiteRemarquable.keySet()) {
			if (key.toString().equals(ea.toString())) {
				formeFactorisee = mapIdentiteRemarquable.get(key);
			}
		}
		return formeFactorisee;
	}

}