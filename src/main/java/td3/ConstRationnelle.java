package td3;

public final class ConstRationnelle implements ExpressionArithmetique, Derivable {

	private final int numerateur;
	private final int denominateur;

	public ConstRationnelle(int num, int denom) throws DivisionParZeroException {
		this.numerateur = num;
		this.denominateur = denom;
		if(denom==0) {
			throw new DivisionParZeroException();
		}
	}

	public int getNumerateur() {
		return numerateur;
	}

	public int getDenominateur() {
		return denominateur;
	}

	@Override
	public ExpressionArithmetique simplifier() throws DivisionParZeroException {
		if (this.numerateur % this.denominateur == 0) {
			return new ConstEntiere(numerateur / this.denominateur);
		}
		int pgcd = gcd(this.numerateur, this.denominateur);
		return new ConstRationnelle(this.numerateur / pgcd, this.denominateur / pgcd);
	}

	@Override
	public ExpressionArithmetique simplifier(int i) throws DivisionParZeroException {
		return this.simplifier();
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) {
		return (double) this.getNumerateur() / this.getDenominateur();
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) {
		return this.calculer(map);
	}

	@Override
	public ExpressionArithmetique deriver() {
		return new ConstEntiere(0);
	}

	private static int gcd(int a, int b) {
		if (b == 0)// qd le reste = 0 je peux plus rediviser on a trouvé le pgcd
			return a;// a c le pgcd au final
		else
			return gcd(b, a % b);
	}

	@Override
	public String toString() {
		return "(" + numerateur + "/" + denominateur + ")";
	}
}
