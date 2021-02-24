package poo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.ExpressionArithmetique;
import td3.LogarithmeNeperien;
import td3.LogarithmeNeperienNegatifOuNulException;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class LogarithmeNeperienNegatifOuNulExceptionTest {


	@Test
	public void LogarithmeNegatifOuNulExceptionTest1() {


		try {
			ConstEntiere zero=new ConstEntiere(0);
			LogarithmeNeperien logDeZero= new LogarithmeNeperien(zero);
		}catch(LogarithmeNeperienNegatifOuNulException e){
			assert(e.getMessage().contains("Le logarithme d'un nombre négatif n'existe pas"));
		}catch(Exception e) {

		}
		
		try {
			ConstEntiere zero=new ConstEntiere(-6);
			LogarithmeNeperien logDeMoinsSix= new LogarithmeNeperien(zero);
		}catch(LogarithmeNeperienNegatifOuNulException e){
			assert(e.getMessage().contains("Le logarithme d'un nombre négatif n'existe pas"));
		}catch(Exception e) {

		}
		
		try {
			ConstRationnelle zero=new ConstRationnelle(-1,9);
			LogarithmeNeperien logDeZero= new LogarithmeNeperien(zero);
		}catch(LogarithmeNeperienNegatifOuNulException e){
			assert(e.getMessage().contains("Le logarithme d'un nombre négatif n'existe pas"));
		}catch(Exception e) {

		}
		
		
		try {
			ConstRationnelle zero=new ConstRationnelle(1,-9);
			LogarithmeNeperien logDeZero= new LogarithmeNeperien(zero);
		}catch(LogarithmeNeperienNegatifOuNulException e){
			assert(e.getMessage().contains("Le logarithme d'un nombre négatif n'existe pas"));
		}catch(Exception e) {

		}
		
		
    	List<VariableSymbolique> liste = new ArrayList<VariableSymbolique>();
		VariableSymbolique x=new VariableSymbolique("x");
		VariableSymbolique y=new VariableSymbolique("y");
    	liste.add(x);
    	liste.add(y);
    	
    	List<ExpressionArithmetique> liste2 = new ArrayList<ExpressionArithmetique>();
    	ConstEntiere zero= new ConstEntiere(0);
    	ConstEntiere moinsHuit= new ConstEntiere(-8);
    	liste2.add(zero);
    	liste2.add(moinsHuit);
    	
    	VariableSymboliqueMapping map = new VariableSymboliqueMapping(liste,liste2);
    	
		try {
			LogarithmeNeperien logDeX= new LogarithmeNeperien(new ConstEntiere((int) x.calculer(map)));
		}catch(LogarithmeNeperienNegatifOuNulException e){
			assert(e.getMessage().contains("Le logarithme d'un nombre négatif n'existe pas"));
		}catch(Exception e) {

		}
		
		try {
			LogarithmeNeperien logDeY= new LogarithmeNeperien(new ConstEntiere((int) y.calculer(map)));
		}catch(LogarithmeNeperienNegatifOuNulException e){
			assert(e.getMessage().contains("Le logarithme d'un nombre négatif n'existe pas"));
		}catch(Exception e) {

		}
		
	}
	
	/*
	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionMoinsQuatre() throws Exception{
		ConstEntiere moinsQuatre=new ConstEntiere(-4);
		LogarithmeNeperien ln=new LogarithmeNeperien(moinsQuatre);
	}

	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionZero() throws Exception{
		ConstEntiere zero=new ConstEntiere(0);
		LogarithmeNeperien ln=new LogarithmeNeperien(zero);   
	}


	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionNeg() throws Exception{
		ConstRationnelle MoinsunSurDeux= new ConstRationnelle(-1,2);
		LogarithmeNeperien ln=new LogarithmeNeperien(MoinsunSurDeux); 
	} 

	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionNeg() throws Exception{
		ConstRationnelle unSurMoinsDeux= new ConstRationnelle(1,-2);
		LogarithmeNeperien ln=new LogarithmeNeperien(unSurMoinsDeux); 
	}

	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionZero() throws Exception{
		ConstRationnelle ZeroSurDeux= new ConstRationnelle(0,2);
		LogarithmeNeperien ln=new LogarithmeNeperien(ZeroSurDeux); 
	}


	List<VariableSymbolique> liste = new ArrayList<VariableSymbolique>();
	VariableSymbolique x=new VariableSymbolique("x");
	VariableSymbolique y=new VariableSymbolique("y");
	liste.add(x);
	liste.add(y);

	List<ExpressionArithmetique> liste2 = new ArrayList<ExpressionArithmetique>();
	ConstEntiere zero= new ConstEntiere(0);
	ConstEntiere moinsHuit= new ConstEntiere(-8);
	liste2.add(zero);
	liste2.add(moinsHuit);

	VariableSymboliqueMapping map = new VariableSymboliqueMapping(liste,liste2);

	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionZero() throws Exception{
		LogarithmeNeperien ln=new LogarithmeNeperien(new ConstEntiere((int) x.calculer(map))); 
	}
	@Test (expected = VaNeg() throws Exception{
		LogarithmeNeperien ln=new LogarithmeNeperien(new ConstEntiere((int) y.calculer(map))); 
	}
	*/
}
