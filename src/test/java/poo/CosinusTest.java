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
import td3.Soustraction;
import td3.ValeurNegativeOuNulleSousLaRacineException;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class CosinusTest {
	@Test
	public void cosinusTestCalculer() throws Exception {
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
		Cosinus cosDeux =  new Cosinus (deux);
		assertEquals(-0.417,cosDeux.calculer(map),0.001);
		
		
		ConstRationnelle neufSurDeux = new ConstRationnelle(9,2);
		Cosinus cosNeufSurDeux =  new Cosinus (neufSurDeux);
		assertEquals(-0.211,cosNeufSurDeux.calculer(map),0.001);
		
		
		Cosinus cosX = new Cosinus(x);
		assertEquals(-0.417,cosX.calculer(map),0.001);
		
	}

	@Test
	public void cosinusTestSimplifier() throws Exception {
		    
		ConstEntiere deux = new ConstEntiere(2);
		Cosinus cosDeux =  new Cosinus (deux);
		
		assertEquals("cos(2)",cosDeux.simplifier().toString());
		
		ConstEntiere zero = new ConstEntiere(0);
		Cosinus cosZero =  new Cosinus (zero);
		assertEquals("1",cosZero.simplifier().toString());
		
		ConstRationnelle neufSurDeux = new ConstRationnelle(9,2);
		Cosinus cosNeufSurDeux =  new Cosinus (neufSurDeux);
		assertEquals("cos((9/2))",cosNeufSurDeux.simplifier().toString());
		
		ConstRationnelle vingtSurSeixe = new ConstRationnelle(20,16);
		Cosinus cosVingtSurSeixe =  new Cosinus (vingtSurSeixe);
		assertEquals("cos((5/4))",cosVingtSurSeixe.simplifier().toString());
		
		VariableSymbolique x= new VariableSymbolique("x");
		Cosinus cosX = new Cosinus(x);
		assertEquals("cos(x)",cosX.simplifier().toString());
		
		
		ConstEntiere neuf = new ConstEntiere(9);
		Addition DeuxPlusNeuf = new Addition(neuf,deux);
		Cosinus cosDeuxPlusNeuf = new Cosinus(DeuxPlusNeuf);
		assertEquals("cos(11)",cosDeuxPlusNeuf.simplifier().toString());
		
		Addition DeuxPlusX = new Addition (deux,x);
		assertEquals("((cos(2)*cos(x))-(sin(2)*sin(x)))",new Cosinus(DeuxPlusX).simplifier().toString());
		
		
		Soustraction NeufMoinsDeux = new Soustraction(neuf,deux);
		Cosinus cosNeufMoinsDeux = new Cosinus(NeufMoinsDeux);
		assertEquals("cos(7)",cosNeufMoinsDeux.simplifier().toString());
		
		Soustraction DeuxMoinsX = new Soustraction (deux,x);
		assertEquals("((cos(2)*cos(x))+(sin(2)*sin(x)))",new Cosinus(DeuxMoinsX).simplifier().toString());
		
		Division NeufDiviseParDeux = new Division(neuf,deux);
		Cosinus cosNeufDiviseParDeux = new Cosinus(NeufDiviseParDeux);
		assertEquals("cos((9/2))",cosNeufDiviseParDeux.simplifier().toString());
		
		Division NeufDiviseParX = new Division(neuf,x);
		assertEquals("cos((9/x))", new Cosinus (NeufDiviseParX).simplifier().toString());
		
		ConstSymbolique pi=new Pi();
		ConstEntiere six = new ConstEntiere(6);
		Division piSurSix = new Division(pi,six);
		Cosinus cosPiSurSix = new Cosinus(piSurSix);
		assertEquals("(√(3)/2)",cosPiSurSix.simplifier().toString());
		
		ConstEntiere quatre = new ConstEntiere(4);
		Division piSurQuatre = new Division(pi,quatre);
		Cosinus cosPiSurQuatre = new Cosinus(piSurQuatre);
		assertEquals("(√(2)/2)",cosPiSurQuatre.simplifier().toString());
		
		ConstEntiere trois = new ConstEntiere(3);
		Division piSurTrois = new Division(pi,trois);
		Cosinus cosPiSurTrois = new Cosinus(piSurTrois);
		assertEquals("(1/2)",cosPiSurTrois.simplifier().toString());
		
		Division piSurDeux = new Division(pi,deux);
		Cosinus cosPiSurDeux = new Cosinus(piSurDeux);
		assertEquals("0",cosPiSurDeux.simplifier().toString());
		
		ConstEntiere sept = new ConstEntiere(7);
		Division piSurSept = new Division (pi,sept);
		assertEquals("cos(((π)/7))",new Cosinus(piSurSept).simplifier().toString());
		
		Multiplication NeufFoisDeux = new  Multiplication(neuf, deux);
		Cosinus cosNeufFoisDeux = new  Cosinus(NeufFoisDeux);
		assertEquals("cos(18)",cosNeufFoisDeux.simplifier().toString());
		
		Multiplication NeufFoisX = new  Multiplication(neuf, x);
		assertEquals("cos((9*x))",new Cosinus(NeufFoisX).simplifier().toString());
		
		Puissance xAuCarre = new Puissance(x,deux);
		Cosinus cosXAuCarre = new  Cosinus(xAuCarre);
		assertEquals("cos((x^2))",cosXAuCarre.simplifier().toString());
		
		RacineCarree racineNeuf = new RacineCarree(neuf);
		Cosinus cosRacineNeuf = new Cosinus(racineNeuf);
		assertEquals("cos(3)",cosRacineNeuf.simplifier().toString());
		
		RacineCarree racineX = new RacineCarree(x);
		assertEquals("cos(√(x))",new Cosinus(racineX).simplifier().toString());
	}
	
	@Test
	public void cosinusToStringTest() throws Exception {
		ConstEntiere deux = new ConstEntiere(2);
		assertEquals("cos(2)",new Cosinus(deux).toString());
		
		ConstRationnelle sixSurTrois= new ConstRationnelle(6,3);
		assertEquals("cos((6/3))",new Cosinus(sixSurTrois).toString());
		
		VariableSymbolique x= new VariableSymbolique("x");
		assertEquals("cos(x)",new Cosinus(x).toString());
		
		ConstEntiere neuf = new ConstEntiere(9);
		Addition DeuxPlusNeuf = new Addition(neuf,deux);
		assertEquals("cos((9+2))",new Cosinus(DeuxPlusNeuf).toString());
		
		Soustraction NeufMoinsDeux = new Soustraction(neuf,deux);
		assertEquals("cos((9-2))",new Cosinus(NeufMoinsDeux).toString());
		
		Division NeufDiviseParDeux = new Division(neuf,deux);
		assertEquals("cos((9/2))",new Cosinus(NeufDiviseParDeux).toString());
		
		Multiplication NeufFoisDeux = new  Multiplication(neuf, deux);
		assertEquals("cos((9*2))",new Cosinus(NeufFoisDeux).toString());
		
		Puissance xAuCarre = new Puissance(x,deux);
		assertEquals("cos((x^2))",new Cosinus(xAuCarre).toString());
		
		RacineCarree racineNeuf = new RacineCarree(neuf);
		assertEquals("cos(√(9))",new Cosinus(racineNeuf).toString());
	}
} 
