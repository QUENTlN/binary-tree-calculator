package td3;

public abstract class OperationUnaire implements ExpressionArithmetique {

	protected ExpressionArithmetique ea;

	public OperationUnaire(ExpressionArithmetique ea) {
		this.ea = ea;
	}

	protected ExpressionArithmetique simplifie(ExpressionArithmetique ea) {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstEntiere valeur) throws ValeurNegativeOuNulleSousLaRacineException, Exception {
		return this;
	}

	protected ExpressionArithmetique simplifie(ConstRationnelle valeur) throws DivisionParZeroException, ValeurNegativeOuNulleSousLaRacineException, Exception {
		return this;
	}

	protected ExpressionArithmetique simplifie(Multiplication valeur) throws LogarithmeNeperienNegatifOuNulException, Exception {
		return this;
	}

	protected ExpressionArithmetique simplifie(Addition valeur) {
		return this;
	}

	protected ExpressionArithmetique simplifie(Soustraction valeur) {
		return this;
	}

	protected ExpressionArithmetique simplifie(Division valeur) throws DivisionParZeroException, Exception {
		return this;
	}
	
	protected ExpressionArithmetique simplifie(ConstSymbolique valeur) throws LogarithmeNeperienNegatifOuNulException, Exception {
		return this;
	}

	protected ExpressionArithmetique simplifie(DerivationPolynome valeur) throws Exception {
		return this;
	}

	public ExpressionArithmetique simplifier() throws Exception {
		ExpressionArithmetique res;

		this.ea = this.ea.simplifier();

		if (this instanceof DerivationPolynome) {
			res = simplifie((DerivationPolynome) this);
		} else if (this.ea instanceof ConstEntiere) {
			ConstEntiere valeur = (ConstEntiere) this.ea;
			res = simplifie(valeur);
		} else if (this.ea instanceof ConstRationnelle) {
			ConstRationnelle valeur = (ConstRationnelle) this.ea;
			res = simplifie(valeur);
		} else if (this.ea instanceof VariableSymbolique) {
			VariableSymbolique valeur = (VariableSymbolique) this.ea;
			res = simplifie(valeur);
		} else if (this.ea instanceof Addition) {
			Addition add = (Addition) this.ea;
			res = simplifie(add);
		} else if (this.ea instanceof Multiplication) {
			Multiplication mult = (Multiplication) this.ea;
			res = simplifie(mult);
		} else if (this.ea instanceof Soustraction) {
			Soustraction sous = (Soustraction) this.ea;
			res = simplifie(sous);
		} else if (this.ea instanceof Division) {
			Division div = (Division) this.ea;
			res = simplifie(div);
		} else if (this.ea instanceof Puissance) {
			Puissance puis = (Puissance) this.ea;
			res = simplifie(puis);
		} else if (this.ea instanceof RacineCarree) {
			RacineCarree rac = (RacineCarree) this.ea;
			res = simplifie(rac);
		} else if (this.ea instanceof Cosinus) {
			Cosinus cos = (Cosinus) this.ea;
			res = simplifie(cos);
		} else if (this.ea instanceof Sinus) {
			Sinus sin = (Sinus) this.ea;
			res = simplifie(sin);
		} else if (this.ea instanceof LogarithmeNeperien) {
			LogarithmeNeperien ln = (LogarithmeNeperien) this.ea;
			res = simplifie(ln);
		} else if (this.ea instanceof ConstSymbolique) {
			ConstSymbolique cs = (ConstSymbolique) this.ea;
			res = simplifie(cs);
		} else {
			return this;
		}

		return res;

	}

	public ExpressionArithmetique simplifier(int i) throws Exception {

		ExpressionArithmetique exp = this.ea;

		if (ea instanceof OperationBinaire) {
			exp = ((OperationBinaire) ea).simplifier(i);
		} else if (ea instanceof VariableSymboliqueIndexee) {
			exp = ((VariableSymboliqueIndexee) ea).simplifier(i);
		} else if (ea instanceof VariableSymbolique) {
			exp = ((VariableSymbolique) ea).simplifier(i);
		} else if (ea instanceof OperationUnaire) {
			exp = ((OperationUnaire) ea).simplifier(i);
		}
		
		if (this instanceof DerivationPolynome) {
			return new DerivationPolynome(exp);
		} else if (this instanceof Cosinus) {
			return new Cosinus(exp);
		} else if (this instanceof Sinus) {
			return new Sinus(exp);
		} else if (this instanceof LogarithmeNeperien) {
			return new LogarithmeNeperien(exp);
		} else if (this instanceof RacineCarree) {
			return new RacineCarree(exp);
		} else {
			return this;
		}
	}

}