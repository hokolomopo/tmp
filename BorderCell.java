/*
 * Cette classe herite de Cell est permet de gerer les cellules au bord de la 
 * grille. Elle permet de gerer les compteurs sur les cotes
 */
public class BorderCell extends Cell{
	
	int count;
	
	public BorderCell(int row, int column, String state, String nextState,String previousState, int nbNeighbors, Grid grid) {
		
		super(row, column, state, nextState, previousState, nbNeighbors, grid);
	}
	
	public int changeState() {
		int count = 0;
		
		if (previousState != state) {
			
			if((previousState.equals("Dead") || previousState.equals("None")) && state.equals("Alive"))  
				count = 1;   // la cellule est nee
			
			else if (previousState.equals("Alive") && state.equals("Dead"))
				count = -1;  // la cellule est morte 
		}
		
		else 
			count = 0;   // l'etat de la cellule n'a pas change
		
		return count;
	}
	
	public int getNumber() {
		
		count = changeState();
		return count;
		
	}
	
}// Fin de la classe BorderCell