package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import td3.Addition;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.Distribution;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.Multiplication;
import td3.Soustraction;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class DistributionTest {
	@Test
	public void distributionTestSimplifier() throws Exception{
		//2(x+(1/2))
		ConstEntiere deux = new ConstEntiere(2);
		VariableSymbolique var = new VariableSymbolique("x");
		ConstRationnelle unDemi = new ConstRationnelle(1,2);
		Addition add = new Addition(var,unDemi);
		Multiplication mult = new Multiplication(deux,add);
		Distribution dist = new Distribution(mult);
		assertEquals("((2*x)+1)",dist.simplifier().toString());
		
		
		//2((x+(1/2))+5)
		ConstEntiere cinq = new ConstEntiere(5);
		Addition add2 = new Addition(add,cinq);
		Multiplication mult2 = new Multiplication(deux,add2);
		Distribution dist2 = new Distribution(mult2);
		assertEquals("(((2*x)+1)+10)",dist2.simplifier().toString());
		
		//2(x-(1/2))
		Soustraction sous = new Soustraction(var,unDemi);
		Multiplication mult3 = new Multiplication(deux, sous);
		Distribution dist3 = new Distribution(mult3);
		assertEquals("((2*x)-1)",dist3.simplifier().toString());
	
		
		//2*((x+(1/2))-5)
		Soustraction sous2 = new Soustraction(add,cinq);
		Multiplication mult4 = new Multiplication (deux,sous2);
		Distribution dist4 = new Distribution(mult4);
		assertEquals("(((2*x)+1)-10)",dist4.simplifier().toString());
		
		//2*((x-(1/2))-5)
		Soustraction sous3 = new Soustraction(sous,cinq);
		Multiplication mult5 = new Multiplication (deux,sous3);
		Distribution dist5 = new Distribution(mult5);
		assertEquals("(((2*x)-1)-10)",dist5.simplifier().toString());
	
		
		//2*((x-(1/2))+5)
		Addition add3 = new Addition(sous, cinq);
		Multiplication mult6 = new Multiplication (deux,add3);
		Distribution dist6 = new Distribution(mult6);
		assertEquals("(((2*x)-1)+10)",dist6.simplifier().toString());
		
		
		//2*(5+(x+(1/2)))
		Addition add4 = new Addition(cinq, add);
		Multiplication mult7 = new Multiplication (deux,add4);
		Distribution dist7 = new Distribution(mult7);
		assertEquals("(10+((2*x)+1))",dist7.simplifier().toString());
		
		
		//2*(5-(x-(1/2)))
		Soustraction sous4 = new Soustraction(cinq, sous);
		Multiplication mult8 = new Multiplication (deux,sous4);
		Distribution dist8 = new Distribution(mult8);
		assertEquals("(10-((2*x)-1))",dist8.simplifier().toString());
		
		//2*(5-(x+(1/2)))
		Soustraction sous5 = new Soustraction(cinq, add);
		Multiplication mult9 = new Multiplication (deux,sous5);
		Distribution dist9 = new Distribution(mult9);
		assertEquals("(10-((2*x)+1))",dist9.simplifier().toString());
		
		
		//2*(5+(x-(1/2)))
		Addition add5 = new Addition(cinq, sous);
		Multiplication mult10 = new Multiplication (deux,add5);
		Distribution dist10 = new Distribution(mult10);
		assertEquals("(10+((2*x)-1))",dist10.simplifier().toString());
		
		
	}
	
	@Test
	public void distributionTestCalculer() throws Exception{
		
		 	VariableSymbolique var= new VariableSymbolique("x");
		   
		    
		    List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
		    listeTest.add(var);
		    
		    ExpressionArithmetique valeur1= new ConstEntiere(4);
	      
	        List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
	        listeTest2.add(valeur1);
	        
	        VariableSymboliqueMapping map = new VariableSymboliqueMapping(listeTest,listeTest2);
		
		//2(x+(1/2))
		ConstEntiere deux = new ConstEntiere(2);
		ConstRationnelle unDemi = new ConstRationnelle(1,2);
		Addition add = new Addition(var,unDemi);
		Multiplication mult = new Multiplication(deux,add);
		Distribution dist = new Distribution(mult);
		assertEquals(9,dist.calculer(map),0.001);
		
	
		//2((x+(1/2))+5)
		ConstEntiere cinq = new ConstEntiere(5);
		Addition add2 = new Addition(add,cinq);
		Multiplication mult2 = new Multiplication(deux,add2);
		Distribution dist2 = new Distribution(mult2);
		assertEquals(19,dist2.calculer(map),0.001);
		
		
		//2(x-(1/2))
		Soustraction sous = new Soustraction(var,unDemi);
		Multiplication mult3 = new Multiplication(deux, sous);
		Distribution dist3 = new Distribution(mult3);
		assertEquals(7,dist3.calculer(map),0.001);
		
		
		//2*((x+(1/2))-5)
		Soustraction sous2 = new Soustraction(add,cinq);
		Multiplication mult4 = new Multiplication (deux,sous2);
		Distribution dist4 = new Distribution(mult4);
		assertEquals(-1,dist4.calculer(map),0.001);
		
		//2*((x-(1/2))-5)
		Soustraction sous3 = new Soustraction(sous,cinq);
		Multiplication mult5 = new Multiplication (deux,sous3);
		Distribution dist5 = new Distribution(mult5);
		assertEquals(-3,dist5.calculer(map),0.001);
		
	
		
		//2*((x-(1/2))+5)
		Addition add3 = new Addition(sous, cinq);
		Multiplication mult6 = new Multiplication (deux,add3);
		Distribution dist6 = new Distribution(mult6);
		assertEquals(17,dist6.calculer(map),0.001);
		
		
		//2*(5+(x+(1/2)))
		Addition add4 = new Addition(cinq, add);
		Multiplication mult7 = new Multiplication (deux,add4);
		Distribution dist7 = new Distribution(mult7);
		assertEquals(19,dist7.calculer(map),0.001);
		
		//2*(5-(x-(1/2)))
		Soustraction sous4 = new Soustraction(cinq, sous);
		Multiplication mult8 = new Multiplication (deux,sous4);
		Distribution dist8 = new Distribution(mult8);
		assertEquals(3,dist8.calculer(map),0.001);
		
		//2*(5-(x+(1/2)))
		Soustraction sous5 = new Soustraction(cinq, add);
		Multiplication mult9 = new Multiplication (deux,sous5);
		Distribution dist9 = new Distribution(mult9);
		assertEquals(1,dist9.calculer(map),0.001);
		
		
		//2*(5+(x-(1/2)))
		Addition add5 = new Addition(cinq, sous);
		Multiplication mult10 = new Multiplication (deux,add5);
		Distribution dist10 = new Distribution(mult10);
		assertEquals(17,dist10.calculer(map),0.001);
	}
	
	
	@Test
	public void distributionToString() throws DivisionParZeroException {
		//2(x+(1/2))
		ConstEntiere deux = new ConstEntiere(2);
		VariableSymbolique var = new VariableSymbolique("x");
		ConstRationnelle unDemi = new ConstRationnelle(1,2);
		Addition add = new Addition(var,unDemi);
		Multiplication mult = new Multiplication(deux,add);
		Distribution dist = new Distribution(mult);
		assertEquals("((2*(x+(1/2))))",dist.toString());
				
		
		//2((x+(1/2))+5)
		ConstEntiere cinq = new ConstEntiere(5);
		Addition add2 = new Addition(add,cinq);
		Multiplication mult2 = new Multiplication(deux,add2);
		Distribution dist2 = new Distribution(mult2);
		assertEquals("((2*((x+(1/2))+5)))",dist2.toString());
				
		
		//2(x-(1/2))
		Soustraction sous = new Soustraction(var,unDemi);
		Multiplication mult3 = new Multiplication(deux, sous);
		Distribution dist3 = new Distribution(mult3);
		assertEquals("((2*(x-(1/2))))",dist3.toString());
				
		//2*((x+(1/2))-5)
		Soustraction sous2 = new Soustraction(add,cinq);
		Multiplication mult4 = new Multiplication (deux,sous2);
		Distribution dist4 = new Distribution(mult4);
		assertEquals("((2*((x+(1/2))-5)))",dist4.toString());
			
		//2*((x-(1/2))-5)
		Soustraction sous3 = new Soustraction(sous,cinq);
		Multiplication mult5 = new Multiplication (deux,sous3);
		Distribution dist5 = new Distribution(mult5);
		assertEquals("((2*((x-(1/2))-5)))",dist5.toString());
		
				
		//2*((x-(1/2))+5)
		Addition add3 = new Addition(sous, cinq);
		Multiplication mult6 = new Multiplication (deux,add3);
		Distribution dist6 = new Distribution(mult6);
		assertEquals("((2*((x-(1/2))+5)))",dist6.toString());
			
		
			
		//2*(5+(x+(1/2)))
		Addition add4 = new Addition(cinq, add);
		Multiplication mult7 = new Multiplication (deux,add4);
		Distribution dist7 = new Distribution(mult7);
		assertEquals("((2*(5+(x+(1/2)))))",dist7.toString());
				
		
				
		//2*(5-(x-(1/2)))
		Soustraction sous4 = new Soustraction(cinq, sous);
		Multiplication mult8 = new Multiplication (deux,sous4);
		Distribution dist8 = new Distribution(mult8);
		assertEquals("((2*(5-(x-(1/2)))))",dist8.toString());
			
		
		//2*(5-(x+(1/2)))
		Soustraction sous5 = new Soustraction(cinq, add);
		Multiplication mult9 = new Multiplication (deux,sous5);
		Distribution dist9 = new Distribution(mult9);
		assertEquals("((2*(5-(x+(1/2)))))",dist9.toString());
				
			
		//2*(5+(x-(1/2)))
		Addition add5 = new Addition(cinq, sous);
		Multiplication mult10 = new Multiplication (deux,add5);
		Distribution dist10 = new Distribution(mult10);
		assertEquals("((2*(5+(x-(1/2)))))",dist10.toString());

	}
	
	
}
