import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class GameOfLife {

	public static void main(String[] args) throws SizeException, InterruptedException {
		
		int subsize = 0, size = 0;
		Grid jeu = null;
		Scanner sc = null;
		
		// Initialisation du jeu 
		
		try {
		sc = new Scanner(System.in);
		
		System.out.println("Veuillez saisir la taille de la grille : ");
		size = sc.nextInt();
		
		System.out.println("Veuillez saisir la taille de la region centree de depart : ");
		subsize = sc.nextInt();	
		
		jeu = new Grid(size, subsize);
		
		}
		
		catch(SizeException e) {
			
		}
		
		finally {
			sc.close();
		}
		
		if (jeu != null) {
			
			jeu.displayGrid();			 
				
			// On fait une boucle infinie pour faire le jeu
			while(true) {
			
				// On met un intervalle de 1 seconde entre chaque mise a jour de grille
				TimeUnit.SECONDS.sleep(1);
			
				// On passe une ligne a la fin de chaque affichage de la grille
				System.out.print("\n");
			
				//Mise a jour de la grille
				jeu.updateGrid(jeu);
			
				// Affichage de la grille
				jeu.displayGrid();
			
			}
		}
	}
}