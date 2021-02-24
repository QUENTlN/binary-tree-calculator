package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.Division;
import td3.DivisionParZeroException;
import td3.LogarithmeNeperienNegatifOuNulException;
import td3.Exponentielle;
import td3.ExpressionArithmetique;
import td3.LogarithmeNeperien;
import td3.Multiplication;
import td3.Pi;
import td3.Somme;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class LogarithmeNeperienTest {
	
	@Test
	public void logarithmeNeperienTestSimplifier() throws Exception {
		ConstEntiere un = new ConstEntiere(1);
		LogarithmeNeperien logDeUn = new LogarithmeNeperien(un);
		assertEquals("0",logDeUn.simplifier().toString());
		
		
		Pi pi = new Pi();
		LogarithmeNeperien logDePi = new LogarithmeNeperien(pi);
		assertEquals("log((π))",logDePi.simplifier().toString());
		
		Exponentielle exp = new Exponentielle();
		LogarithmeNeperien logDeExp = new LogarithmeNeperien(exp);
		assertEquals("1",logDeExp.simplifier().toString());
		
		ConstEntiere trois = new ConstEntiere(3);
		ConstEntiere cinq = new ConstEntiere(5);
		Multiplication mult = new Multiplication(trois,cinq);
		LogarithmeNeperien logTroisFoisCinq = new LogarithmeNeperien(mult);
		assertEquals("log(15)",logTroisFoisCinq.simplifier().toString());
		
		VariableSymbolique var = new VariableSymbolique("x");
		Multiplication mult2 = new Multiplication(trois,var);
		LogarithmeNeperien logTroisFoisVar = new LogarithmeNeperien(mult2);
		assertEquals("(log(3)+log(x))",logTroisFoisVar.simplifier().toString());
		
		ConstRationnelle unTiers = new ConstRationnelle(1,3);
		LogarithmeNeperien logDeUnTiers = new LogarithmeNeperien(unTiers);
		assertEquals("log((1/3))",logDeUnTiers.simplifier().toString());
		
		ConstRationnelle quaranteSurQuinze = new ConstRationnelle(40,15);
		LogarithmeNeperien logDeQuaranteSurQuinze = new LogarithmeNeperien(quaranteSurQuinze);
		assertEquals("log((8/3))",logDeQuaranteSurQuinze.simplifier().toString());
		
		Division troisDiviseParX = new Division(trois,var);
		LogarithmeNeperien logDeTroisDiviseParX = new LogarithmeNeperien(troisDiviseParX);
		assertEquals("(log(3)-log(x))",logDeTroisDiviseParX.simplifier().toString());
		
		
		Division unDiviseParX = new Division(un,var);
		LogarithmeNeperien logDeUnDiviseParX = new LogarithmeNeperien(unDiviseParX);
		assertEquals("(-1*log(x))",logDeUnDiviseParX.simplifier().toString());
		
	}
	
	
	@Test
	public void logarithmeNeperienTestCalculer() throws Exception {
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
		
		ConstEntiere un = new ConstEntiere(1);
		LogarithmeNeperien logDeUn = new LogarithmeNeperien(un);
		assertEquals(0,logDeUn.calculer(test),0.001);
		
		ConstEntiere trois = new ConstEntiere(3);
		LogarithmeNeperien logDeTrois = new LogarithmeNeperien(trois);
		assertEquals(1.098,logDeTrois.calculer(test),0.001);
		
		Pi pi = new Pi();
		LogarithmeNeperien logDePi = new LogarithmeNeperien(pi);
		assertEquals(1.1447,logDePi.calculer(test),0.0001);
		
		Exponentielle exp = new Exponentielle();
		LogarithmeNeperien logDeExp = new LogarithmeNeperien(exp);
		assertEquals(1,logDeExp.calculer(test),0.001);
		
		
		ConstEntiere cinq = new ConstEntiere(5);
		Multiplication mult = new Multiplication(trois,cinq);
		LogarithmeNeperien logTroisFoisCinq = new LogarithmeNeperien(mult);
		assertEquals(2.708,logTroisFoisCinq.calculer(test),0.001);
		
		
		Multiplication mult2 = new Multiplication(trois,x);
		LogarithmeNeperien logTroisFoisX = new LogarithmeNeperien(mult2);
		assertEquals(1.791,logTroisFoisX.calculer(test),0.001);
		
		ConstRationnelle unTiers = new ConstRationnelle(1,3);
		LogarithmeNeperien logDeUnTiers = new LogarithmeNeperien(unTiers);
		assertEquals(-1.099,logDeUnTiers.calculer(test),0.001);
		
		
		Division troisDiviseParX = new Division(trois,x);
		LogarithmeNeperien logDeTroisDiviseParX = new LogarithmeNeperien(troisDiviseParX);
		assertEquals(0.405,logDeTroisDiviseParX.calculer(test),0.001);
		
		
		Division unDiviseParX = new Division(un,x);
		LogarithmeNeperien logDeUnDiviseParX = new LogarithmeNeperien(unDiviseParX);
		assertEquals(-0.693,logDeUnDiviseParX.calculer(test),0.001);
		
		
	}
	
	@Test
	public void logarithmeNeperienTestToString() throws Exception {
		ConstEntiere un = new ConstEntiere(1);
		LogarithmeNeperien logDeUn = new LogarithmeNeperien(un);
		assertEquals("log(1)",logDeUn.toString());
		
		
		Pi pi = new Pi();
		LogarithmeNeperien logDePi = new LogarithmeNeperien(pi);
		assertEquals("log((π))",logDePi.toString());
		
		Exponentielle exp = new Exponentielle();
		LogarithmeNeperien logDeExp = new LogarithmeNeperien(exp);
		assertEquals("log((e))",logDeExp.toString());
		
		ConstEntiere trois = new ConstEntiere(3);
		ConstEntiere cinq = new ConstEntiere(5);
		Multiplication mult = new Multiplication(trois,cinq);
		LogarithmeNeperien logTroisFoisCinq = new LogarithmeNeperien(mult);
		assertEquals("log((3*5))",logTroisFoisCinq.toString());
		
		VariableSymbolique var = new VariableSymbolique("x");
		Multiplication mult2 = new Multiplication(trois,var);
		LogarithmeNeperien logTroisFoisVar = new LogarithmeNeperien(mult2);
		assertEquals("log((3*x))",logTroisFoisVar.toString());
		
		ConstRationnelle unTiers = new ConstRationnelle(1,3);
		LogarithmeNeperien logDeUnTiers = new LogarithmeNeperien(unTiers);
		assertEquals("log((1/3))",logDeUnTiers.toString());
		
		ConstRationnelle quaranteSurQuinze = new ConstRationnelle(40,15);
		LogarithmeNeperien logDeQuaranteSurQuinze = new LogarithmeNeperien(quaranteSurQuinze);
		assertEquals("log((40/15))",logDeQuaranteSurQuinze.toString());
		
		Division troisDiviseParX = new Division(trois,var);
		LogarithmeNeperien logDeTroisDiviseParX = new LogarithmeNeperien(troisDiviseParX);
		assertEquals("log((3/x))",logDeTroisDiviseParX.toString());
		
		
		Division unDiviseParX = new Division(un,var);
		LogarithmeNeperien logDeUnDiviseParX = new LogarithmeNeperien(unDiviseParX);
		assertEquals("log((1/x))",logDeUnDiviseParX.toString());
	}
	
	@Test
	public void logarithmeNeperienTestCalculer2() throws Exception {
		
    	VariableSymboliqueMapping  map= new VariableSymboliqueMapping();
    	VariableSymbolique i = new VariableSymbolique("i");
    	LogarithmeNeperien logDeI = new LogarithmeNeperien(i);
    	Somme sommeLogDeI = new Somme (logDeI,i,new ConstEntiere(1),new ConstEntiere(4));
    	assertEquals(3.17805,sommeLogDeI.calculer(map,i),0.00001);
	}
	
}
