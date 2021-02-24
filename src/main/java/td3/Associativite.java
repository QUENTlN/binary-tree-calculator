package td3;

public class Associativite implements ExpressionArithmetique {

	protected ExpressionArithmetique ea;

	public Associativite(ExpressionArithmetique ea) {
		this.ea = ea;
	}

	@Override
	public ExpressionArithmetique simplifier() throws Exception, NonAssociatifException {
		if (this.ea instanceof OperationAssociative) {
			if ((this.ea instanceof Addition) && (((OperationBinaire) this.ea).getEaRight() instanceof Addition)) {
				Addition add = (Addition) this.ea;
				if ((((OperationBinaire) add.getEaRight()).getEaRight() instanceof Addition)) {
					Addition add1 = new Addition(add.getEaLeft(), ((OperationBinaire) add.getEaRight()).getEaLeft());
					Addition add2 = new Addition(add1, ((OperationBinaire) add.getEaRight()).getEaRight());
					Associativite asso = new Associativite(add2);
					return asso.simplifier();
				}

				else if ((((OperationBinaire) add.getEaRight()).getEaLeft() instanceof Addition)) {
					Addition add1 = new Addition(add.getEaLeft(), ((OperationBinaire) add.getEaRight()).getEaRight());
					Addition add2 = new Addition(add1, ((OperationBinaire) add.getEaRight()).getEaLeft());
					Associativite asso = new Associativite(add2);
					return asso.simplifier();
				}

				else {
					return new Addition(new Addition(add.getEaLeft(), ((OperationBinaire) add.getEaRight()).getEaLeft())
							.simplifier(), ((OperationBinaire) add.getEaRight()).getEaRight()).simplifier();
				}
			} else if ((this.ea instanceof Multiplication)
					&& (((OperationBinaire) this.ea).getEaRight() instanceof Multiplication)) {
				Multiplication add = (Multiplication) this.ea;
				if ((((OperationBinaire) add.getEaRight()).getEaRight() instanceof Multiplication)) {
					Multiplication add1 = new Multiplication(add.getEaLeft(),
							((OperationBinaire) add.getEaRight()).getEaLeft());
					Multiplication add2 = new Multiplication(add1, ((OperationBinaire) add.getEaRight()).getEaRight());
					Associativite asso = new Associativite(add2);
					return asso.simplifier();
				}

				else if ((((OperationBinaire) add.getEaRight()).getEaLeft() instanceof Multiplication)) {
					Multiplication add1 = new Multiplication(add.getEaLeft(),
							((OperationBinaire) add.getEaRight()).getEaRight());
					Multiplication add2 = new Multiplication(add1, ((OperationBinaire) add.getEaRight()).getEaLeft());
					Associativite asso = new Associativite(add2);
					return asso.simplifier();
				}

				else {
					return new Multiplication(
							new Multiplication(add.getEaLeft(), ((OperationBinaire) add.getEaRight()).getEaLeft())
									.simplifier(),
							((OperationBinaire) add.getEaRight()).getEaRight()).simplifier();
				}
			} else {
				return this;
			}
		} else {
			throw new NonAssociatifException();
			//return this;
		}
	}

	@Override
	public ExpressionArithmetique simplifier(int i) {
		return null;
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		this.ea = this.ea.simplifier();
		return this.ea.calculer(map);
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) {
		return 0;
	}

	@Override
	public String toString() {
		return "(" + ea.toString() + ")";
	}
}