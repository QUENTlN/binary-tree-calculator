package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.Addition;
import td3.Associativite;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.ConstSymbolique;
import td3.DivisionParZeroException;
import td3.Exponentielle;
import td3.ExpressionArithmetique;
import td3.Multiplication;
import td3.Pi;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class AssociativiteTest {

	@Test
	public void associativiteTestSimplifier() throws Exception {
		
		VariableSymbolique y = new VariableSymbolique("y");
		ConstEntiere trois = new ConstEntiere(3);
		ConstEntiere quatre =new ConstEntiere(4);
		Addition add = new Addition(trois, y);
		Addition addd = new Addition(quatre, add);
		Associativite asso = new Associativite (addd);
		assertEquals("(7+y)",asso.simplifier().toString());
		
		
		ConstEntiere un = new ConstEntiere(1);
		VariableSymbolique var = new VariableSymbolique("x");
		ConstRationnelle unSurDeux = new ConstRationnelle(1, 2);
		
		Addition addition1 = new Addition(unSurDeux, var);
		Addition addition2 = new Addition(quatre, addition1);
		Associativite associativite1 = new Associativite (addition2);
		assertEquals("((9/2)+x)",associativite1.simplifier().toString());
		
		Addition addition3 = new Addition(quatre, y);
		Addition addition4 = new Addition(unSurDeux, addition3);
		Associativite associativite2 = new Associativite (addition4);
		assertEquals("((9/2)+y)",associativite2.simplifier().toString());
		
		Addition addi = new Addition (un,var);
        Addition additi = new Addition (un,addi);
        Addition additio = new Addition (additi,un);
        Addition additioo = new Addition(un,additio);
        Associativite assoc = new Associativite (additioo);
        assertEquals("(4+x)",assoc.simplifier().toString());
		
        
        Addition addi2 = new Addition (unSurDeux,var);
        Addition additi2 = new Addition (un,addi2);
        Addition additio2 = new Addition (additi2,un);
        Addition additioo2 = new Addition(un,additio2);
        Associativite assoc2 = new Associativite (additioo2);
        assertEquals("((7/2)+x)",assoc2.simplifier().toString());
        
        ConstSymbolique pi = new Pi();
		Addition addition5 = new Addition(trois, pi);
		Addition addition6 = new Addition(quatre, addition5);
		Associativite assocPi = new Associativite (addition6);
		assertEquals("(7+(π))",assocPi.simplifier().toString());
		
		ConstSymbolique e = new Exponentielle();
		Addition addition7 = new Addition(trois, e);
		Addition addition8 = new Addition(quatre, addition7);
		Associativite assocExpo = new Associativite (addition8);
		assertEquals("(7+(e))",assocExpo.simplifier().toString());
        
        ConstEntiere deux = new ConstEntiere(2);
        Multiplication mult= new Multiplication(un,var);
        Multiplication mult2 = new Multiplication(deux, mult);
        Associativite assoc3 = new Associativite (mult2);
        assertEquals("(2*x)",assoc3.simplifier().toString());
        
        
        Multiplication mult3 = new Multiplication(unSurDeux, var);
        Multiplication mult4 = new Multiplication(deux, mult3);
        Associativite assoc4 = new Associativite(mult4);
        assertEquals("x",assoc4.simplifier().toString());
        
        Multiplication mult5 = new Multiplication (un,var);
        Multiplication mult6 = new Multiplication (un,mult5);
        Multiplication mult7 = new Multiplication (mult6,un);
        Multiplication mult8 = new Multiplication(un,mult7);
        Associativite assoc5 = new Associativite (mult8);
        assertEquals("x",assoc5.simplifier().toString());
        
        Multiplication mult9 = new Multiplication (unSurDeux,var);
        Multiplication mult10 = new Multiplication (un,mult9);
        Multiplication mult11 = new Multiplication (mult10,trois);
        Multiplication mult12 = new Multiplication(deux,mult11);
        Associativite assoc6 = new Associativite (mult12);
        assertEquals("(3*x)",assoc6.simplifier().toString());
        
        Multiplication mult13 = new Multiplication(trois, pi);
        Multiplication mult14 = new Multiplication(quatre, mult13);
		Associativite assocMultPi = new Associativite (mult14);
		assertEquals("(12*(π))",assocMultPi.simplifier().toString());
		
		Multiplication mult15 = new Multiplication(trois, e);
		Multiplication mult16 = new Multiplication(quatre, mult15);
		Associativite assocMultExpo = new Associativite (mult16);
		assertEquals("(12*(e))",assocMultExpo.simplifier().toString());
        

	}
	
	@Test
	public void associativiteTestCalculer() throws Exception {
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
        VariableSymboliqueMapping map = new VariableSymboliqueMapping(listeTest,listeTest2);
        
		ConstEntiere trois = new ConstEntiere(3);
		ConstEntiere quatre =new ConstEntiere(4);
		Addition add = new Addition(trois, y);
		Addition addd = new Addition(quatre, add);
		Associativite asso = new Associativite (addd);
		assertEquals(11,asso.calculer(map),0.0001);
		
		ConstEntiere un = new ConstEntiere(1);
		ConstRationnelle unSurDeux = new ConstRationnelle(1, 2);
		
		Addition addition1 = new Addition(unSurDeux, x);
		Addition addition2 = new Addition(quatre, addition1);
		Associativite associativite1 = new Associativite (addition2);
		assertEquals(6.5,associativite1.calculer(map),0.0001);
		
		Addition addition3 = new Addition(quatre, y);
		Addition addition4 = new Addition(unSurDeux, addition3);
		Associativite associativite2 = new Associativite (addition4);
		assertEquals(8.5,associativite2.calculer(map),0.0001);
		
		Addition addi = new Addition (un,x);
        Addition additi = new Addition (un,addi);
        Addition additio = new Addition (additi,un);
        Addition additioo = new Addition(un,additio);
        Associativite assoc = new Associativite (additioo);
        assertEquals(6,assoc.calculer(map),0.0001);
		
        
        Addition addi2 = new Addition (unSurDeux,x);
        Addition additi2 = new Addition (un,addi2);
        Addition additio2 = new Addition (additi2,un);
        Addition additioo2 = new Addition(un,additio2);
        Associativite assoc2 = new Associativite (additioo2);
        assertEquals(5.5,assoc2.calculer(map),0.0001);
        
        ConstEntiere deux = new ConstEntiere(2);
        Multiplication mult= new Multiplication(un,x);
        Multiplication mult2 = new Multiplication(deux, mult);
        Associativite assoc3 = new Associativite (mult2);
        assertEquals(4,assoc3.calculer(map),0.0001);
        
        
        Multiplication mult3 = new Multiplication(unSurDeux, x);
        Multiplication mult4 = new Multiplication(deux, mult3);
        Associativite assoc4 = new Associativite(mult4);
        assertEquals(2,assoc4.calculer(map),0.0001);
        
        Multiplication mult5 = new Multiplication (un,x);
        Multiplication mult6 = new Multiplication (un,mult5);
        Multiplication mult7 = new Multiplication (mult6,un);
        Multiplication mult8 = new Multiplication(un,mult7);
        Associativite assoc5 = new Associativite (mult8);
        assertEquals(2,assoc5.calculer(map),0.0001);
        
        Multiplication mult9 = new Multiplication (unSurDeux,x);
        Multiplication mult10 = new Multiplication (un,mult9);
        Multiplication mult11 = new Multiplication (mult10,trois);
        Multiplication mult12 = new Multiplication(deux,mult11);
        Associativite assoc6 = new Associativite (mult12);
        assertEquals(6,assoc6.calculer(map),0.0001);
        
        ConstSymbolique pi = new Pi();
		Addition addition5 = new Addition(trois, pi);
		Addition addition6 = new Addition(quatre, addition5);
		Associativite assocPi = new Associativite (addition6);
		assertEquals(10.141,assocPi.calculer(map),0.001);
		
		ConstSymbolique e = new Exponentielle();
		Addition addition7 = new Addition(trois, e);
		Addition addition8 = new Addition(quatre, addition7);
		Associativite assocExpo = new Associativite (addition8);
		assertEquals(9.718,assocExpo.calculer(map),0.001);
		
		Multiplication mult13 = new Multiplication(trois, pi);
        Multiplication mult14 = new Multiplication(quatre, mult13);
		Associativite assocMultPi = new Associativite (mult14);
		assertEquals(37.699,assocMultPi.calculer(map),0.001);
		
		Multiplication mult15 = new Multiplication(trois, e);
		Multiplication mult16 = new Multiplication(quatre, mult15);
		Associativite assocMultExpo = new Associativite (mult16);
		assertEquals(32.619,assocMultExpo.calculer(map),0.001);
        
        
	}
}
