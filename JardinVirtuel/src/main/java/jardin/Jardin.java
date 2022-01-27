package jardin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.AbstractMap.SimpleEntry;

import jardin.flore.Ail;
import jardin.flore.Betterave;
import jardin.flore.Carotte;
import jardin.flore.Etat;
import jardin.flore.IOgm;
import jardin.flore.IRacePure;
import jardin.flore.Tomate;
import jardin.flore.Vegetal;

public class Jardin {

	private int longueur;
	private int largeur;
	private HashMap<String, Integer> panier;
	private Emplacement emplacement[][];
	
	public Emplacement[][] getEmplacement() {
		return emplacement;
	}

	public HashMap<String, Integer> getPanier() {
		return panier;
	}

	public Jardin(int longueur, int largeur) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.panier = new HashMap<String, Integer>();
		this.emplacement = new Emplacement[this.longueur][this.largeur];
	}

	public void ajouterPanier(String nom, Integer quantite) {
		this.panier.put(nom, this.panier.getOrDefault(nom, 0) + quantite);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Voici notre jardin\n");

		for (int x = 0; x < this.longueur; x++) {
			for (int y = 0; y < this.largeur; y++) {
				Emplacement e = emplacement[x][y];
				if (e == null) {
					sb.append("o");
				} else {
					sb.append(e);
				}
			}
			sb.append("\n");
		}

		sb.append("\nEt notre panier contient :");

		TreeSet<String> sortedSet = new TreeSet<String>();
		sortedSet.addAll(panier.keySet());

		Iterator<String> it = sortedSet.iterator();
		while (it.hasNext()) {
			String nom = it.next();
			Integer quantite = this.panier.get(nom);
			sb.append("\n");
			sb.append(nom);
			sb.append(" : ");
			sb.append(quantite);
			sb.append(" graine(s)");
		}
		return sb.toString();
	}

	public void semer() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Indiquer l'axe X");
		int x = sc.nextInt();

		System.out.println("Indiquer l'axe Y");
		int y = sc.nextInt();

		System.out.println("Quel vegetal? (1. Ail , 2. Betterave , 3. Carotte , 4. Tomate");
		int choiceVegetal = sc.nextInt();

		switch (choiceVegetal) {
		case 1:
			if (this.panier.get("Ail") != null && this.panier.get("Ail") > 0) {
				this.emplacement[x][y] = new Emplacement(new Ail());
				this.panier.put("Ail", this.panier.get("Ail") - 1);
			}
			break;
		case 2:
			if (this.panier.get("Betterave") != null && this.panier.get("Betterave") > 0) {
				this.emplacement[x][y] = new Emplacement(new Betterave());
				this.panier.put("Betterave", this.panier.get("Betterave") - 1);
				break;
			}
		case 3:
			if (this.panier.get("Carotte") != null && this.panier.get("Carotte") > 0) {
				this.emplacement[x][y] = new Emplacement(new Carotte());
				this.panier.put("Carotte", this.panier.get("Carotte") - 1);
				break;
			}
		case 4:
			if (this.panier.get("Tomate") != null && this.panier.get("Tomate") > 0) {
				this.emplacement[x][y] = new Emplacement(new Tomate());
				this.panier.put("Tomate", this.panier.get("Tomate") - 1);
				break;
			}
		}
	}

	public void passerSaisonSuivante() {
		for (int x = 0; x < this.longueur; x++) {
			for (int y = 0; y < this.largeur; y++) {
				Emplacement e = emplacement[x][y];
				if (e != null) {
					if (e.getVeg().getEtat() == Etat.MORT) {
						emplacement[x][y] = null;
					} else {
						e.getVeg().grandir();
					}
				}
			}
		}
	}

	public void recolter() {
		
		//Emplacement[][] duplicate = emplacement.clone();
		
		for (int x = 0; x < this.longueur; x++) {
			for (int y = 0; y < this.largeur; y++) {

				Emplacement e = emplacement[x][y];
				if (e != null && e.getVeg().getEtat() == Etat.FLEUR) {
					emplacement[x][y] = null;
					Vegetal veg = e.getVeg();
					if (veg instanceof IRacePure) {
						IRacePure v = (IRacePure) veg;
						v.seReproduire(this.panier);
					} else if (veg instanceof IOgm) {
						IOgm v = (IOgm) veg;
						SimpleEntry<Integer, Integer> simpleEntry = v.seDupliquer(this.longueur, this.largeur);
						emplacement[simpleEntry.getKey()][simpleEntry.getValue()] = new Emplacement(veg);
					}
				}
			}
		}
	}

	public void arroserUnEmplacement(int x, int y) {
		Emplacement e = emplacement[x][y];
		if(e != null && e.getVeg() != null) {
			Vegetal veg = e.getVeg();
			veg.grandir(2);		
		}
	}

}