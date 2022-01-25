package jardin;

import java.util.Scanner;

public class Terre {

	public static void main(String[] args) {
		
		Jardin j = new Jardin(3, 5);
		j.ajouterPanier("Tomate", 5);
		j.ajouterPanier("Betterave", 3);
		j.ajouterPanier("Carotte", 4);
		j.ajouterPanier("Ail", 1);
		
		int choice = 0;
		
		do {
			
			System.out.println(j);
			
			System.out.println("1 : Semer");
			System.out.println("2 : Recolter");
			System.out.println("3 : Saison suivante");
			System.out.println("4 : Quitter");
			
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				j.semer();
				break;
			case 2:
				j.recolter();
				break;
			case 3:
				j.passerSaisonSuivante();
				break;
			}

		} while(choice!=4);
		
	}

}