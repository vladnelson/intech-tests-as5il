package jardin.flore;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarotteTest {
	
	@Test
	public void testSeReproduirePanierVide() {
		//Arrange
		HashMap<String, Integer> panier = new HashMap<>();
		IRacePure carotte = new Carotte();
		
		//Act
		carotte.seReproduire(panier);
		
		//Assert		
		Assertions.assertEquals(3, panier.get("Carotte"));
		
	}
	
	@Test
	public void testSeReproduirePanierNonVide() {
		//Arrange
		HashMap<String, Integer> panier = new HashMap<>();
		panier.put("Carotte", 1);
		IRacePure carotte = new Carotte();
		
		//Act
		carotte.seReproduire(panier);
		
		//Assert		
		Assertions.assertEquals(4, panier.get("Carotte"));
		
	}
	

}
