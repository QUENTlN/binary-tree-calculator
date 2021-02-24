package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.Cosinus;
import td3.DerivationPolynome;
import td3.ExpressionArithmetique;
import td3.LogarithmeNeperien;
import td3.Multiplication;
import td3.Pi;
import td3.Puissance;
import td3.RacineCarree;
import td3.Sinus;
import td3.Somme;
import td3.Soustraction;
import td3.VariableSymbolique;
import td3.VariableSymboliqueIndexee;
import td3.VariableSymboliqueMapping;

public class SommeTest {

	@Test
	public void sommeTestCalculer() throws Exception {

		VariableSymbolique x = new VariableSymboliqueIndexee(0);
		VariableSymbolique y = new VariableSymboliqueIndexee(1);
		VariableSymbolique z = new VariableSymboliqueIndexee(2);
		VariableSymbolique xx = new VariableSymbolique("x");
		List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
		listeTest.add(x);
		listeTest.add(y);
		listeTest.add(z);
		listeTest.add(xx);

		ExpressionArithmetique valeur1 = new ConstEntiere(2);
		ExpressionArithmetique valeur2 = new ConstEntiere(4);
		ExpressionArithmetique valeur3 = new ConstEntiere(5);
		ExpressionArithmetique valeur4 = new ConstEntiere(2);
		List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
		listeTest2.add(valeur1);
		listeTest2.add(valeur2);
		listeTest2.add(valeur3);
		listeTest2.add(valeur4);

		VariableSymboliqueMapping test = new VariableSymboliqueMapping(listeTest, listeTest2);

		VariableSymbolique i = new VariableSymbolique("i");
		VariableSymboliqueIndexee ai = new VariableSymboliqueIndexee(0);
		
		ConstEntiere zero = new ConstEntiere(0);
		ConstEntiere un = new ConstEntiere(1);
		ConstEntiere deux = new ConstEntiere(2);
		ConstEntiere trois = new ConstEntiere(3);
		Addition add = new Addition(i,ai);
		Soustraction sous = new Soustraction(add,xx);
		Cosinus cos = new Cosinus(add);
		LogarithmeNeperien ln = new LogarithmeNeperien(un);
		Sinus sin = new Sinus(xx);
		RacineCarree rc = new RacineCarree(sin);
		DerivationPolynome deriv = new DerivationPolynome(xx);
		
		
		Somme somme = new Somme(un, i, zero, trois);
		Somme somme1 = new Somme(add, i, zero, trois);
		Somme somme2 = new Somme(sous, i, zero, trois);
		Somme somme3 = new Somme(cos, i, zero, trois);
		Somme somme4 = new Somme(ln, i, un, trois);
		Somme somme5 = new Somme(i, i, zero, trois);
		Somme somme6 = new Somme(ai, i, zero, trois);
		Somme somme7 = new Somme(xx, i, zero, trois);
		Somme somme8 = new Somme(rc, i, zero, trois);
		
		assertEquals(4.0, somme.calculer(test), 0.1);
		assertEquals(17.0, somme1.calculer(test), 0.1);
		assertEquals(9.0, somme2.calculer(test), 0.1);
		assertEquals(-0.36857, somme3.calculer(test), 0.00001);
		assertEquals(0.0, somme4.calculer(test), 0.1);
		assertEquals(6.0, somme5.calculer(test), 0.1);
		assertEquals(11.0, somme6.calculer(test), 0.1);
		assertEquals(8.0, somme7.calculer(test), 0.1);
		assertEquals(3.81428, somme8.calculer(test),0.00001);
	}
	
	@Test
	public void sommeTestSimplifier() throws Exception {

		VariableSymbolique xx = new VariableSymbolique("x");
		VariableSymbolique i = new VariableSymbolique("i");
		VariableSymboliqueIndexee ai = new VariableSymboliqueIndexee(0);
		
		ConstEntiere zero = new ConstEntiere(0);
		ConstEntiere un = new ConstEntiere(1);
		ConstEntiere deux = new ConstEntiere(2);
		ConstEntiere trois = new ConstEntiere(3);
		Addition add = new Addition(i,ai);
		Soustraction sous = new Soustraction(add,xx);
		Cosinus cos = new Cosinus(add);
		LogarithmeNeperien ln = new LogarithmeNeperien(cos);
		Sinus sin = new Sinus(xx);
		RacineCarree rc = new RacineCarree(sin);
		DerivationPolynome deriv = new DerivationPolynome(xx);
		
		
		Somme somme = new Somme(un, i, zero, trois);
		Somme somme1 = new Somme(add, i, zero, trois);
		Somme somme2 = new Somme(sous, i, zero, trois);
		Somme somme3 = new Somme(cos, i, zero, trois);
		Somme somme4 = new Somme(ln, i, zero, trois);
		Somme somme5 = new Somme(i, i, zero, trois);
		Somme somme6 = new Somme(ai, i, zero, deux);
		Somme somme7 = new Somme(xx, i, zero, trois);
		Somme somme8 = new Somme(rc, i, zero, trois);
		
		assertEquals("(((1+1)+1)+1)", somme.simplifier().toString());
		assertEquals("((((0+a0)+(1+a1))+(2+a2))+(3+a3))",somme1.simplifier().toString());
		assertEquals("(((((0+a0)-x)+((1+a1)-x))+((2+a2)-x))+((3+a3)-x))",somme2.simplifier().toString());
		assertEquals("(((((cos(a0)*cos(a0))-(sin(a0)*sin(a0)))+((cos(a1)*cos(a0))-(sin(a1)*sin(a0))))+((cos(a2)*cos(a0))-(sin(a2)*sin(a0))))+((cos(a3)*cos(a0))-(sin(a3)*sin(a0))))", somme3.simplifier().toString());
		assertEquals("(((log(((cos(a0)*cos(a0))-(sin(a0)*sin(a0))))+log(((cos(a1)*cos(a0))-(sin(a1)*sin(a0)))))+log(((cos(a2)*cos(a0))-(sin(a2)*sin(a0)))))+log(((cos(a3)*cos(a0))-(sin(a3)*sin(a0)))))",somme4.simplifier().toString());
		assertEquals("(((0+1)+2)+3)",somme5.simplifier().toString());
		assertEquals("((a0+a1)+a2)",somme6.simplifier().toString());
		assertEquals("(((x+x)+x)+x)",somme7.simplifier().toString());
		assertEquals("(((√(sin(x))+√(sin(x)))+√(sin(x)))+√(sin(x)))",somme8.simplifier().toString());
	}
}
