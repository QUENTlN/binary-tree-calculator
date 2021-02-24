package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.Soustraction;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;
import td3.ConstRationnelle;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.Multiplication;
import td3.Puissance;

public class SoustractionTest {
	
	@Test
	public void soustractionTestCalculer() throws Exception {
		
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
		Soustraction minus = new Soustraction(neuf,deux);
		assertEquals(7,minus.calculer(test),0.001);
		
		ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Soustraction minus2 = new Soustraction(neuf,UnSurDeux);
		assertEquals(8.5,minus2.calculer(test),0.001);
		
		Soustraction minus3 = new Soustraction(UnSurDeux,neuf);
		assertEquals(-8.5,minus3.calculer(test),0.001);
		
		ConstRationnelle TroisSurQuatre = new ConstRationnelle(3,4);
		Soustraction minus4 = new Soustraction(TroisSurQuatre,UnSurDeux);
		assertEquals(0.25,minus4.calculer(test),0.001);

		Soustraction minus5 = new Soustraction(neuf,x);
		assertEquals(7,minus5.calculer(test),0.001);
		
		Soustraction minus6 = new Soustraction(TroisSurQuatre,z);
		assertEquals(-4.25,minus6.calculer(test),0.001);
		
		Soustraction minus7 = new Soustraction(z,x);
		assertEquals(3,minus7.calculer(test),0.001);
		
		Soustraction minus0 = new Soustraction(neuf,neuf);
		assertEquals(0,minus0.calculer(test),0.001);
	}
	
	@Test
	public void soustractionTestSimplifier() throws Exception {

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
		Soustraction minus = new Soustraction(neuf,deux);
		assertEquals("7",minus.simplifier().toString());
		
		ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Soustraction minus2 = new Soustraction(neuf,UnSurDeux);
		assertEquals("(17/2)",minus2.simplifier().toString());
		
		ConstRationnelle TroisSurQuatre = new ConstRationnelle(3,4);
		Soustraction minus3 = new Soustraction(TroisSurQuatre,UnSurDeux);
		assertEquals("(1/4)",minus3.simplifier().toString());
		
		Soustraction minus4 = new Soustraction(neuf,x);
		assertEquals("(9-x)",minus4.simplifier().toString());
		
		ConstRationnelle QuaranteSurQuinze = new ConstRationnelle(40,15);
		Soustraction minus5 = new Soustraction(x,QuaranteSurQuinze);
		assertEquals("(x-(8/3))",minus5.simplifier().toString());
		
		Soustraction minus6 = new Soustraction(UnSurDeux,neuf);
		assertEquals("(17/-2)",minus6.simplifier().toString());
		
		Soustraction minus7 = new Soustraction(x,y);
		assertEquals("(x-y)",minus7.simplifier().toString());
		
		Soustraction minus8 = new Soustraction(QuaranteSurQuinze,x);
		assertEquals("((8/3)-x)",minus8.simplifier().toString());
		
		Soustraction minus9 = new Soustraction(x,neuf);
		assertEquals("(x-9)",minus9.simplifier().toString());
		
		
		
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
        
        //identite remarquable Xa²-Yab+Xb²(cas passant)
        Addition sixFoisAFoisBPlustroisFoisBAuCarre=new Addition(sixFoisAFoisB,troisFoisBAuCarre);
        Soustraction ea=new Soustraction(troisFoisAAuCarre,sixFoisAFoisBPlustroisFoisBAuCarre);
        assertEquals("(3*((a-b)^2))",ea.simplifier().toString());
        
        //identite remarquable Xa²-Yab-Xb² (cas non passant)
        Soustraction sixFoisAFoisBMoinstroisFoisBAuCarre=new Soustraction(sixFoisAFoisB,troisFoisBAuCarre);
        Soustraction ea2=new Soustraction(troisFoisAAuCarre,sixFoisAFoisBMoinstroisFoisBAuCarre);
        assertEquals("((3*(a^2))-((6*(a*b))-(3*(b^2))))",ea2.simplifier().toString());
        
        //identite remarquable Xa^3-Yab-Xb² (cas non passant)
        Puissance aCube=new Puissance(a,trois);
        ExpressionArithmetique troisFoisAAuCube=new Multiplication(trois,aCube);
        Soustraction ea3=new Soustraction(troisFoisAAuCube,sixFoisAFoisBMoinstroisFoisBAuCarre);
        assertEquals("((3*(a^3))-((6*(a*b))-(3*(b^2))))",ea3.simplifier().toString());
        
        //identite remarquable Xa²-Yab-Xb^3 (cas non passant)
        //identite remarquable Xa²-Yaa+Xb² (cas non passant)
        //identite remarquable Xa²-Yab+Xa² (cas non passant)
        //identite remarquable Xa²-Yab+Za² (cas non passant)
        //identite remarquable X*3²-(Y*3*6)+X*3² (cas non passant)
        
        //identite remarquable Xa²-Yab-Xb² (cas non passant) Y pas égale au double de x
        ConstEntiere quatre=new ConstEntiere(4);
        ConstEntiere seize=new ConstEntiere(16);

        ExpressionArithmetique quatreFoisAAuCarre=new Multiplication(quatre,new Puissance(a,deux));
        ExpressionArithmetique seizeFoisAFoisB=new Multiplication(seize,new Multiplication(a,b));
        ExpressionArithmetique quatreFoisBAuCarre=new Multiplication(quatre,new Puissance(b,deux));
        Soustraction quatreFoisAAuCarrePlusseizeFoisAFoisBPlusquatreFoisBAuCarre=new Soustraction(quatreFoisAAuCarre,new Addition(seizeFoisAFoisB,quatreFoisBAuCarre));
        assertEquals("((4*(a^2))-((16*(a*b))+(4*(b^2))))",quatreFoisAAuCarrePlusseizeFoisAFoisBPlusquatreFoisBAuCarre.simplifier().toString());
        
        
        
        
        //identite remarquable a²-b² (cas passant)
        Soustraction aCarrebMoinsCarreB=new Soustraction(aCarre,bCarre);
        assertEquals("((a+b)*(a-b))",aCarrebMoinsCarreB.simplifier().toString());

        //identite remarquable Ya²-Yb² (cas passant)
        Multiplication sixFoisACarre=new Multiplication(six,aCarre);
        Multiplication sixFoisBCarre=new Multiplication(six,bCarre);
        Soustraction sixFoisACarreMoinsSixFoisBCarre=new Soustraction(sixFoisACarre,sixFoisBCarre);
        assertEquals("(6*((a+b)*(a-b)))",sixFoisACarreMoinsSixFoisBCarre.simplifier().toString());
        
        //identite remarquable Ya²-Ya² (cas non passant)
        Soustraction sixFoisACarreMoinsSixFoisACarre=new Soustraction(sixFoisACarre,sixFoisACarre);
        assertEquals("0",sixFoisACarreMoinsSixFoisACarre.simplifier().toString());
        
        //identite remarquable Xa²-Yb² (cas non passant)
        Multiplication troisFoisACarre=new Multiplication(trois,aCarre);
        Addition troisFoisACarrePlusSixFoisBCarre=new Addition(troisFoisACarre,sixFoisBCarre);
        assertEquals("((3*(a^2))+(6*(b^2)))", troisFoisACarrePlusSixFoisBCarre.simplifier().toString());

        //identite remarquable Xa^3-Yb² (cas non passant)
        Multiplication troisFoisACube=new Multiplication(trois,aCube);
        Addition troisFoisACubePlusSixFoisBCarre=new Addition(troisFoisACube,sixFoisBCarre);
        assertEquals("((3*(a^3))+(6*(b^2)))",troisFoisACubePlusSixFoisBCarre.simplifier().toString());


        
        //________________________________________________________________________________________________
        
        
	}
	
	@Test
	public void soustractionTestToString() throws DivisionParZeroException {

		ConstEntiere neuf = new ConstEntiere(9);
		ConstEntiere deux = new ConstEntiere(2);
		Soustraction minus = new Soustraction(neuf,deux);
		assertEquals("(9-2)",minus.toString());
		
		ConstRationnelle UnSurDeux = new ConstRationnelle(1,2);
		Soustraction minus2 = new Soustraction(neuf,UnSurDeux);
		assertEquals("(9-(1/2))",minus2.toString());
		
		VariableSymbolique varSymb = new VariableSymbolique("x");
		Soustraction minus3 = new Soustraction(neuf,varSymb);
		assertEquals("(9-x)",minus3.toString());
		
		Soustraction minus4 = new Soustraction(UnSurDeux,varSymb);
		assertEquals("((1/2)-x)",minus4.toString());
	
		
	}
}
