package poo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.DerivationPolynome;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.PolynomeNonDerivableException;
import td3.Puissance;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class PolynomeNonDerivableExceptionTest {

	@Test
	public void RacineDivisionParZeroTest() {
		try {//marche
			VariableSymbolique x= new VariableSymbolique("x");
		    ///4+x^4
		    ConstEntiere quatre = new ConstEntiere(4);
		    Puissance puiss1 = new Puissance(quatre,x);
		    Addition add = new Addition(quatre, puiss1);
			DerivationPolynome calculerPolynome = new DerivationPolynome(add);
			ExpressionArithmetique s=calculerPolynome.deriver();
			System.out.println(s);
		}catch(PolynomeNonDerivableException e){
			assert(e.getMessage().contains("Ce polynôme ne peut pas être dérivé"));
		}catch(Exception e) {

		}
		
		try {//marche
			VariableSymbolique x= new VariableSymbolique("x");
			VariableSymbolique y= new VariableSymbolique("y");
		    Puissance xPuissanceY = new Puissance(x,y);
			DerivationPolynome calculerPolynome = new DerivationPolynome(xPuissanceY);
			ExpressionArithmetique s=calculerPolynome.deriver();
		}catch(PolynomeNonDerivableException e){
			assert(e.getMessage().contains("Ce polynôme ne peut pas être dérivé"));
		}catch(Exception e) {

		}
		
	}
}
