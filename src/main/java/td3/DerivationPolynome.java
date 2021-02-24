package td3;

public class DerivationPolynome extends OperationUnaire {

	public DerivationPolynome(ExpressionArithmetique ea) {
		super(ea);
	}

	@Override
	public ExpressionArithmetique simplifie(DerivationPolynome dp) throws Exception {
		return dp.deriver(); 
	}

	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		if (ea instanceof Derivable) {
			this.ea = ((Derivable) this.ea).deriver();
		}
		return this.ea.calculer(map);
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) {
		return 0;
	}

	public ExpressionArithmetique deriver() throws Exception {
		if (ea instanceof Derivable) {
			return ((Derivable) this.ea).deriver().simplifier();
		}
		return this; 
	}

	public ExpressionArithmetique deriver(int i) throws Exception {
		for(int j=0;j<i;j++) {
			if (ea instanceof Derivable) {
				ea = ((Derivable) this.ea).deriver().simplifier();
			}
		}
		return this.ea;
	}

	@Override
	public String toString() {
		return ea.toString();
	}

	
}
