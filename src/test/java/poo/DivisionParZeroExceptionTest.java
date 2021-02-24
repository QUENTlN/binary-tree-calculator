package poo;

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

public class DivisionParZeroExceptionTest {

	@Test
	public void divisionParZero() {


		try {
			ConstRationnelle sixSurZero= new ConstRationnelle(6,0);
		}catch(DivisionParZeroException e){
			assert(e.getMessage().contains("La division par 0 n'est pas supporté"));
		}catch(Exception e) {

		}

		ConstEntiere trois= new ConstEntiere(3);
		try {
			ConstEntiere zero= new ConstEntiere(0);
			Division division=new Division(trois,zero);
		}catch(DivisionParZeroException e){
			assert(e.getMessage().contains("La division par 0 n'est pas supporté"));
		}catch(Exception e) {

		}


		
		try {
			ConstRationnelle zeroSurTrois= new ConstRationnelle(0,3);
			Division division=new Division(trois,zeroSurTrois);
		}catch(DivisionParZeroException e){
			assert(e.getMessage().contains("La division par 0 n'est pas supporté"));
		}catch(Exception e) {

		}
		
		try {
	    	List<VariableSymbolique> liste = new ArrayList<VariableSymbolique>();
			VariableSymbolique x=new VariableSymbolique("x");
	    	liste.add(x);
	    	List<ExpressionArithmetique> liste2 = new ArrayList<ExpressionArithmetique>();
	    	ConstEntiere zero= new ConstEntiere(0);
	    	liste2.add(zero);
	    	VariableSymboliqueMapping map = new VariableSymboliqueMapping(liste,liste2);
	    	
	    	Division division=new Division(trois,new ConstEntiere((int) x.calculer(map)));
		}catch(DivisionParZeroException e){
			assert(e.getMessage().contains("La division par 0 n'est pas supporté"));
		}catch(Exception e) {

		}
		
		try {
	    	List<VariableSymbolique> liste = new ArrayList<VariableSymbolique>();
			VariableSymbolique y=new VariableSymbolique("y");
	    	liste.add(y);
	    	List<ExpressionArithmetique> liste2 = new ArrayList<ExpressionArithmetique>();
	    	ConstRationnelle zeroSurNeuf= new ConstRationnelle(0,9);
	    	liste2.add(zeroSurNeuf);
	    	VariableSymboliqueMapping map = new VariableSymboliqueMapping(liste,liste2);
	    	
			Division division=new Division(trois,new ConstEntiere((int) y.calculer(map)));
		}catch(DivisionParZeroException e){
			assert(e.getMessage().contains("La division par 0 n'est pas supporté"));
		}catch(Exception e) {

		}

		
	}
	
	
	/*

	@Test (expected = DivisionParZeroException.class)
	public void exceptionZero() throws Exception{
		ConstEntiere zero=new ConstEntiere(0);
		Division div=new Division(zero);   
	}


	@Test (expected = DivisionParZeroException.class)
	public void exceptionZero() throws Exception{
		ConstRationnelle ZeroSurDeux= new ConstRationnelle(0,2);
		Division div=new Division(ZeroSurDeux); 
	}
	
	@Test (expected = DivisionParZeroException.class)
	public void exceptionZero() throws Exception{
		ConstRationnelle deuxSurZero= new ConstRationnelle(2,0);
		Division div=new Division(deuxSurZero); 
	}


	List<VariableSymbolique> liste = new ArrayList<VariableSymbolique>();
	VariableSymbolique x=new VariableSymbolique("x");
	liste.add(x);
	VariableSymbolique x=new VariableSymbolique("y");
	liste.add(y);

	List<ExpressionArithmetique> liste2 = new ArrayList<ExpressionArithmetique>();
	ConstEntiere zero= new ConstEntiere(0);
	liste2.add(zero);
	ConstRationnelle zeroSurTrois= new ConstRationnelle(0/3);
	liste2.add(zero);
	VariableSymboliqueMapping map = new VariableSymboliqueMapping(liste,liste2);

	@Test (expected = DivisionParZeroException.class)
	public void exceptionZero() throws Exception{
		Division div=new Division(trois,new ConstEntiere((int) x.calculer(map); 
	}
	
	@Test (expected = DivisionParZeroException.class)
	public void exceptionZero() throws Exception{
		Division div=new Division(trois,new ConstEntiere((int) y.calculer(map); 
	}
	
	*/
}
