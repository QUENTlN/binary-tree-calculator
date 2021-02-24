package poo;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.Division;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class DivisionTest {
	
	@Test
	public void divisionTestSimplifier() throws Exception{

    	
		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Division div = new Division(neuf,deux);
		assertEquals("(9/2)",div.simplifier().toString());
		
		ConstRationnelle unSurDeux = new ConstRationnelle(1,2);
		Division div2 = new Division(neuf,unSurDeux);
		assertEquals("18",div2.simplifier().toString());
				
		Division div3 = new Division(unSurDeux,neuf);
		assertEquals("(1/18)",div3.simplifier().toString());
		
		ConstRationnelle troisSurQuatre = new ConstRationnelle(3,4);
		ConstRationnelle cinqSurDix = new ConstRationnelle(5,10);
		Division div4 = new Division(troisSurQuatre,unSurDeux);
		assertEquals("(3/2)",div4.simplifier().toString());
		
		VariableSymbolique x= new VariableSymbolique("x");
		Division div5 = new Division(neuf,x);
		assertEquals("(9/x)",div5.simplifier().toString());
				
		Division div6 = new Division(x,neuf);
		assertEquals("(x/9)",div6.simplifier().toString());
		

		Division div7 = new Division(x,cinqSurDix);
		assertEquals("(x/(1/2))",div7.simplifier().toString());
		
		Division div8 = new Division(cinqSurDix,x);
		assertEquals("((1/2)/x)",div8.simplifier().toString());
		
		VariableSymbolique y= new VariableSymbolique("y");
		Division div9 = new Division(x,y);
		assertEquals("(x/y)",div9.simplifier().toString());


		
	}
	
	
	@Test
	public void CalculerDivisionTest() throws Exception {
	    
	    List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
		VariableSymbolique x= new VariableSymbolique("x");
	    VariableSymbolique y= new VariableSymbolique("y");
	    listeTest.add(x);
	    listeTest.add(y);
        
        List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
	    ExpressionArithmetique valeur1= new ConstEntiere(2);
        ExpressionArithmetique valeur2= new ConstEntiere(4);
        listeTest2.add(valeur1);
        listeTest2.add(valeur2);
        
        VariableSymboliqueMapping map = new VariableSymboliqueMapping(listeTest,listeTest2);

        ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Division div1 = new Division(neuf,deux);
		assertEquals(4.5,div1.calculer(map),0.001);
		
		ConstRationnelle CinqSurDix = new ConstRationnelle(5,10);
		Division div2 = new Division(neuf,CinqSurDix);
		assertEquals(18,div2.calculer(map),0.001);
		
		Division div3 = new Division(CinqSurDix,neuf);
		assertEquals(0.055,div3.calculer(map),0.001);
		
		Division div4 = new Division(x,neuf);
		assertEquals(0.222,div4.calculer(map),0.001);
		
		Division div5 = new Division(neuf,x);
		assertEquals(4.5,div5.calculer(map),0.001);
		
		ConstRationnelle NeufSurTrois = new ConstRationnelle(9,3);
		Division div6 = new Division(NeufSurTrois,CinqSurDix);
		assertEquals(6,div6.calculer(map),0.001);

		Division div7 = new Division(NeufSurTrois,y);
		assertEquals(0.75,div7.calculer(map),0.001);
		
		Division div8 = new Division(y,x);
		assertEquals(2,div8.calculer(map),0.001);
	}
	
	@Test
	public void DivisionToStringTest() throws Exception {
		
		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Division div1 = new Division(neuf,deux);
		assertEquals("(9/2)",div1.toString());
		
		ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Division div2 = new Division(neuf,UnSurDeux);
		assertEquals("(9/(1/2))",div2.toString());
		
		VariableSymbolique varSymb = new VariableSymbolique("x");
		Division div3 = new Division(neuf,varSymb);
		assertEquals("(9/x)",div3.toString());
		
		Division div4 = new Division(UnSurDeux,varSymb);
		assertEquals("((1/2)/x)",div4.toString());
	}

}
