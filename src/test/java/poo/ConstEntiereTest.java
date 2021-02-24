package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ExpressionArithmetique;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class ConstEntiereTest {

	@Test
	public void simpleSum() {
		VariableSymbolique x= new VariableSymbolique("x");
		List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
		listeTest.add(x);
		ExpressionArithmetique valeur1= new ConstEntiere(1);
        List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
        listeTest2.add(valeur1);
        VariableSymboliqueMapping map = new VariableSymboliqueMapping(listeTest,listeTest2);

        
		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		
		assertEquals(9, neuf.getEntier());
		assertEquals(2, deux.getEntier());
		

		assertEquals("9", neuf.toString());
		assertEquals("2", deux.toString());
		

		assertEquals(neuf, neuf.simplifier());
		assertEquals(deux, deux.simplifier());

		
		assertEquals(9, (int)neuf.calculer(map));
		assertEquals(2, (int)deux.calculer(map));
		
		neuf.setEntier(10);
		assertEquals("10", neuf.simplifier().toString());
		
		assertEquals("10", neuf.simplifier(1).toString());
		
		VariableSymbolique var = new VariableSymbolique("i");
		assertEquals(10, neuf.calculer(map,var),0.0001);
	}
}
