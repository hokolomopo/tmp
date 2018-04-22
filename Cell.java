/*
 * Cette classe permet de gerer une cellule, c-a-d gerer ses voisins, 
 * ses etats et sa mise a jour 
 */
public class Cell {

	protected int column;
	protected int row;
	protected String state;
	protected String nextState; 
	protected int nbNeighbors;
	protected Grid grid;
	protected String previousState;
	
	public Cell(int pRow, int pColumn, String pState, String pNextState, String prState, int pNbNeighbors, Grid pGrid) {
		
		column = pColumn;
		row = pRow;
		state = pState;
		nextState = pNextState;
		previousState = prState;
		nbNeighbors = pNbNeighbors;
		grid = pGrid;	
		
	}
	
	public String getState() {
		return state;
	}
	
	public void setNextState() {
		int nbNeighbors = getNbNeighbors();
		if(state =="Alive") {
			if (nbNeighbors == 2 || nbNeighbors == 3) 
				nextState = "Alive";
			
			else 
			nextState = "Dead";
		}
		else {
			if(this.nbNeighbors ==3)
				nextState = "Alive";
			else 
				nextState = "Dead";
		} 
	}
	
	// Mise a jour de la cellule
	public void updateCell() {
		previousState = state;
		state = nextState;		
	}
	
	// Permet de trouver le nombre de voisins de la cellule
	public int getNbNeighbors() {
		
		int leftLimit = -1, rightLimit = 1, topLimit = -1, underLimit = 1;
		int nbNeighbors = 0;
		
		if(this.row == 0)
			topLimit = 0;
		
		if(this.row == this.grid.getSize() -1)
			underLimit = 0;
		
		if(this.column == 0)
			leftLimit = 0;
		
		if(this.column == this.grid.getSize() -1)
			rightLimit = 0;
		
		
		for (int i= topLimit; i<= underLimit; i++) {
			for(int j= leftLimit; j<= rightLimit; j++) {
				
				if (this.grid.getGrid()[(this.row)+i][(this.column)+j].state.equals("Alive"))
					nbNeighbors ++;
			}
		}	
		
		// On ne doit pas compter la cellule dans ses voisins si elle est vivante
		if (this.grid.getGrid()[this.row][this.column].state.equals("Alive")){
			nbNeighbors --;
		}
		
		return nbNeighbors;
	}// fin getNeighbors()	
	
} // fin de la classe Cell