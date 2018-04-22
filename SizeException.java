/* 
 * Cette classe permet de vérifier que les tailles entrees pour les differentes grilles 
 * sont bien compatibles, c-a-d que la taille de la sous-grille doit etre plus petite 
 * que celle de la grande grille, elles doivent toutes les  deux avoir la meme parite et 
 * doivent etre positives
 */
public class SizeException extends Exception{
	
	public static final long serialVersionUID = 1L;
	public SizeException(int e) {
		
		switch(e) {
		
		case(0):
			System.err.println("Vous avez entre une taille de grille plus petite que celle de la sous-grille!");
			break;	
		
		case(1):
			System.err.println("Les tailles des différentes grilles doivent toutes deux etre paires ou impaires!");
			break;
			
		case(2):
			System.err.println("Les tailles des grilles doivent être positives!");
			break;
			
		}
	}
} // Fin de la classe SizeException