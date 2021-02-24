package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.DerivationPolynome;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.Multiplication;
import td3.Puissance;
import td3.Soustraction;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class DerivationPolynomeTest {

	@Test
	public void derivationTestCalculer() throws Exception {
		VariableSymbolique x= new VariableSymbolique("x");
		List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
		listeTest.add(x);
		ExpressionArithmetique valeur1= new ConstEntiere(2);
		List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
		listeTest2.add(valeur1);
	    VariableSymboliqueMapping map = new VariableSymboliqueMapping(listeTest,listeTest2);
	    ///4+x^4
	    ConstEntiere constEntiere1 = new ConstEntiere(4);
	    Puissance puiss1 = new Puissance(x,constEntiere1);
	    Addition add1 = new Addition(constEntiere1, puiss1);
		DerivationPolynome calculerPolynome = new DerivationPolynome(add1);
		assertEquals(32, calculerPolynome.calculer(map),0.00001);
	
	}
	
	@Test
	public void derivationTestDeriver() throws Exception {
		VariableSymbolique x= new VariableSymbolique("x");
		ConstEntiere constEntiere1 = new ConstEntiere(4);
	    Puissance puiss1 = new Puissance(x,constEntiere1);
	    Addition add1 = new Addition(constEntiere1, puiss1);
		DerivationPolynome simplifierPolynome = new DerivationPolynome(add1);
		assertEquals("(4*(x^3))", simplifierPolynome.deriver().toString());

		ConstRationnelle constRationnelle1 = new ConstRationnelle(1,2);
	    Puissance puiss2 = new Puissance(x,constEntiere1);
	    Soustraction minus1 = new Soustraction(constRationnelle1, puiss1);
		DerivationPolynome simplifierPolynome2 = new DerivationPolynome(minus1);
		assertEquals("(0-(4*(x^3)))", simplifierPolynome2.deriver().toString());
		
		/*ConstEntiere constEntiere2 = new ConstEntiere(2);
		ConstEntiere constEntiere3 = new ConstEntiere(3);
	    Puissance puiss3 = new Puissance(x,constEntiere2);
	    Multiplication mult1 = new Multiplication(constEntiere3, puiss3);
		ConstEntiere constEntiere4 = new ConstEntiere(5);
		Multiplication mult2 = new Multiplication(constEntiere4, x);
		ConstEntiere constEntiere5 = new ConstEntiere(10);
		Addition add2 = new Addition(new Addition(mult1, mult2), constEntiere5);
		DerivationPolynome simplifierPolynome3 = new DerivationPolynome(add2);
		assertEquals("((6*x)+5)", simplifierPolynome3.simplifier().toString());		
		*/
		    
	}
	
	@Test
	public void derivationTestDeriverOrdreN() throws Exception {
		VariableSymbolique x= new VariableSymbolique("x");
		ConstEntiere dix = new ConstEntiere(10);
		ConstEntiere cinq = new ConstEntiere(5);
		ConstEntiere quatre = new ConstEntiere(4);
		ConstEntiere trois = new ConstEntiere(3);
		ConstEntiere deux = new ConstEntiere(2);
		Puissance puiss = new Puissance(x,quatre);
		Puissance puiss2 = new Puissance(x,trois);
		Puissance puiss3 = new Puissance(x,deux);
		Multiplication mult = new Multiplication(cinq, puiss);
		Multiplication mult2 = new Multiplication(quatre, puiss2);
		Multiplication mult3 = new Multiplication(trois, puiss3);
		Multiplication mult4 = new Multiplication(cinq, x);
		Addition add = new Addition(mult,new Addition(mult2, new Addition(mult3,new Addition(mult4,dix))));
		DerivationPolynome deriverOrdreN = new DerivationPolynome(add);
		assertEquals("((5*(x^4))+((4*(x^3))+((3*(x^2))+((5*x)+10))))", deriverOrdreN.toString());
		assertEquals("((120*x)+24)", deriverOrdreN.deriver(3).toString());	

	}
	
	
}
