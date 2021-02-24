package td3;

import java.util.ArrayList;
import java.util.List;

public class Division extends OperationBinaire implements OperationAvecElementNeutre {

	public Division(ExpressionArithmetique eaLeft, ExpressionArithmetique eaRight) throws Exception { // 
		super(eaLeft, eaRight);
		
		 if (eaRight instanceof ConstEntiere && ((ConstEntiere) eaRight).getEntier() == 0) {
			throw new DivisionParZeroException();
		}/*else if(eaRight instanceof VariableSymbolique) {
	    	List<VariableSymbolique> liste = new ArrayList<VariableSymbolique>();
			liste.add((VariableSymbolique)eaRight);
	    	List<ExpressionArithmetique> liste2 = new ArrayList<ExpressionArithmetique>();
	    	ExpressionArithmetique moinsSix= new ConstEntiere(-6);
	    	liste2.add(moinsSix);
	    	VariableSymboliqueMapping map = new VariableSymboliqueMapping(liste,liste2);
			if(((VariableSymbolique)(eaRight)).calculer(map)<=0) {
				throw new DivisionParZeroException();
			}
			
		}*/
	}
	


	@Override
	public String getElementNeutre() {
		return "1";
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstEntiere droite) throws DivisionParZeroException {
		return new ConstRationnelle(gauche.getNumerateur(), gauche.getDenominateur() * droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstRationnelle droite) throws DivisionParZeroException {
		return new ConstRationnelle(gauche.getEntier() * droite.getDenominateur(), droite.getNumerateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstRationnelle gauche, ConstRationnelle droite) throws DivisionParZeroException {
		return new ConstRationnelle(gauche.getNumerateur() * droite.getDenominateur(),
				gauche.getDenominateur() * droite.getNumerateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstEntiere gauche, ConstEntiere droite) throws DivisionParZeroException {
		return new ConstRationnelle(gauche.getEntier(), droite.getEntier()).simplifier();
	}
	
	@Override
	public double calculer(VariableSymboliqueMapping map) throws Exception {
		return this.eaLeft.calculer(map) / this.eaRight.calculer(map);
	}

	@Override
	public double calculer(VariableSymboliqueMapping map, VariableSymbolique i) throws Exception {
		return this.eaLeft.calculer(map, i) / this.eaRight.calculer(map, i);
	}

	@Override
	public String toString() {
		return "(" + eaLeft.toString() + "/" + eaRight.toString() + ")";
	}
}
