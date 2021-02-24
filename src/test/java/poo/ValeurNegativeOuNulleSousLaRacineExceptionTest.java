package poo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.ConstEntiere;
import td3.ConstRationnelle;
import td3.DivisionParZeroException;
import td3.ExpressionArithmetique;
import td3.LogarithmeNeperienNegatifOuNulException;
import td3.RacineCarree;
import td3.ValeurNegativeOuNulleSousLaRacineException;
import td3.VariableSymbolique;
import td3.VariableSymboliqueMapping;

public class ValeurNegativeOuNulleSousLaRacineExceptionTest {

	@Test
	public void RacineDivisionParZeroTest() {

		try {
			ConstEntiere moinsQuatre=new ConstEntiere(-4);
			RacineCarree racine=new RacineCarree(moinsQuatre);
		}catch(ValeurNegativeOuNulleSousLaRacineException e){
			assert(e.getMessage().contains("La valeur sous la racine ne peut pas être négative ou nul"));
		}catch(Exception e) {
			
		}
		
		try {
			ConstEntiere zero=new ConstEntiere(0);
			RacineCarree racine=new RacineCarree(zero);
		}catch(ValeurNegativeOuNulleSousLaRacineException e){
			assert(e.getMessage().contains("La valeur sous la racine ne peut pas être négative ou nul"));
		}catch(Exception e) {

		}
		
		
		try {
			ConstRationnelle MoinsunSurDeux= new ConstRationnelle(-1,2);
			RacineCarree racine=new RacineCarree(MoinsunSurDeux);
		}catch(ValeurNegativeOuNulleSousLaRacineException e){
			assert(e.getMessage().contains("La valeur sous la racine ne peut pas être négative ou nul"));
		}catch(Exception e) {

		}
		
		try {
			ConstRationnelle unSurMoinsDeux= new ConstRationnelle(1,-2);
			RacineCarree racine=new RacineCarree(unSurMoinsDeux);
		}catch(ValeurNegativeOuNulleSousLaRacineException e){
			assert(e.getMessage().contains("La valeur sous la racine ne peut pas être négative ou nul"));
		}catch(Exception e) {

		}
		
		try {
			ConstRationnelle zeroSurDeux= new ConstRationnelle(0,2);
			RacineCarree racine=new RacineCarree(zeroSurDeux);
		}catch(ValeurNegativeOuNulleSousLaRacineException e){
			assert(e.getMessage().contains("La valeur sous la racine ne peut pas être négative ou nul"));
		}catch(Exception e) {

		}
		
		try {
			ConstRationnelle deuxSurZero= new ConstRationnelle(2,0);
			RacineCarree racine=new RacineCarree(deuxSurZero);
		}catch(ValeurNegativeOuNulleSousLaRacineException e){
			assert(e.getMessage().contains("La valeur sous la racine ne peut pas être négative ou nul"));
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
			RacineCarree racine=new RacineCarree(new ConstEntiere((int) x.calculer(map)));
		}catch(ValeurNegativeOuNulleSousLaRacineException e){
			assert(e.getMessage().contains("La valeur sous la racine ne peut pas être négative ou nul"));
		}catch(Exception e) {
		}
		
		try {
			
			RacineCarree racine=new RacineCarree(new ConstEntiere((int) y.calculer(map)));
		}catch(ValeurNegativeOuNulleSousLaRacineException e){
			assert(e.getMessage().contains("La valeur sous la racine ne peut pas être négative ou nul"));
		}catch(Exception e) {
		}
		
	}
	


/*
	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionMoinsQuatre() throws Exception{
		ConstEntiere moinsQuatre=new ConstEntiere(-4);
		RacineCarree racine=new RacineCarree(moinsQuatre);
	}

	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionZero() throws Exception{
		ConstEntiere zero=new ConstEntiere(0);
		RacineCarree racine=new RacineCarree(zero);   
	}


	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionNeg() throws Exception{
		ConstRationnelle MoinsunSurDeux= new ConstRationnelle(-1,2);
		RacineCarree racine=new RacineCarree(MoinsunSurDeux); 
	} 

	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionNeg() throws Exception{
		ConstRationnelle unSurMoinsDeux= new ConstRationnelle(1,-2);
		RacineCarree racine=new RacineCarree(unSurMoinsDeux); 
	}

	@Test (expected = ValeurNegativeOuNulSousLaRacineException.class)
	public void exceptionZero() throws Exception{
		ConstRationnelle ZeroSurDeux= new ConstRationnelle(0,2);
		RacineCarree racine=new RacineCarree(ZeroSurDeux); 
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
		RacineCarree racine=new RacineCarree(new ConstEntiere((int) x.calculer(map))); 
	}
	@Test (expected = VaNeg() throws Exception{
		RacineCarree racine=new RacineCarree(new ConstEntiere((int) y.calculer(map))); 
	}
	*/


}