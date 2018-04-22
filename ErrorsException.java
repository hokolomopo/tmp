
public class ErrorsException extends Exception {
	 
	private static final long serialVersionUID = 1L;

	/*Cette classe va traiter des Exception, lorsque les valeurs des parametres m et n choisis ne
	 * sont pas acceptables*/

	public ErrorsException(int err) {
		
		switch (err) {
		 
		case 0: 
			System.err.println("Attention : les 2 tailles doivent etre de meme parite");
			break;
			
		case 1 : 
			System.err.println("Attention : la taille de la petite grille doit etre inferieure "
					+ "ou egale a la taille du jeu");
			break;
			 
		case 2:
			System.err.println("Attention : les 2 tailles doivent etre superieures a 0");
			break;
			
		case 3:
			System.err.println("Attention : vous avez choisi des tailles trop elevees");
			
			/*Si les tailles choisies sont trop elevees, le code a du mal a tourner et,
			 *de plus, le jeu n'a pas d'interet quand les tailles sont si grandes (visuellement
			 *difficile a observer) */
			
			break;
		}
		
	}

}
