package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ExpressionArithmetique;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class VariableSymboliqueTest {
	@Test
	public void testVariableSymbolique() throws Exception {
		
	    
	    List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
	    VariableSymbolique x= new VariableSymbolique("x");
	    listeTest.add(x);
        
        List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
        ExpressionArithmetique valeur1= new ConstEntiere(2);
        listeTest2.add(valeur1);
        VariableSymboliqueMapping map = new VariableSymboliqueMapping(listeTest,listeTest2);
		

		assertEquals("x",x.getNomVariableSymbolique());
		assertEquals(x,x.simplifier());
		assertEquals(2, x.calculer(map),0.001);
		assertEquals("x",x.toString());
		assertEquals("x",x.getNomVariableSymbolique());
		assertEquals("1",x.deriver().toString());
		
		
	}

}
