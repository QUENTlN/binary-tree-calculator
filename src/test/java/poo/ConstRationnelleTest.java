package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class ConstRationnelleTest {
	

	@Test
	public void testConstRationnelle() throws DivisionParZeroException {
	VariableSymbolique x= new VariableSymbolique("x");
	List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
	listeTest.add(x);
	ExpressionArithmetique valeur1= new ConstEntiere(1);
    List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
    listeTest2.add(valeur1);
    VariableSymboliqueMapping map = new VariableSymboliqueMapping(listeTest,listeTest2);

	ConstRationnelle neufSurDeux = new ConstRationnelle(9,2);
	ConstRationnelle QuaranteSurSeize = new ConstRationnelle(40,16);

	assertEquals("(9/2)", (neufSurDeux.toString()));
	assertEquals(9, neufSurDeux.getNumerateur());
	assertEquals(2, neufSurDeux.getDenominateur());
	assertEquals("2.5", (String.valueOf(QuaranteSurSeize.calculer(map))));
	assertEquals("(5/2)", QuaranteSurSeize.simplifier().toString());
	
	ConstRationnelle quatreSurDeux = new ConstRationnelle(4,2);
	assertEquals("2", quatreSurDeux.simplifier().toString());
	
	VariableSymbolique var = new VariableSymbolique("i");
	assertEquals("2",quatreSurDeux.simplifier(1).toString());
	assertEquals(2,quatreSurDeux.calculer(map,var),0.0001);
	
	}
	
}
