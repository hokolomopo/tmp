public class Cell {
	/*Cette classe reprend toutes les methodes directement relatives
	 * aux cellules en elles-meme*/
	
	protected int row;
	protected int column;
	protected boolean state;
	protected boolean nextState; 
	protected int size; 
	private Grid grid;

	public Cell(int r, int c, boolean s, Grid g) {
		row = r;
		column = c;
		state = s;
		grid = g;
	}

	public int neighbors() {
		/*Cette methode permet e la celulle de compter combien de ses voisins sont vivants, 
		 *a partir du vecteur compose de ceux-ci*/
	
		Cell[] neighbors = new Cell[9];
		int nbr=0;
		neighbors = this.grid.findNeighbors(row, column);
		for (int i=0; i<9 ; i++) {
			
			if (neighbors[i] != null && neighbors[i].getState()==true) 
					nbr+= 1;
		}
		if (neighbors [4].getState()==true)
			nbr-= 1;	  
		/*Si la cellule en question est vivante, il ne faut pas la compter comme un voisin
		 * vivant*/ 
	
		return nbr;
		}


	public boolean findNextState() {
		/*Cette methode renvoie l'etat suivant de la cellule, sur base du nombre de ses voisins
		 *  vivants */
	
		int numberNeighbors = neighbors();	
	
		if(numberNeighbors<2 || numberNeighbors>=4)
			nextState = false;
		
		
			if(state == true && (numberNeighbors==2 || numberNeighbors==3))
				nextState = true;
		
		if(numberNeighbors==3 && state==false) 
			nextState = true;
		
		return nextState;
	}


	public boolean getState () {	
		return state;
	}

	public void update() {
		state = nextState;
	}

}