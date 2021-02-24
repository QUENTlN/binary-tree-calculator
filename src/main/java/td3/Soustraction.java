package td3;

public class Soustraction extends OperationBinaire
		implements OperationAvecElementNeutre, Derivable, Factorisable, Distribuable {

	public Soustraction(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) {
		super(eaLeft, eaRight);
	}

	@Override
	public String getElementNeutre() {
		return "0";
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) throws DivisionParZeroException {
		return new ConstRationnelle(gauche.getNumerateur() - (droite.getEntier() * gauche.getDenominateur()),
				gauche.getDenominateur() * 1).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) throws DivisionParZeroException {
		return new ConstRationnelle(
				gauche.getNumerateur() * droite.getDenominateur() - gauche.getDenominateur() * droite.getNumerateur(),
				droite.getDenominateur() * gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) {
		return new ConstEntiere(gauche.getEntier() - droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstRationnelle droite) throws DivisionParZeroException {
		return new ConstRationnelle((droite.getDenominateur() * gauche.getEntier()) - droite.getNumerateur() * 1,
				1 * droite.getDenominateur()).simplifier();
	}

	/* @Override
	protected ExpressionArithmetique simplifie(Puissance gauche, Addition droite) {
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
		return new Multiplication(denominateurCommun2, new Addition(new Soustraction(aCarre, new Multiplication(
				new ConstEntiere(((ConstEntiere) multiplicateurB).getEntier() / denominateurCommun2.getEntier()), aB)),
				bCarre));
	} */


	@Override
	protected ExpressionArithmetique simplifie(Multiplication gauche, Multiplication droite) {
		ConstEntiere facteur = (ConstEntiere) gauche.getEaLeft();
		VariableSymbolique a = (VariableSymbolique) ((OperationBinaire) gauche.getEaRight()).getEaLeft();
		VariableSymbolique b = (VariableSymbolique) ((OperationBinaire) droite.getEaRight()).getEaLeft();
		Multiplication aPlusBFoisAMoinsB = new Multiplication(new Addition(a, b), new Soustraction(a, b));
		Multiplication factoriser = new Multiplication(facteur, aPlusBFoisAMoinsB);
		return factoriser;
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
				(new Soustraction(aCarre, new Addition(bFoisAb, bCarre))).simplifier());

	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		return this.eaLeft.calculer(map) - this.eaRight.calculer(map);
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		return this.eaLeft.calculer(map, i) - this.eaRight.calculer(map, i);
	}

	@Override
	public ExpressionArithmetique deriver() throws Exception {
		if (eaLeft instanceof Derivable && eaRight instanceof Derivable) {
			return new Soustraction(((Derivable) eaLeft).deriver(), ((Derivable) eaRight).deriver());
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
		return "(" + eaLeft.toString() + "-" + eaRight.toString() + ")";
	}
}
