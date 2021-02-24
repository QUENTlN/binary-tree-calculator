package td3;

import java.util.ArrayList;
import java.util.List;

public class RacineCarree extends OperationUnaire {

	public RacineCarree(ExpressionArithmetique ea) throws Exception {
		super(ea);
		if((ea instanceof ConstEntiere) && ((ConstEntiere) ea).getEntier()<=0 ) {
			throw new ValeurNegativeOuNulleSousLaRacineException();
		}else if(ea instanceof ConstRationnelle) {
			if(((ConstRationnelle) ea).getNumerateur() <=0 || ((ConstRationnelle) ea).getDenominateur()<=0) {
				throw new ValeurNegativeOuNulleSousLaRacineException();
			}

		}

	}

	

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere valeur) throws Exception {
		int racineAuDebut = valeur.getEntier();

		if (getCarre(racineAuDebut) != racineAuDebut) {
			return new ConstEntiere(getCarre(racineAuDebut));
		}

		for (int i = 2; i < racineAuDebut; i++) {

			if ((racineAuDebut % i) != 0) {
				continue;
			}
			int div = racineAuDebut / i;

			int carree = getCarre(div);

			if (carree != div) {
				return new Multiplication(new ConstEntiere(carree), new RacineCarree(new ConstEntiere(i)));
			}
		}
		return new RacineCarree(new ConstEntiere(racineAuDebut));
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle valeur) throws Exception {
		int denominateur = valeur.getDenominateur();
		int numerateur = valeur.getNumerateur();
		return new Division(simplifie(new ConstEntiere(numerateur)), simplifie(new ConstEntiere(denominateur)));
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		
			return Math.sqrt( ea.calculer(map));
		
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
			return Math.sqrt(ea.calculer(map, i));
		
	}

	private int getCarre(int result) {
		for (int i = 0; i < result; i++) {
			if (i * i == result) {
				return i;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "√(" + ea.toString() + ")";
	}
}
