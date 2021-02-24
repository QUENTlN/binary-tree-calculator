package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ConstSymbolique;
import td3.Exponentielle;
import td3.ExpressionArithmetique;
import td3.Pi;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class ConstSymboliqueTest {
	
	
	@Test
	public void testConstSymbolique() {
		
		
		VariableSymbolique x= new VariableSymbolique("x");
		List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
		listeTest.add(x);
		ExpressionArithmetique valeur1= new ConstEntiere(1);
        List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
        listeTest2.add(valeur1);
        VariableSymboliqueMapping map = new VariableSymboliqueMapping(listeTest,listeTest2);
        
        
        ConstSymbolique pi= new Pi();
		assertEquals("(π)", pi.simplifier().toString());
		assertEquals(3.14159265359, pi.calculer(map), 0.001);
		assertEquals("(π)", (pi.toString()));
		
		Exponentielle exponentielle= new Exponentielle();
		assertEquals("(e)", exponentielle.simplifier().toString());
		assertEquals(2.71828, exponentielle.calculer(map), 0.001);
		assertEquals("(e)", (exponentielle.toString()));
	}
}
