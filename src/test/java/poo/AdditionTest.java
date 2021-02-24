package poo;




import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.ConstSymbolique;
import td3.Division;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.IdentiteRemarquableMapping;
import td3.Multiplication;
import td3.Pi;
import td3.Puissance;
import td3.Soustraction;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;


public class AdditionTest {
	@Test
	public void additionTestCalculer() throws Exception {

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
		
		
		
		//qd y'a pas de valeur attribué test passe pas
		//VariableSymbolique t = new VariableSymbolique("t");
		//Addition plus10 = new Addition(neuf, t);
		//assertEquals(6 +" t",plus10.calculer());
		
		
	}
	
	@Test
	public void additionTestSimplifier() throws Exception {

		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Addition plus = new Addition(neuf,deux);
		assertEquals(11,((ConstEntiere) plus.simplifier()).getEntier());
		
		ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Addition plus2 = new Addition(neuf,UnSurDeux);
		assertEquals(19, ((ConstRationnelle) plus2.simplifier()).getNumerateur());
		assertEquals(2, ((ConstRationnelle) plus2.simplifier()).getDenominateur());

		
		Addition plus3 = new Addition(UnSurDeux,neuf);
		assertEquals(19, ((ConstRationnelle) plus3.simplifier()).getNumerateur());
		assertEquals(2, ((ConstRationnelle) plus3.simplifier()).getDenominateur());

		
		ConstRationnelle troisSurQuatre = new ConstRationnelle(3,4);
		ConstRationnelle vingtSurSeixe = new ConstRationnelle(20,16);
		
		Addition plus4 = new Addition(troisSurQuatre,vingtSurSeixe);
		assertEquals("2", plus4.simplifier().toString());
		
		VariableSymbolique x= new VariableSymbolique("x");
		VariableSymbolique y= new VariableSymbolique("y");
		Addition plus5 = new Addition(x,y);
		assertEquals("(x+y)",plus5.simplifier().toString());
		
		
		Addition plus6 = new Addition(neuf, x);
		assertEquals("(9+x)",plus6.simplifier().toString());
		
		
		Addition plus7 = new Addition(x,neuf);
		assertEquals("(x+9)",plus7.simplifier().toString());
		
		
		Addition plus8 = new Addition(UnSurDeux, y);
		assertEquals("((1/2)+y)",plus8.simplifier().toString());
		
		
		Addition plus9 = new Addition(x, troisSurQuatre);
		assertEquals("(x+(3/4))",plus9.simplifier().toString());
		
		
		VariableSymbolique t = new VariableSymbolique("t");
		Addition plus10 = new Addition(t, neuf);
		assertEquals("(t+9)",plus10.simplifier().toString());
		
	
		Addition plus11 = new Addition(x, vingtSurSeixe);
		assertEquals("(x+(5/4))",plus11.simplifier().toString());
		
		

        
		//________________________________________________________________________________________________
		
		
		//identite remarquable
		VariableSymbolique a=new VariableSymbolique("a");
		VariableSymbolique b=new VariableSymbolique("b");
        ConstEntiere trois=new ConstEntiere(3);
        ConstEntiere six=new ConstEntiere(6);
        Puissance aCarre=new Puissance(a,deux);
        Puissance bCarre=new Puissance(b,deux);
        Multiplication aFoisB=new Multiplication(a,b);
        
        ExpressionArithmetique troisFoisAAuCarre=new Multiplication(trois,aCarre);
        ExpressionArithmetique sixFoisAFoisB=new Multiplication(six,aFoisB);
        ExpressionArithmetique troisFoisBAuCarre=new Multiplication(trois,bCarre);
        
        //identite remarquable Xa²+Yab+Xb²(cas passant)
        Addition sixFoisAFoisBPlustroisFoisBAuCarre=new Addition(sixFoisAFoisB,troisFoisBAuCarre);
        Addition ea=new Addition(troisFoisAAuCarre,sixFoisAFoisBPlustroisFoisBAuCarre);
        assertEquals("(3*((a+b)^2))",ea.simplifier().toString());

        //identite remarquable Xa²-Yab-Xb² (cas non passant)
        Soustraction sixFoisAFoisBMoinstroisFoisBAuCarre=new Soustraction(sixFoisAFoisB,troisFoisBAuCarre);
        Addition ea2=new Addition(troisFoisAAuCarre,sixFoisAFoisBMoinstroisFoisBAuCarre);
        assertEquals("((3*(a^2))+((6*(a*b))-(3*(b^2))))",ea2.simplifier().toString());

        //identite remarquable Xa^3-Yab-Xb² (cas non passant)
        Puissance aCube=new Puissance(a,trois);
        ExpressionArithmetique troisFoisAAuCube=new Multiplication(trois,aCube);
        Addition ea3=new Addition(troisFoisAAuCube,sixFoisAFoisBMoinstroisFoisBAuCarre);
        assertEquals("((3*(a^3))+((6*(a*b))-(3*(b^2))))",ea3.simplifier().toString());
        
        //identite remarquable Xa²-Yab-Xb² (cas non passant) Y pas égale au double de x
        ConstEntiere quatre=new ConstEntiere(4);
        ConstEntiere seize=new ConstEntiere(16);

        
        //identite remarquable Xa²-Yab-Xb^3 (cas non passant)
        //identite remarquable Xa²-Yaa+Xb² (cas non passant)
        //identite remarquable Xa²-Yab+Xa² (cas non passant)
        //identite remarquable Xa²-Yab+Za² (cas non passant)
        //identite remarquable X*3²-(Y*3*6)+X*3² (cas non passant)  
        
        ExpressionArithmetique quatreFoisAAuCarre=new Multiplication(quatre,new Puissance(a,deux));
        ExpressionArithmetique seizeFoisAFoisB=new Multiplication(seize,new Multiplication(a,b));
        ExpressionArithmetique quatreFoisBAuCarre=new Multiplication(quatre,new Puissance(b,deux));
        Addition quatreFoisAAuCarrePlusseizeFoisAFoisBPlusquatreFoisBAuCarre=new Addition(quatreFoisAAuCarre,new Addition(seizeFoisAFoisB,quatreFoisBAuCarre));
        assertEquals("((4*(a^2))+((16*(a*b))+(4*(b^2))))",quatreFoisAAuCarrePlusseizeFoisAFoisBPlusquatreFoisBAuCarre.simplifier().toString());
        
        
		//________________________________________________________________________________________________
		
	}
	@Test
	public void additionTestToString() throws DivisionParZeroException {

		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Addition plus = new Addition(neuf,deux);
		assertEquals("(9+2)",plus.toString());
		
		
		ConstRationnelle unSurDeux = new ConstRationnelle(1,2);
		Addition plus2 = new Addition(neuf,unSurDeux);
		assertEquals("(9+(1/2))",plus2.toString());
		
		VariableSymbolique x = new VariableSymbolique("x");
		Addition plus3 = new Addition(neuf,x);
		assertEquals("(9+x)",plus3.toString());
		
		Addition plus4 = new Addition(unSurDeux,x);
		assertEquals("((1/2)+x)",plus4.toString());
	}
	
}
