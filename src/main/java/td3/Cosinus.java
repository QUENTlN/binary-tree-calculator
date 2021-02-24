package td3;

import java.lang.Math;

public class Cosinus extends OperationUnaire {

	public Cosinus(ExpressionArithmetique ea) {
		super(ea);
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		
			return Math.cos( ea.calculer(map));
		
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		
			return Math.cos( ea.calculer(map, i));
		
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere valeur) {
		if (valeur.getEntier() == 0) {
			return new ConstEntiere(1);
		}
		return new Cosinus(valeur);
	}
	
	@Override
	protected ExpressionArithmetique simplifie(Addition add) {

		return new Soustraction(new Multiplication(new Cosinus(add.getEaLeft()), new Cosinus(add.getEaRight())),
				new Multiplication(new Sinus(add.getEaLeft()), new Sinus(add.getEaRight())));

	}

	@Override
	protected ExpressionArithmetique simplifie(Soustraction sous) {
		return new Addition(new Multiplication(new Cosinus(sous.getEaLeft()), new Cosinus(sous.getEaRight())),
				new Multiplication(new Sinus(sous.getEaLeft()), new Sinus(sous.getEaRight())));

	}

	@Override
	protected ExpressionArithmetique simplifie(Division div) throws Exception {
		if (div.getEaLeft() instanceof Pi && div.getEaRight() instanceof ConstEntiere) {
			if (((ConstEntiere) div.getEaRight()).getEntier() == 6) {
				return new Division(new RacineCarree(new ConstEntiere(3)), new ConstEntiere(2));
			} else if (((ConstEntiere) div.getEaRight()).getEntier() == 4) {
				return new Division(new RacineCarree(new ConstEntiere(2)), new ConstEntiere(2));
			} else if (((ConstEntiere) div.getEaRight()).getEntier() == 3) {
				return new ConstRationnelle(1, 2);
			} else if (((ConstEntiere) div.getEaRight()).getEntier() == 2) {
				return new ConstEntiere(0);
			} else {
				return new Cosinus(div.simplifier());
			}
		}
		return new Cosinus(div.simplifier());
	}

	@Override
	public String toString() {
		return "cos(" + ea.toString() + ")";
	}
}
