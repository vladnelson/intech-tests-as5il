package jardin.flore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VegetalTest {
	
	@Test
	public void testGrandir() {
		
		Etat[] etats = {Etat.GRAINE, Etat.GERME, Etat.TIGE, Etat.FEUILLE, Etat.FLEUR, Etat.MORT};
		
		// Arrange
		Vegetal veg = new Vegetal() {	};
		
		for(int i =1; i < etats.length; i++) {
			// Act
			veg.grandir();
			// Assert
			Assertions.assertEquals(etats[i], veg.getEtat());
		}
	}

}
