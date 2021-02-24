package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.ConstSymbolique;
import td3.Division;
import td3.ExpressionArithmetique;
import td3.Multiplication;
import td3.Pi;
import td3.RacineCarree;
import td3.Soustraction;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	@Test
	public void tests() throws Exception{
	VariableSymbolique x= new VariableSymbolique("x");
    VariableSymbolique y= new VariableSymbolique("y");
    VariableSymbolique z= new VariableSymbolique("z");
    VariableSymbolique t= new VariableSymbolique("t");
    
    List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
    listeTest.add(x);
    listeTest.add(y);
    listeTest.add(z);
    listeTest.add(t);
    ExpressionArithmetique valeur1= new ConstEntiere(2);
    ExpressionArithmetique valeur2= new ConstEntiere(4);
    ExpressionArithmetique valeur3= new ConstEntiere(1);
    
    List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
    listeTest2.add(valeur1);
    listeTest2.add(valeur2);
    listeTest2.add(valeur3);
    listeTest2.add(valeur3);
    VariableSymboliqueMapping map = new VariableSymboliqueMapping(listeTest,listeTest2);

	ConstEntiere neuf = new ConstEntiere(9);
	ConstEntiere deux = new ConstEntiere(2);
	Addition plus = new Addition(neuf,deux);
	assertEquals(11,plus.calculer(map),0.001);
	
	ConstRationnelle unSurDeux = new ConstRationnelle(1,2);
	Addition plus2 = new Addition(neuf,unSurDeux);
	assertEquals(9.5,plus2.calculer(map),0.001);
	
	Addition plus3 = new Addition(unSurDeux,neuf);
	assertEquals(9.5,plus3.calculer(map),0.001);
	
	ConstRationnelle troisSurQuatre = new ConstRationnelle(3,4);
	Addition plus4 = new Addition(troisSurQuatre,unSurDeux);
	assertEquals(1.25,plus4.calculer(map),0.001);
	
	ConstEntiere un = new ConstEntiere(1);
	Addition plus13 = new Addition (un,troisSurQuatre);
	assertEquals(1.7500,plus13.calculer(map),0.0001);
	
	ConstSymbolique pi = new Pi();
	Addition plus14 = new Addition (un,pi);
	assertEquals(4.1416,plus14.calculer(map),0.0001);
	
	Addition plus15 = new Addition (un,new Division (z,new Addition(t,un)));
	assertEquals(1.5,plus15.calculer(map),0.0001);

	
	Addition plus12 = new Addition(unSurDeux,unSurDeux);
	assertEquals(1.0,plus12.calculer(map),0.001);
	
	Addition plus5 = new Addition(x,y);
	assertEquals(6.0,plus5.calculer(map),0.001);
	
	
	Addition plus6 = new Addition(neuf, x);
	assertEquals(11.0,plus6.calculer(map),0.001);
	
	Addition plus7 = new Addition(x,neuf);
	assertEquals(11.0,plus7.calculer(map),0.001);
	
	Addition plus8 = new Addition(unSurDeux, y);
	assertEquals(4.5,plus8.calculer(map),0.001);
	
	Addition plus9 = new Addition(x, troisSurQuatre);
	assertEquals(2.75,plus9.calculer(map),0.001);
	
	ConstRationnelle vingtSurSeixe = new ConstRationnelle(20,16);
	Addition plus11 = new Addition(troisSurQuatre, vingtSurSeixe);
	assertEquals(2,plus11.calculer(map),0.001);
	}
	
	/*
	@Test
	public void testConstEntiere() {

		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);

		assertEquals(9, (neuf.getEntier()));
		assertEquals("2", (deux.toString()));
		assertEquals(2, ((ConstEntiere)deux.simplifier()).getEntier());
		assertEquals(9, (int)neuf.calculer());
	}
	
	@Test
	public void testConstRationnelle() {

		ConstRationnelle neufSurDeux = new ConstRationnelle(9,2);
		ConstRationnelle QuaranteSurSeize = new ConstRationnelle(40,16);

		assertEquals("9/2", (neufSurDeux.toString()));
		assertEquals(2.5, (QuaranteSurSeize.calculer()));
		assertEquals(2, ((ConstEntiere)deux.simplifier()).getEntier());
		assertEquals(9, neuf.calculer());
	}
	
	@Test
	public void simpleSum() {

		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Addition racine = new Addition(neuf, deux);

		assertEquals(11, ((ConstEntiere) racine.simplifier()).getEntier());

	}

	@Test
	public void classExample() {

		ExpressionArithmetique neuf = new ConstEntiere(9);
		ExpressionArithmetique deux = new ConstEntiere(2);
		ExpressionArithmetique trois = new ConstEntiere(3);
		ExpressionArithmetique cr = new ConstRationnelle(1, 17);

		ExpressionArithmetique plus = new Addition(neuf, deux);
		ExpressionArithmetique minus = new Soustraction(trois, cr);
		ExpressionArithmetique times = new Multiplication(plus, minus);

		

		assertEquals(550, ((ConstRationnelle) times.simplifier()).getNumerateur());
		assertEquals(17, ((ConstRationnelle) times.simplifier()).getDenominateur());

	}
	
	@Test
	public void exempleCalculer() {

		ExpressionArithmetique neuf = new ConstEntiere(9);
		ExpressionArithmetique deux = new ConstEntiere(2);
		ExpressionArithmetique trois = new ConstEntiere(3);
		ExpressionArithmetique cr = new ConstRationnelle(1, 17);

		ExpressionArithmetique plus = new Addition(neuf, deux);
		ExpressionArithmetique minus = new Soustraction(trois, cr);
		ExpressionArithmetique times = new Multiplication(plus, minus);

		ExpressionArithmetique results = new ConstRationnelle(550, 17);

		
		assertEquals(550/17.0, times.calculer(),0.00001);

	}
	
	@Test
	public void simplifierDivision() {

		ExpressionArithmetique neuf = new ConstEntiere(9);
		ExpressionArithmetique deux = new ConstEntiere(2);
		Division neufDivDeux = new Division(neuf,deux);
		assertEquals("(9/2)",neufDivDeux.toString());

		
		ExpressionArithmetique neufSurDeux = new ConstRationnelle(9,2);
		Division neufSurDeuxDivDeux = new Division(neufSurDeux,deux);
		assertEquals("((9/2)/2)",neufSurDeuxDivDeux.toString());
		
	}
	
	@Test
	public void simplifierRacineCarree() {
		ExpressionArithmetique neuf = new ConstEntiere(9);
		RacineCarree racineNeuf = new RacineCarree(neuf);
		assertEquals("√(9)",racineNeuf.toString());
		
		ExpressionArithmetique quarante = new ConstEntiere(40);
		RacineCarree racineQuarante = new RacineCarree(quarante);
		assertEquals("(2*√(10))",racineQuarante.simplifier().toString());
		
		ExpressionArithmetique deux = new ConstEntiere(2);
		RacineCarree racineNeufSurDeux = new RacineCarree(new ConstRationnelle(9,2));
		assertEquals("(√(9)/√(2))",racineNeufSurDeux.simplifier().toString());
		
	}
	*/
}
