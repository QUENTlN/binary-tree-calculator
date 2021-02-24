package td3;

public class Distribution implements ExpressionArithmetique {

	protected ExpressionArithmetique ea;

	public Distribution(ExpressionArithmetique ea) {
		this.ea = ea;
	}

	@Override
	public ExpressionArithmetique simplifier() throws Exception {
		if ((this.ea instanceof Multiplication) && (((OperationBinaire) this.ea).getEaRight() instanceof Distribuable)) {
			Multiplication mult = (Multiplication) this.ea;

			if (mult.getEaRight() instanceof Addition) {
				if ((((OperationBinaire) mult.getEaRight()).getEaRight() instanceof Distribuable)) {
					Multiplication mult2 = new Multiplication(mult.getEaLeft(),((OperationBinaire) mult.getEaRight()).getEaRight());
					Distribution dist = new Distribution(mult2);
					return new Addition(new Multiplication(mult.getEaLeft(), ((OperationBinaire) mult.getEaRight()).getEaLeft()).simplifier(),dist.simplifier());
				} else if ((((OperationBinaire) mult.getEaRight()).getEaLeft() instanceof Distribuable)) {
					Multiplication mult2 = new Multiplication(mult.getEaLeft(),((OperationBinaire) mult.getEaRight()).getEaLeft());
					Distribution dist = new Distribution(mult2);
					return new Addition(dist.simplifier(),new Multiplication(mult.getEaLeft(), ((OperationBinaire) mult.getEaRight()).getEaRight()).simplifier());
				}

				else {
					return new Addition(
							new Multiplication(mult.getEaLeft(), ((OperationBinaire) mult.getEaRight()).getEaLeft()).simplifier(),
							new Multiplication(mult.getEaLeft(), ((OperationBinaire) mult.getEaRight()).getEaRight()).simplifier());
				}
			} else {
				if ((((OperationBinaire) mult.getEaRight()).getEaRight() instanceof Distribuable)) {
					Multiplication mult2 = new Multiplication(mult.getEaLeft(),((OperationBinaire) mult.getEaRight()).getEaRight());
					Distribution dist = new Distribution(mult2);
					return new Soustraction(new Multiplication(mult.getEaLeft(), ((OperationBinaire) mult.getEaRight()).getEaLeft()).simplifier(),dist.simplifier());
				} else if ((((OperationBinaire) mult.getEaRight()).getEaLeft() instanceof Distribuable)) {
					Multiplication mult2 = new Multiplication(mult.getEaLeft(),((OperationBinaire) mult.getEaRight()).getEaLeft());
					Distribution dist = new Distribution(mult2);
					return new Soustraction(dist.simplifier(),new Multiplication(mult.getEaLeft(), ((OperationBinaire) mult.getEaRight()).getEaRight()).simplifier());
				}

				else {
					return new Soustraction(
							new Multiplication(mult.getEaLeft(), ((OperationBinaire) mult.getEaRight()).getEaLeft()).simplifier(),
							new Multiplication(mult.getEaLeft(), ((OperationBinaire) mult.getEaRight()).getEaRight()).simplifier());
				}

			}

		} else {
			return this;
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
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		this.ea = this.ea.simplifier();
		return this.ea.calculer(map);
	}

	@Override
	public String toString() {
		return "(" + ea.toString() + ")";
	}
}
