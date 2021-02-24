package poo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.IdentiteRemarquableMapping;
import td3.Multiplication;
import td3.Puissance;
import td3.Soustraction;
import td3.VariableSymbolique;

public class IdentiteRemarquableMappingTest {

	@Test
	public void getIdentiteRemarquableTest(){
		
		IdentiteRemarquableMapping mapIdentiteRemarquable = new IdentiteRemarquableMapping();

		VariableSymbolique a=new VariableSymbolique("a");
		ConstEntiere deux=new ConstEntiere(2);
		Puissance aCarre=new Puissance(a,deux);

		VariableSymbolique b=new VariableSymbolique("b");
		Puissance bCarre=new Puissance(b,deux);

		Multiplication deuxAB=new Multiplication(deux,new Multiplication(a,b));
		
		Addition identiteRemarquableDeveloppeeAddition=new Addition(aCarre,new Addition(deuxAB,bCarre));
		assertEquals("((a+b)^2)",mapIdentiteRemarquable.getIdentiteRemarquable(identiteRemarquableDeveloppeeAddition).toString());
		
		Soustraction identiteRemarquableDeveloppeeSoustraction=new Soustraction(aCarre,new Addition(deuxAB,bCarre));
		assertEquals("((a-b)^2)",mapIdentiteRemarquable.getIdentiteRemarquable(identiteRemarquableDeveloppeeSoustraction).toString());
		
		Soustraction aCarreMoinsBCarre=new Soustraction(aCarre,bCarre);
		assertEquals("((a+b)*(a-b))",mapIdentiteRemarquable.getIdentiteRemarquable(aCarreMoinsBCarre).toString());
	}

}
