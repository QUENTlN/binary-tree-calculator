package poo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import td3.Addition;
import td3.ConstEntiere;
import td3.ExpressionArithmetique;
import td3.Matrice;
import td3.OperationMatricielle;
import td3.ProduitMatricielle;
import td3.SommeMatricielle;
import td3.VariableSymbolique;
import td3.VariableSymboliqueIndexee;
import td3.VariableSymboliqueMapping;

public class MatriceTest {
	
	@Test
	public void MatriceTestCalcul() throws Exception {
		VariableSymbolique x = new VariableSymbolique("x");
		List<VariableSymbolique> listeTest = new ArrayList<VariableSymbolique>();
		listeTest.add(x);

		ExpressionArithmetique valeur1 = new ConstEntiere(2);
		List<ExpressionArithmetique> listeTest2 = new ArrayList<ExpressionArithmetique>();
		listeTest2.add(valeur1);

		VariableSymboliqueMapping test = new VariableSymboliqueMapping(listeTest, listeTest2);

		ConstEntiere nb1 = new ConstEntiere(1);
		ConstEntiere nb2 = new ConstEntiere(2);
		ConstEntiere nb3 = new ConstEntiere(3);
		ConstEntiere nb4 = new ConstEntiere(4);

		ExpressionArithmetique[][] tabMat1 = new ExpressionArithmetique[][] { { nb1, new Addition(nb2, nb1) },
				{ nb3, nb4 } };

		Matrice mat1 = new Matrice(tabMat1);

		assertEquals("[1.0, 3.0]\n[3.0, 4.0]\n", OperationMatricielle.affichercalcul(mat1.calculer(test)));
		
		ExpressionArithmetique[][] tabMat2 = new ExpressionArithmetique[][] { { nb4, x }, { nb4, nb4 } };

		Matrice mat2 = new Matrice(tabMat2);

		assertEquals("[4.0, 2.0]\n[4.0, 4.0]\n", OperationMatricielle.affichercalcul(mat2.calculer(test)));

		SommeMatricielle sommeMat = new SommeMatricielle(mat1, mat2);
		
		assertEquals("[5.0, 5.0]\n[7.0, 8.0]\n", OperationMatricielle.affichercalcul(sommeMat.calculer(test)));

		ProduitMatricielle prodMat = new ProduitMatricielle(mat1, mat2);
		
		assertEquals("[16.0, 14.0]\n[28.0, 22.0]\n", OperationMatricielle.affichercalcul(prodMat.calculer(test)));
	}
	
	@Test
	public void MatriceTestSimplifier() throws Exception {
		
		VariableSymbolique x = new VariableSymbolique("x");
		ConstEntiere nb1 = new ConstEntiere(1);
		ConstEntiere nb2 = new ConstEntiere(2);
		ConstEntiere nb3 = new ConstEntiere(3);
		ConstEntiere nb4 = new ConstEntiere(4);

		ExpressionArithmetique[][] tabMat1 = new ExpressionArithmetique[][] { { nb1, new Addition(nb2, nb1) },
				{ nb3, nb4 } };

		Matrice mat1 = new Matrice(tabMat1);
		
		assertEquals("[1, 3]\n[3, 4]\n", mat1.simplifier().toString());

		ExpressionArithmetique[][] tabMat2 = new ExpressionArithmetique[][] { { nb4, x }, { nb4, nb4 } };

		Matrice mat2 = new Matrice(tabMat2);
		
		assertEquals("[4, x]\n[4, 4]\n", mat2.simplifier().toString());

		SommeMatricielle sommeMat = new SommeMatricielle(mat1, mat2);
		
		assertEquals("[5, (3+x)]\n[7, 8]\n", sommeMat.simplifier().toString());

		ProduitMatricielle prodMat = new ProduitMatricielle(mat1, mat2);
		
		assertEquals("[16, (x+12)]\n[28, ((3*x)+16)]\n", prodMat.simplifier().toString());
	}
}
