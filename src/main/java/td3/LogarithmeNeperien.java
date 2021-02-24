package td3;

import java.util.ArrayList;
import java.util.List;

public class LogarithmeNeperien extends OperationUnaire {

	
	public LogarithmeNeperien(ExpressionArithmetique ea) throws Exception {
		super(ea);
		
		if(ea instanceof ConstEntiere && ((ConstEntiere) ea).getEntier()<=0) { 
			throw new LogarithmeNeperienNegatifOuNulException(); 
		}else if(ea instanceof ConstRationnelle) {
			if(((ConstRationnelle) ea).getNumerateur() <=0 || ((ConstRationnelle) ea).getDenominateur()<=0) {
				throw new LogarithmeNeperienNegatifOuNulException();
			}
		}
	}



	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere valeur) throws Exception {
		if (valeur.getEntier() == 1) {
			return new ConstEntiere(0);
		}
		return new LogarithmeNeperien(valeur);
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstSymbolique valeur) throws Exception {
		if (valeur instanceof Pi) {
			return new LogarithmeNeperien(valeur);
		} else {
			return new ConstEntiere(1);
		}
	}

	@Override
	protected ExpressionArithmetique simplifie(Multiplication mult) throws Exception {

		return new Addition(new LogarithmeNeperien(mult.getEaLeft()), new LogarithmeNeperien(mult.getEaRight()));
	}

	@Override
	protected ExpressionArithmetique simplifie(Division div) throws Exception {
		if (div.getEaLeft() instanceof ConstEntiere && ((ConstEntiere) div.getEaLeft()).getEntier() == 1) {
			return new Multiplication(new ConstEntiere(-1), new LogarithmeNeperien(div.getEaRight()));
		} else {
			return new Soustraction(new LogarithmeNeperien(div.getEaLeft()), new LogarithmeNeperien(div.getEaRight()));
		}
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		return Math.log(ea.calculer(map));

	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		return Math.log(ea.calculer(map, i));
	}

	@Override
	public String toString() {
		return "log(" + ea.toString() + ")";
	}
}
