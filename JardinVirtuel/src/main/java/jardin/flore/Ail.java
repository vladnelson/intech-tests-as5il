package jardin.flore;

import java.util.HashMap;

public class Ail extends Vegetal implements IRacePure {

	public Ail() {
		super();
		dessin[3] = 'a';
		dessin[4] = 'A';
	}

	@Override
	public void seReproduire(HashMap<String, Integer> panier) {
		panier.put("Ail", panier.getOrDefault("Ail",0) + 3);
	}

}