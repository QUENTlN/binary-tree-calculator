package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.ExpressionArithmetique;
import td3.Multiplication;
import td3.Soustraction;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class MultiplicationTest {
	
	
	@Test
	public void multiplicationTestCalculer() throws Exception {

		VariableSymbolique x= new VariableSymbolique("x");
    	VariableSymbolique y= new VariableSymbolique("y");
    	VariableSymbolique z= new VariableSymbolique("z");
    	List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
    	listeTest.add(x);
    	listeTest.add(y);
    	listeTest.add(z);
    	
    	ExpressionArithmetique valeur1= new ConstEntiere(2);
    	ExpressionArithmetique valeur2= new ConstEntiere(4);
    	ExpressionArithmetique valeur3= new ConstEntiere(5);
    	List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
    	listeTest2.add(valeur1);
    	listeTest2.add(valeur2);
    	listeTest2.add(valeur3);
    	
    	VariableSymboliqueMapping test = new VariableSymboliqueMapping(listeTest,listeTest2);
		
    	ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Multiplication mult = new Multiplication(neuf,deux);
		assertEquals(18,mult.calculer(test),0.001);
		
		ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Multiplication mult2 = new Multiplication(neuf,UnSurDeux);
		assertEquals(4.5,mult2.calculer(test),0.001);
		
		Multiplication mult3 = new Multiplication(x,neuf);
		assertEquals(18,mult3.calculer(test),0.001);
		
		ConstRationnelle TroisSurQuatre = new ConstRationnelle(3,4);
		Multiplication mult4 = new Multiplication(TroisSurQuatre,UnSurDeux);
		assertEquals(0.375,mult4.calculer(test),0.001);

		Multiplication mult5 = new Multiplication(neuf,y);
		assertEquals(36,mult5.calculer(test),0.001);
		
		Multiplication mult6 = new Multiplication(TroisSurQuatre,z);
		assertEquals(3.75,mult6.calculer(test),0.001);
		
		Multiplication mult7 = new Multiplication(z,x);
		assertEquals(10,mult7.calculer(test),0.001);
	}
	
	@Test
	public void multiplicationTestSimplifier() throws Exception {

		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Multiplication mult = new Multiplication(neuf,deux);
		assertEquals("18",mult.simplifier().toString());
		
		ConstRationnelle unSurDeux = new ConstRationnelle(1,2);
		Multiplication mult2 = new Multiplication(neuf,unSurDeux);
		assertEquals("(9/2)",mult2.simplifier().toString());
		
		VariableSymbolique varSymb = new VariableSymbolique("x");
		Multiplication minus4 = new Multiplication(neuf,varSymb);
		assertEquals("(9*x)",minus4.simplifier().toString());
		
		Multiplication mult3a = new Multiplication(unSurDeux,neuf);
		assertEquals("(9/2)",mult3a.simplifier().toString());
		
		Multiplication mult3 = new Multiplication(unSurDeux,unSurDeux);
		assertEquals("(1/4)",mult3.simplifier().toString());
		
		ConstRationnelle QuaranteSurQuinze = new ConstRationnelle(40,15);
		Multiplication mult5 = new Multiplication(QuaranteSurQuinze,varSymb);
		assertEquals("((8/3)*x)",mult5.simplifier().toString());
		
		ConstEntiere moinsDeux = new ConstEntiere(-2);
		Multiplication mult6 = new Multiplication(moinsDeux,neuf);
		assertEquals("-18",mult6.simplifier().toString());
		
		Multiplication minus3 = new Multiplication(unSurDeux,neuf);
		assertEquals("(9/2)",minus3.simplifier().toString());
		
		Addition neufPlusDeux = new Addition(neuf,deux);
		Multiplication minus9 = new Multiplication(neuf,neufPlusDeux);
		assertEquals("99",minus9.simplifier().toString());

		Multiplication minus10 = new Multiplication(neufPlusDeux,neuf);
		assertEquals("99",minus10.simplifier().toString());
		
		Multiplication minus20= new Multiplication(neuf,neuf);
		assertEquals("81",minus20.simplifier().toString());
		
		
	}

}
