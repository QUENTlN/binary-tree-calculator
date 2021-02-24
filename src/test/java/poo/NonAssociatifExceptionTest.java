package poo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import td3.Addition;
import td3.Associativite;
import td3.ConstEntiere;
import td3.NonAssociatifException;
import td3.Soustraction;
import td3.VariableSymbolique;

public class NonAssociatifExceptionTest{

	@Test
	public void nonAssociatif() {
		
		try {//marche
			VariableSymbolique y = new VariableSymbolique("y");
			ConstEntiere trois = new ConstEntiere(3);
			ConstEntiere quatre =new ConstEntiere(4);
			Addition add = new Addition(trois, y);
			Soustraction addd = new Soustraction(quatre, add);
			Associativite asso = new Associativite (addd);
			String s=asso.simplifier().toString();
		}catch(NonAssociatifException e){
			assert(e.getMessage().contains("Cette expression n'est pas associative"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
