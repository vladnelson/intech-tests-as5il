package jardin.flore;

import java.util.AbstractMap.SimpleEntry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BetteraveTest {
	
	@Test
	public void testSeDupliquer() {
		//Arrange
		Betterave betterave = new Betterave();
		int longueur = 5;
		int largeur = 3;
		
		//Act
		SimpleEntry<Integer, Integer> result = betterave.seDupliquer(longueur, largeur);
		
		//Assert
		boolean xResult = result.getKey() >= 0 && result.getKey() < longueur;
		boolean yResult = result.getValue() >= 0 && result.getValue() < largeur;
		
		Assertions.assertTrue(xResult && yResult);
		
		Assertions.assertEquals(Etat.GRAINE, betterave.getEtat());
	}
	

}
