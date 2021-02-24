package poo;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.RacineCarree;
import td3.ValeurNegativeOuNulleSousLaRacineException;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class RacineCarreeTest {

	@Test
	public void RacineCarreeSimplifierTest() throws Exception {
		
		ConstEntiere neuf=new ConstEntiere(9);
		RacineCarree squareRoot0=new RacineCarree(neuf);
		assertEquals("3",squareRoot0.simplifier().toString());
		
		ConstEntiere centQuarante=new ConstEntiere(140);
		RacineCarree squareRoot1=new RacineCarree(centQuarante);
		assertEquals("(2*√(35))",squareRoot1.simplifier().toString());
		
		ConstRationnelle huitSurDeux= new ConstRationnelle(1,2);
		RacineCarree squareRoot2=new RacineCarree(huitSurDeux);
		assertEquals("(√(1)/√(2))", squareRoot2.simplifier().toString());
		
		VariableSymbolique x= new VariableSymbolique("x");
		RacineCarree squareRoot3=new RacineCarree(x);
		assertEquals("√(x)",squareRoot3.simplifier().toString());
		
	}
	
	@Test
	public void RacineCarreeCalculerTest() throws Exception {
		
		List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
		VariableSymbolique x= new VariableSymbolique("x");
    	listeTest.add(x);
    	
    	List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
    	ExpressionArithmetique valeur1= new ConstEntiere(25);
    	listeTest2.add(valeur1);
    	
    	VariableSymboliqueMapping map = new VariableSymboliqueMapping(listeTest,listeTest2);
    	
		ConstEntiere neuf=new ConstEntiere(9);
		RacineCarree racineneuf = new RacineCarree(neuf);
		assertEquals(3, racineneuf.calculer(map), 0.001);
		
		ConstRationnelle huitSurDeux= new ConstRationnelle(8,2);
		assertEquals(2, new RacineCarree(huitSurDeux).calculer(map), 0.001);
		
		assertEquals(5, new RacineCarree(x).calculer(map), 0.001);
	}
	
	
	@Test
	public void RacineCarreeToStringTest() throws Exception {
		ConstEntiere deux = new ConstEntiere(2);
		assertEquals("√(2)",new RacineCarree(deux).toString());
		
		ConstRationnelle sixSurTrois= new ConstRationnelle(6,3);
		assertEquals("√((6/3))",new RacineCarree(sixSurTrois).toString());
		
		VariableSymbolique y= new VariableSymbolique("y");
		assertEquals("√(y)",new RacineCarree(y).toString());
		
	}
}
