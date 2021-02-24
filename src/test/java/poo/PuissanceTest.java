package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.Puissance;
import td3.Soustraction;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class PuissanceTest {
	
	@Test
	public void puissanceTestCalculer() throws Exception {
		
		//verifier derivee 2^2 = 0
		
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
		Puissance pow = new Puissance(neuf,deux);
		assertEquals(81,pow.calculer(test),0.001);
		
		ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Puissance pow2 = new Puissance(neuf,UnSurDeux);
		assertEquals(3,pow2.calculer(test),0.001);
		
		Puissance pow3 = new Puissance(UnSurDeux,neuf);
		assertEquals(0.001,pow3.calculer(test),0.001);
		
		ConstRationnelle TroisSurQuatre = new ConstRationnelle(3,4);
		Puissance pow4 = new Puissance(TroisSurQuatre,UnSurDeux);
		assertEquals(0.866,pow4.calculer(test),0.001);

		Puissance pow5 = new Puissance(neuf,y);
		assertEquals(6561,pow5.calculer(test),0.001);
		
		Puissance pow6 = new Puissance(TroisSurQuatre,z);
		assertEquals(0.237,pow6.calculer(test),0.001);
		
		Puissance pow7 = new Puissance(z,x);
		assertEquals(25,pow7.calculer(test),0.001);
	}
	
	@Test
	public void puissanceTestSimplifier() throws Exception {

		VariableSymbolique x= new VariableSymbolique("x");
		VariableSymbolique y= new VariableSymbolique("y");
		List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
    	listeTest.add(x);
    	listeTest.add(y);
    	ExpressionArithmetique valeur1= new ConstEntiere(2);
    	ExpressionArithmetique valeur2= new ConstEntiere(4);
    	List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
    	listeTest2.add(valeur1);
    	listeTest2.add(valeur2);
    	VariableSymboliqueMapping test = new VariableSymboliqueMapping(listeTest,listeTest2);
    	
		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Puissance pow = new Puissance(neuf,deux);
		assertEquals("(9^2)",pow.simplifier().toString());
		
		ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Puissance pow2 = new Puissance(neuf,UnSurDeux);
		assertEquals("(9^(1/2))",pow2.simplifier().toString());
		
		ConstRationnelle TroisSurQuatre = new ConstRationnelle(3,4);
		Puissance pow3 = new Puissance(TroisSurQuatre,UnSurDeux);
		assertEquals("((3^(1/2))/(4^(1/2)))",pow3.simplifier().toString());
		
		Puissance pow4 = new Puissance(neuf,x);
		assertEquals("(9^x)",pow4.simplifier().toString());
		
		ConstRationnelle QuaranteSurQuinze = new ConstRationnelle(40,15);
		Puissance pow5 = new Puissance(x,QuaranteSurQuinze);
		assertEquals("(x^(8/3))",pow5.simplifier().toString());
		
		Puissance pow6 = new Puissance(UnSurDeux,neuf);
		assertEquals("((1^9)/(2^9))",pow6.simplifier().toString());
		
		Puissance pow7 = new Puissance(x,y);
		assertEquals("(x^y)",pow7.simplifier().toString());
		
		Puissance pow8 = new Puissance(QuaranteSurQuinze,x);
		assertEquals("((8^x)/(3^x))",pow8.simplifier().toString());
		
		Puissance pow9 = new Puissance(x,neuf);
		assertEquals("(x^9)",pow9.simplifier().toString());
	}
	
	@Test
	public void puissanceTestToString() throws DivisionParZeroException {

		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Puissance pow = new Puissance(neuf,deux);
		assertEquals("(9^2)",pow.toString());
		
		ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Puissance pow2 = new Puissance(neuf,UnSurDeux);
		assertEquals("(9^(1/2))",pow2.toString());
		
		VariableSymbolique varSymb = new VariableSymbolique("x");
		Puissance pow3 = new Puissance(neuf,varSymb);
		assertEquals("(9^x)",pow3.toString());
		
		Puissance pow4 = new Puissance(UnSurDeux,varSymb);
		assertEquals("((1/2)^x)",pow4.toString());
	
		
	}

}
