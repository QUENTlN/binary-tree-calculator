package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.ExpressionArithmetique;
import td3.Pi;
import td3.Addition;
import td3.ConstSymbolique;
import td3.Exponentielle;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class PiTest {

	@Test
	public void PiTestCalculer() throws Exception {
		
		VariableSymbolique x= new VariableSymbolique("x");
    	VariableSymbolique y= new VariableSymbolique("y");
    	VariableSymbolique z= new VariableSymbolique("z");
    	List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
    	listeTest.add(x);
    	listeTest.add(y);
   
    	ExpressionArithmetique valeur1= new ConstEntiere(2);
    	ExpressionArithmetique valeur2= new ConstEntiere(4);
    	List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
    	listeTest2.add(valeur1);
    	listeTest2.add(valeur2);
    	
    	VariableSymboliqueMapping test = new VariableSymboliqueMapping(listeTest,listeTest2);
    	
    	ConstSymbolique pi=new Pi(); 
    	
    	ConstEntiere neuf = new ConstEntiere(9);
		Addition plus = new Addition(neuf,pi);
		assertEquals(12.141,plus.calculer(test),0.001);
		
		ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Addition plus1 = new Addition(UnSurDeux,pi);
		assertEquals(3.641,plus1.calculer(test),0.001);
		
		Addition plus2 = new Addition(x,pi);
		assertEquals(5.141,plus2.calculer(test),0.001);
		
		
	}
	
	@Test
	public void piTestSimplifier() throws Exception {
		
    	
		VariableSymbolique x= new VariableSymbolique("x");
    	
		ConstSymbolique pi=new Pi(); 
		
    	ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Addition plus1 = new Addition(UnSurDeux,pi);
		assertEquals(("((1/2)+(π))"),plus1.simplifier().toString());
		
		Addition plus2 = new Addition(x,pi);
		assertEquals("(x+(π))",plus2.simplifier().toString());
    	
		ConstEntiere neuf = new ConstEntiere(9);
		Addition plus = new Addition(neuf,pi);
		assertEquals("(9+(π))",plus.simplifier().toString());
	}
	
	@Test
	public void piTestToString() {
		
		Pi pi = new Pi();
		assertEquals("(π)",pi.toString());
	}
}
