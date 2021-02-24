package td3;

public class Multiplication extends OperationBinaire
		implements OperationAvecElementNeutre, Derivable, OperationCommutative, OperationAssociative {

	public Multiplication(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		super(eaLeft, eaRight);
	}

	@Override
	public String getElementNeutre() {
		return "1";
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) throws DivisionParZeroException {
		return new ConstRationnelle(droite.getEntier() * gauche.getNumerateur(), gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) throws DivisionParZeroException {
		return new ConstRationnelle(gauche.getNumerateur() * droite.getNumerateur(),
				droite.getDenominateur() * gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) {
		return new ConstEntiere(gauche.getEntier() * droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstRationnelle droite) throws Exception {
		return this.simplifie(droite, gauche).simplifier();
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		return this.eaLeft.calculer(map) * this.eaRight.calculer(map);
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		return this.eaLeft.calculer(map, i) * this.eaRight.calculer(map, i);
	}

	@Override
	public ExpressionArithmetique deriver() throws Exception {
		if ((this.getEaLeft() instanceof Derivable) && (this.getEaRight() instanceof Derivable)) {
			if (this.getEaRight() instanceof Puissance && (this.getEaLeft() instanceof ConstEntiere)
					|| (this.getEaLeft() instanceof ConstRationnelle)) {
				return new Multiplication(
						(new Multiplication(this.getEaLeft(), ((OperationBinaire) (this).getEaRight()).getEaRight()))
								.simplifier(),
						(new Puissance(((OperationBinaire) this.getEaRight()).getEaLeft(), new ConstEntiere(
								((ConstEntiere) ((OperationBinaire) this.getEaRight()).getEaRight()).getEntier() - 1)))
										.simplifier());
			} else if (((this.getEaRight() instanceof ConstEntiere) || (this.getEaRight() instanceof ConstRationnelle))
					&& this.getEaRight() instanceof Puissance) {
				return new Multiplication(
						(new Multiplication(this.getEaRight(), ((OperationBinaire) (this).getEaLeft()).getEaLeft()))
								.simplifier(),
						(new Puissance(((OperationBinaire) this.getEaLeft()).getEaRight(), new ConstEntiere(
								((ConstEntiere) ((OperationBinaire) this.getEaLeft()).getEaLeft()).getEntier() - 1)))
										.simplifier());

			} else if (((this.getEaLeft() instanceof ConstEntiere) || (this.getEaLeft() instanceof ConstRationnelle))
					&& (this.getEaRight() instanceof VariableSymbolique)) {
				return this.getEaLeft();

			} else if ((this.getEaLeft() instanceof VariableSymbolique) && ((this.getEaRight() instanceof ConstEntiere)
					|| (this.getEaRight() instanceof ConstRationnelle))) {
				return this.getEaRight();
			} else if (((this.getEaRight() instanceof ConstEntiere) || (this.getEaRight() instanceof ConstRationnelle))
					&& ((this.getEaLeft() instanceof ConstEntiere) || (this.getEaLeft() instanceof ConstRationnelle))) {
				return new ConstEntiere(0);
			} else {
				return this;
			}

		} else {
			return this;// a revoir si exception

		}
	}

	@Override
	public String toString() {
		return "(" + eaLeft.toString() + "*" + eaRight.toString() + ")";
	}
}
