package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.ConstSymbolique;
import td3.Cosinus;
import td3.Division;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.Multiplication;
import td3.Pi;
import td3.Puissance;
import td3.RacineCarree;
import td3.Sinus;
import td3.Soustraction;
import td3.ValeurNegativeOuNulleSousLaRacineException;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class SinusTest {
	@Test
	public void SinusTestCalculer() throws Exception {
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
	        
		ConstEntiere deux = new ConstEntiere(2);
		Sinus sinDeux =  new Sinus (deux);
		assertEquals(0.909,sinDeux.calculer(map),0.001);
		
		
		ConstRationnelle neufSurDeux = new ConstRationnelle(9,2);
		assertEquals(-0.977,new Sinus (neufSurDeux).calculer(map),0.001);
		
		
		Sinus sinX = new Sinus(x);
		assertEquals(0.909,sinX.calculer(map),0.001);
		
	}

	@Test
	public void SinusTestSimplifier() throws Exception {
		    
		ConstEntiere deux = new ConstEntiere(2);
		assertEquals("sin(2)",new Sinus(deux).simplifier().toString());
		
		ConstEntiere zero = new ConstEntiere(0);
		assertEquals("0",new Sinus(zero).simplifier().toString());
		
		ConstRationnelle neufSurDeux = new ConstRationnelle(9,2);
		assertEquals("sin((9/2))",new Sinus (neufSurDeux).simplifier().toString());
		
		ConstRationnelle vingtSurSeixe = new ConstRationnelle(20,16);
		assertEquals("sin((5/4))", new Sinus (vingtSurSeixe).simplifier().toString());
		
		VariableSymbolique x= new VariableSymbolique("x");
		assertEquals("sin(x)",new Sinus(x).simplifier().toString());
		
		
		ConstEntiere neuf = new ConstEntiere(9);
		Addition DeuxPlusNeuf = new Addition(neuf,deux);
		assertEquals("sin(11)",new Sinus(DeuxPlusNeuf).simplifier().toString());
		
		Addition DeuxPlusX = new Addition (deux,x);
		assertEquals("((sin(2)*cos(x))+(cos(2)*sin(x)))",new Sinus(DeuxPlusX).simplifier().toString());
		
		Soustraction NeufMoinsDeux = new Soustraction(neuf,deux);
		assertEquals("sin(7)",new Sinus(NeufMoinsDeux).simplifier().toString());
		
		Soustraction DeuxMoinsX = new Soustraction (deux,x);
		assertEquals("((sin(2)*cos(x))-(cos(2)*sin(x)))",new Sinus(DeuxMoinsX).simplifier().toString());
		
		
		Division NeufDiviseParDeux = new Division(neuf,deux);
		assertEquals("sin((9/2))",new Sinus(NeufDiviseParDeux).simplifier().toString());
		
		Division NeufDiviseParX = new Division(neuf,x);
		assertEquals("sin((9/x))", new Sinus (NeufDiviseParX).simplifier().toString());
		
		ConstSymbolique pi=new Pi();
		ConstEntiere six = new ConstEntiere(6);
		Division piSurSix = new Division(pi,six);
		assertEquals("(1/2)",new Sinus(piSurSix).simplifier().toString());
		
		ConstEntiere quatre = new ConstEntiere(4);
		Division piSurQuatre = new Division(pi,quatre);
		assertEquals("(√(2)/2)",new Sinus(piSurQuatre).simplifier().toString());
		
		ConstEntiere trois = new ConstEntiere(3);
		Division piSurTrois = new Division(pi,trois);
		assertEquals("(√(3)/2)",new Sinus(piSurTrois).simplifier().toString());
		
		Division piSurDeux = new Division(pi,deux);
		assertEquals("1",new Sinus(piSurDeux).simplifier().toString());
		
		ConstEntiere sept = new ConstEntiere(7);
		Division piSurSept = new Division (pi,sept);
		assertEquals("sin(((π)/7))",new Sinus(piSurSept).simplifier().toString());
		
		
		Multiplication NeufFoisDeux = new  Multiplication(neuf, deux);
		assertEquals("sin(18)",new  Sinus(NeufFoisDeux).simplifier().toString());
		
		Multiplication NeufFoisX = new  Multiplication(neuf, x);
		assertEquals("sin((9*x))",new Sinus(NeufFoisX).simplifier().toString());
		
		Puissance xAuCarre = new Puissance(x,deux);
		assertEquals("sin((x^2))",new  Sinus(xAuCarre).simplifier().toString());
		
		RacineCarree racineNeuf = new RacineCarree(neuf);
		assertEquals("sin(3)", new Sinus(racineNeuf).simplifier().toString());
		
		
		RacineCarree racineX = new RacineCarree(x);
		assertEquals("sin(√(x))",new Sinus(racineX).simplifier().toString());
		
	}
	
	@Test
	public void cosinusToStringTest() throws Exception {
		ConstEntiere deux = new ConstEntiere(2);
		assertEquals("sin(2)",new Sinus(deux).toString());
		
		ConstRationnelle sixSurTrois= new ConstRationnelle(6,3);
		assertEquals("sin((6/3))",new Sinus(sixSurTrois).toString());
		
		VariableSymbolique x= new VariableSymbolique("x");
		assertEquals("sin(x)",new Sinus(x).toString());
		
		ConstEntiere neuf = new ConstEntiere(9);
		Addition DeuxPlusNeuf = new Addition(neuf,deux);
		assertEquals("sin((9+2))",new Sinus(DeuxPlusNeuf).toString());
		
		Soustraction NeufMoinsDeux = new Soustraction(neuf,deux);
		assertEquals("sin((9-2))",new Sinus(NeufMoinsDeux).toString());
		
		Division NeufDiviseParDeux = new Division(neuf,deux);
		assertEquals("sin((9/2))",new Sinus(NeufDiviseParDeux).toString());
		
		Multiplication NeufFoisDeux = new  Multiplication(neuf, deux);
		assertEquals("sin((9*2))",new Sinus(NeufFoisDeux).toString());
		
		Puissance xAuCarre = new Puissance(x,deux);
		assertEquals("sin((x^2))",new Sinus(xAuCarre).toString());
		
		RacineCarree racineNeuf = new RacineCarree(neuf);
		assertEquals("sin(√(9))",new Sinus(racineNeuf).toString());
	}
} 
