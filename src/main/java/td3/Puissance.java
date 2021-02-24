package td3;

public class Puissance extends OperationBinaire implements OperationAvecElementNeutre, Derivable {

	public Puissance(ExpressionArithmetique ea, ExpressionArithmetique ea2) {
		super(ea, ea2);
	}

	@Override
	public String getElementNeutre() {
		return "1";
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) throws Exception {
		return new Division(new Puissance(new ConstEntiere(gauche.getNumerateur()), droite),
				new Puissance(new ConstEntiere(gauche.getDenominateur()), droite));
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) throws Exception {
		return new Division(new Puissance(new ConstEntiere(gauche.getNumerateur()), droite),
				new Puissance(new ConstEntiere(gauche.getDenominateur()), droite));
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, VariableSymbolique droite) throws Exception {
		return new Division(new Puissance(new ConstEntiere(gauche.getNumerateur()), droite),
				new Puissance(new ConstEntiere(gauche.getDenominateur()), droite));
	}

	@Override
	protected ExpressionArithmetique simplifie(VariableSymbolique gauche, ConstEntiere droite) {
		if (droite.getEntier() == 0) {
			ExpressionArithmetique un = new ConstEntiere(1);
			return un;
		}
		if (droite.getEntier() == 1) {
			return gauche;
		}
		return simplifie(droite, gauche);
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstSymbolique gauche, ConstEntiere droite) {
		if (gauche instanceof Exponentielle) {
			if (droite.getEntier() == 0) {
				return new ConstEntiere(1);
			} else if (droite.getEntier() == 1) {
				return new Exponentielle();

			} else {
				return new Puissance(gauche, droite);
			}
		} else {
			return new Puissance(gauche, droite);
		}
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		return Math.pow(this.eaLeft.calculer(map), this.eaRight.calculer(map));
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		return Math.pow(this.eaLeft.calculer(map, i), this.eaRight.calculer(map, i));
	}

	@Override
	public ExpressionArithmetique deriver() throws PolynomeNonDerivableException,Exception {
		if (this.getEaLeft() instanceof Derivable && this.getEaRight() instanceof Derivable) {
			if ((this.getEaLeft() instanceof VariableSymbolique) && ((this.getEaRight() instanceof ConstEntiere)
					|| (this.getEaRight() instanceof ConstRationnelle))) {
				ExpressionArithmetique un = new ConstEntiere(1);
				return new Multiplication(this.getEaRight(),
						new Puissance(this.getEaLeft(), new Soustraction(this.getEaRight(), un).simplifier()));
			} else if (((this.getEaRight() instanceof ConstEntiere) || (this.getEaRight() instanceof ConstRationnelle))
					&& ((this.getEaLeft() instanceof ConstEntiere) || (this.getEaLeft() instanceof ConstRationnelle))) {
				return new ConstEntiere(0);
			}else {
				throw new PolynomeNonDerivableException();
			}
		}else {
			throw new PolynomeNonDerivableException();
		}// J'ai changé ici -isuri
	}

	@Override
	public String toString() {
		return "(" + eaLeft.toString() + "^" + eaRight.toString() + ")";
	}
}
