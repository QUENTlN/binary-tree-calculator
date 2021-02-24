
package td3;

public class Addition extends OperationBinaire implements OperationAvecElementNeutre, Derivable, OperationCommutative,
		Factorisable, Distribuable, OperationAssociative {

	public Addition(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		super(eaLeft, eaRight);
	}

	@Override
	public String getElementNeutre() {
		return "0";
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) throws DivisionParZeroException {
		return new ConstRationnelle(gauche.getNumerateur() + droite.getEntier() * gauche.getDenominateur(),
				1 * gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) throws DivisionParZeroException {
		return new ConstRationnelle(
				gauche.getNumerateur() * droite.getDenominateur() + gauche.getDenominateur() * droite.getNumerateur(),
				droite.getDenominateur() * gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) {
		return new ConstEntiere(gauche.getEntier() + droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstRationnelle droite) throws DivisionParZeroException, Exception {
		return simplifie(droite, gauche).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(Multiplication gauche, Addition droite) throws Exception {
		ConstEntiere denominateurCommun;

		Puissance aCarre = (Puissance) ((OperationBinaire) this.eaLeft).getEaRight();
		ConstEntiere multiplicateurA = (ConstEntiere) ((OperationBinaire) this.eaLeft).getEaLeft();
		ConstEntiere multiplicateurB = (ConstEntiere) ((OperationBinaire) ((OperationBinaire) (this.eaRight))
				.getEaLeft()).getEaLeft();
		Multiplication aB = (Multiplication) ((Multiplication) ((OperationBinaire) this.getEaRight()).getEaLeft())
				.getEaRight();
		ConstEntiere multiplicateurC = (ConstEntiere) ((OperationBinaire) ((OperationBinaire) (this.eaRight))
				.getEaRight()).getEaLeft();
		Puissance bCarre = (Puissance) ((OperationBinaire) ((OperationBinaire) this.eaRight).getEaRight()).getEaRight();

		denominateurCommun = gcd(multiplicateurA, multiplicateurB);
		ConstEntiere denominateurCommun2 = gcd(denominateurCommun, multiplicateurC);
		ConstEntiere b = new ConstEntiere(
				((ConstEntiere) multiplicateurB).getEntier() / denominateurCommun2.getEntier());
		Multiplication bFoisAb = new Multiplication(b, aB);
		return new Multiplication(denominateurCommun2,
				(new Addition(aCarre, new Addition(bFoisAb, bCarre))).simplifier());

	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		return this.eaLeft.calculer(map) + this.eaRight.calculer(map);
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		return this.eaLeft.calculer(map, i) + this.eaRight.calculer(map, i);
	}

	@Override
	public ExpressionArithmetique deriver() throws Exception {
		if (eaLeft instanceof Derivable && eaRight instanceof Derivable) {
			return new Addition(((Derivable) eaLeft).deriver(), ((Derivable) eaRight).deriver());
		} else {
			return this;// a revoir si exception
		}
	}

	private static ConstEntiere gcd(ConstEntiere a, ConstEntiere b) {
		if (b.getEntier() == 0) {
			return a;
		} else {
			ConstEntiere b2 = new ConstEntiere(b.getEntier());
			ConstEntiere a2 = new ConstEntiere(a.getEntier() % b.getEntier());
			return gcd(b2, a2);
		}
	}

	@Override
	public String toString() {
		return "(" + eaLeft.toString() + "+" + eaRight.toString() + ")";
	}

	/*
	 * A simplifier
	 * 
	 * @Override protected ExpressionArithmetique simplifie(ConstEntiere gauche,
	 * Addition droite) { if(((droite.getEaLeft() instanceof VariableSymbolique)) &&
	 * ((droite.getEaRight() instanceof ConstEntiere) || (droite.getEaRight()
	 * instanceof ConstRationnelle))) { return new Addition((new
	 * Addition(gauche,droite.getEaRight())).simplifier(), droite.getEaLeft()); }
	 * else if((((droite.getEaRight() instanceof VariableSymbolique)) &&
	 * ((droite.getEaLeft() instanceof ConstEntiere) || (droite.getEaLeft()
	 * instanceof ConstRationnelle)))) { return new Addition((new Addition(gauche,
	 * droite.getEaLeft())).simplifier(),droite.getEaRight()); } else { return new
	 * Addition(gauche,droite); } }
	 */
}