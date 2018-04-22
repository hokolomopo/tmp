import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameOfLife {
	/*Cette classe reprend donc le jeu dans son integralite, 
	 * elle permet simplement de lancer le jeu et de le faire fonctionner
	 */

	public static void main(String[] args) throws InterruptedException, ErrorsException {
	
		Grid game = null;
		
		try {
			
			Scanner str = new Scanner(System.in);
		
			System.out.println("Veuillez choisir la taille du jeu :");
			int n = str.nextInt();

		
			System.out.println("Veuillez choisir la taille de la petite grille :");
			int m = str.nextInt();	
		
			game = new Grid (n,m);
		
			str.close();
		}
		catch (ErrorsException e) {
		
		}
		while (true) {
	    	/*On cree cette boucle pour que, si les parametres entres ne sont  
	    	 * pas compatibles avec le jeu, le jeu redemarre en demandant de nouvelles valeurs de
	    	 *  parametres */
	    	
	    	if (game==null) {
	    		try {
	    			
				Scanner str = new Scanner(System.in);
	    			
	    			System.err.println("");
			
	    			TimeUnit.MILLISECONDS.sleep(750);
	    			
	    			System.out.println("Veuillez choisir une nouvelle taille de jeu :");
	    			int n = str.nextInt();
			
	    			System.out.println("Veuillez choisir une nouvelle taille de petite grille :");
	    			int m = str.nextInt();	
	    			    			
	    			game = new Grid (n,m);	 
	    			
	    			str.close();
			
	    		}
	    		catch (ErrorsException e) {
			
	    		}
	    	}
	  
	    	int nbGenerations = 1; 
	    	
	    	if (game!= null) {
		
	    		Interface disp = new Interface(game);   		
	    		disp.display();
	    		System.out.println("Generation number : " + nbGenerations);
	    		nbGenerations +=1; 
		
					while (true) {
							TimeUnit.SECONDS.sleep(2);
							/*On utilise une boucle infinie avec un intervalle de temps entre 2
							 * generations pour une meilleure visibilite*/
		
							System.out.println("");
							game.newGeneration();
							disp.display();	
							
							System.out.println("Generation number : " + nbGenerations);
							nbGenerations +=1; 
							
							/*On affiche simplement le nombre de generations passees dans 
							 * le jeu */					
				    }
	    	}
		}	
	}
}

	
