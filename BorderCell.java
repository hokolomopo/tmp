
public class BorderCell extends Cell {
	/* La classe BorderCell herite de la classe Cell pour les cellules des 
	 * bords droits et gauches 
	 */
	
	
	public int nb; 

	public BorderCell (int r, int c, boolean s, Grid g) {
		super (r, c, s, g);
		
	}
	
		@Override public void update() {
		/* Utilisation de Override pour pouvoir utiliser la methode update
		 * appartenant a Cell mais en y apportant une modification propre
		 * aux cellules du bord de la grille
		 */
		
		nb = differentState();		
		
		/* On voit si l'etat des cellules des bords droits et gauches de la
		 * grille ont changes, avant que le nextState ne devienne le State courant
		 */
		state=nextState;
	    }
	
	public int differentState() {
		
		int change;
		if (state != nextState) {
			if(state==false)
				change = 2;
			/* On choisit de mettre 1 si la cellule meurt et 2 si la cellule vit
			 */
			else 
				change = 1;
					}
		else 
			change = 0;
		return change;
	}
	
	public int count () {
		
		return nb;
		//On fait une methode qui retourne le changement d'etat de la cellule 
	}
		
}
