package jardin.flore;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AilTest {
	
	@Test
	public void testSeReproduirePanierVide() {
		//Arrange
		HashMap<String, Integer> panier = new HashMap<>();
		IRacePure ail = new Ail();
		
		//Act
		ail.seReproduire(panier);
		
		//Assert		
		Assertions.assertEquals(3, panier.get("Ail"));
		
	}
	
	@Test
	public void testSeReproduirePanierNonVide() {
		//Arrange
		HashMap<String, Integer> panier = new HashMap<>();
		panier.put("Ail", 2);
		IRacePure ail = new Ail();
		
		//Act
		ail.seReproduire(panier);
		
		//Assert		
		Assertions.assertEquals(5, panier.get("Ail"));
		
	}
	

}
