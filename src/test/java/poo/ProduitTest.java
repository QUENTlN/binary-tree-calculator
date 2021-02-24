package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.Cosinus;
import td3.DerivationPolynome;
import td3.Division;
import td3.ExpressionArithmetique;
import td3.LogarithmeNeperien;
import td3.Multiplication;
import td3.Produit;
import td3.Puissance;
import td3.RacineCarree;
import td3.Sinus;
import td3.Somme;
import td3.Soustraction;
import td3.VariableSymbolique;
import td3.VariableSymboliqueIndexee;
import td3.VariableSymboliqueMapping;

public class ProduitTest {

	@Test
	public void sommeTestCalculer() throws Exception {

		VariableSymbolique x = new VariableSymboliqueIndexee(0);
		VariableSymbolique y = new VariableSymboliqueIndexee(1);
		VariableSymbolique z = new VariableSymboliqueIndexee(2);
		VariableSymbolique z2 = new VariableSymboliqueIndexee(2);
		VariableSymbolique xx = new VariableSymbolique("x");
		List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
		listeTest.add(x);
		listeTest.add(y);
		listeTest.add(z);
		listeTest.add(z2);
		listeTest.add(xx);

		ExpressionArithmetique valeur1 = new ConstEntiere(2);
		ExpressionArithmetique valeur2 = new ConstEntiere(4);
		ExpressionArithmetique valeur3 = new ConstEntiere(5);
		ExpressionArithmetique valeur4 = new ConstEntiere(2);
		ExpressionArithmetique valeur5 = new ConstEntiere(6);
		List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
		listeTest2.add(valeur1);
		listeTest2.add(valeur2);
		listeTest2.add(valeur3);
		listeTest2.add(valeur4);
		listeTest2.add(valeur5);

		VariableSymboliqueMapping test = new VariableSymboliqueMapping(listeTest, listeTest2);

		VariableSymbolique i = new VariableSymbolique("i");
		VariableSymboliqueIndexee ai = new VariableSymboliqueIndexee(0);
		
		ConstEntiere zero = new ConstEntiere(0);
		ConstEntiere un = new ConstEntiere(1);
		ConstEntiere deux = new ConstEntiere(2);
		ConstEntiere trois = new ConstEntiere(3);
		Division add = new Division(i,ai);
		Puissance sous = new Puissance(add,xx);
		Cosinus cos = new Cosinus(add);
		LogarithmeNeperien ln = new LogarithmeNeperien(un);
		Sinus sin = new Sinus(xx);
		RacineCarree rc = new RacineCarree(sin);
		
		
		Produit produit = new Produit(un, i, un, trois);
		Produit produit1 = new Produit(add, i, un, trois);
		Produit produit2 = new Produit(sous, i, un, trois);
		Produit produit3 = new Produit(cos, i, un, trois);
		Produit produit4 = new Produit(ln, i, un, trois);
		Produit produit5 = new Produit(i, i, un, trois);
		Produit produit6 = new Produit(ai, i, un, trois);
		Produit produit7 = new Produit(xx, i, un, trois);
		Produit produit8 = new Produit(rc, i, un, trois);
		
		assertEquals(1.0, produit.calculer(test), 0.1);
		assertEquals(0.0, produit1.calculer(test), 0.1);
		assertEquals(0.0, produit2.calculer(test), 0.1);
		assertEquals(0.0, produit3.calculer(test), 0.1);
		assertEquals(0.0, produit4.calculer(test), 0.1);
		assertEquals(6.0, produit5.calculer(test), 0.1);
		assertEquals(0.0, produit6.calculer(test), 0.1);
		assertEquals(216.0, produit7.calculer(test), 0.1);
		assertEquals(0.0, produit8.calculer(test),0.1);
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
		Puissance add = new Puissance(i,ai);
		Division div = new Division(add,xx);
		Cosinus cos = new Cosinus(add);
		LogarithmeNeperien ln = new LogarithmeNeperien(cos);
		Sinus sin = new Sinus(xx);
		RacineCarree rc = new RacineCarree(sin);
		DerivationPolynome deriv = new DerivationPolynome(xx);
		
		
		Produit produit = new Produit(un, i, zero, trois);
		Produit produit1 = new Produit(add, i, zero, trois);
		Produit produit2 = new Produit(div, i, zero, trois);
		Produit produit3 = new Produit(cos, i, zero, trois);
		Produit produit4 = new Produit(ln, i, un, trois);
		Produit produit5 = new Produit(i, i, zero, trois);
		Produit produit6 = new Produit(ai, i, zero, trois);
		Produit produit7 = new Produit(xx, i, zero, trois);
		Produit produit8 = new Produit(rc, i, zero, trois);
		
		assertEquals("(((1*1)*1)*1)", produit.simplifier().toString());
		assertEquals("((((0^a0)*(1^a1))*(2^a2))*(3^a3))",produit1.simplifier().toString());
		assertEquals("(((((0^a0)/x)*((1^a1)/x))*((2^a2)/x))*((3^a3)/x))",produit2.simplifier().toString());
		assertEquals("(((cos((0^a0))*cos((1^a1)))*cos((2^a2)))*cos((3^a3)))", produit3.simplifier().toString());
		assertEquals("((log(cos((1^a1)))*log(cos((2^a2))))*log(cos((3^a3))))",produit4.simplifier().toString());
		assertEquals("(((0*1)*2)*3)",produit5.simplifier().toString());
		assertEquals("(((a0*a1)*a2)*a3)",produit6.simplifier().toString());
		assertEquals("(((x*x)*x)*x)",produit7.simplifier().toString());
		assertEquals("(((√(sin(x))*√(sin(x)))*√(sin(x)))*√(sin(x)))",produit8.simplifier().toString());
	}
	
}
