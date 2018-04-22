import java.util.Random;

/*
 * Cette classe permet de gerer la grille, c-a-d sa creation, sa mise a jour et son affichage 
 */

public class Grid {
	
	private int size;
	private Cell[][] grid;
	private int subsize;
	
	public Grid(int n, int m) throws SizeException {
		
		// size est la taille de la grille complete et subsize est la taille de la grille centrale
		size = n;
		subsize = m;
		
		// On verifie que les tailles sont compatibles
		if(n<m && (n>0 && m>0)) {
			throw new SizeException(0);
		}
		
		if(Math.abs(n)%2 != Math.abs(m)%2) {
			throw new SizeException(1);	
		}
		
		if(n<0 || m<0) {
			throw new SizeException(2);
		}
		
		grid = new Cell[this.size][this.size];
	
		// On va remplir la grille que de cases mortes
		for(int i=0; i<this.size; i++) {
			for (int j=0; j<this.size; j++){
				if(i==0 || j==0 || i==this.size-1 || j==this.size-1)
					this.grid[i][j] = new BorderCell(i, j, "Dead", "Unknown","None", 0, this);
				else
					this.grid[i][j] = new Cell(i, j, "Dead", "Unknown","None", 0, this);
			}
		}
		
		// On cree la partie centrale de la grille
		for(int indexRow = 0; indexRow < this.subsize; indexRow ++) {
			for (int indexColumn = 0; indexColumn < this.subsize; indexColumn++) {
						
				Random rand= new Random(); // Donne 0 ou 1 au hasard
				int draw = rand.nextInt(2);
						
				if (draw == 0)
					this.grid[((this.size-this.subsize)/2)+indexRow][((this.size-this.subsize)/2)+indexColumn].state = "Dead";
				else 
					this.grid[((this.size-this.subsize)/2)+indexRow][((this.size-this.subsize)/2)+indexColumn].state = "Alive";

			}
		}
	}
	int getSize() {
		return size;
	}
	
	Cell[][] getGrid() {
		return grid;
	}
	
	public void updateGrid(Grid grid) {
		// Mise a jour de la grille
		
		for (int i=0; i<this.size; i++) {
			for(int j=0; j<this.size; j++) {
				this.grid[i][j].setNextState();
			}
		}
		
		for (int i=0; i<this.size; i++) {
			for(int j=0; j<this.size; j++) {
				this.grid[i][j].updateCell();
			}
		}
	}
	
	public void displayGrid() {
		// Affichage de la grille et des compteurs
		
		for(int indexRow=0; indexRow<this.size; indexRow++) {
			// Affichage du compteur a gauche
			String left = new String();
			left += "[+";
			left += String.format("%2d", this.bornCells()[indexRow][0]) + " / -";
			left += String.format("%2d", this.deadCells()[indexRow][0]) + " ]";
			System.out.print(left);
			
			for(int indexColumn = 0; indexColumn < this.size; indexColumn++) {
				
				if (this.grid[indexRow][indexColumn].getState().equals("Alive"))
					System.out.print(" * ");
				else
					System.out.print(" - ");
			}
			
			// Affichage du compteur a droite
			String right = new String();
			right += "[+";
			right += String.format("%2d", this.bornCells()[indexRow][1]) + " / -";
			right += String.format("%2d", this.deadCells()[indexRow][1]) + " ]";
			System.out.print(right);
			
			System.out.print("\n");
					
			}
		
	}
	
	public int countChange(int row, int column) {
		/* Pour savoir si la cellule est nee ou morte au cours de la mise a jour 
		 * de la grille
		 */
		int cpt = ((BorderCell) grid[row][column]).changeState();
		return cpt;
		
	}
	
	public int[][] deadCells() {
		// On compte les cellules mortes au cours de la mise à jour
		
		int[][] dead= new int[this.size][2];
		
		for (int i=0; i<this.size; i++) {
			
			if (this.countChange(i, 0) == -1)
				dead[i][0] = 1;		
			else 
				dead[i][0] = 0;
		}
		
		
		for (int i=0; i<this.size; i++) {
			
			if (this.countChange(i, this.size-1) == -1)
				dead[i][1] = 1;
			else 
				dead[i][1] = 0;
		}
		return dead;
	}
	
	
	public int[][] bornCells() {
	// On compte les cellules qui sont nees au cours de la mise a jour
		
		int[][] born = new int[this.size][2];
		
		for (int i=0; i<this.size; i++) {
			
			if (this.countChange(i, 0) == 1)
				born[i][0] = 1 ;
			else 
				born[i][0] = 0;
		}
		
		for (int i=0; i<this.size; i++) {
			
			if (this.countChange(i, this.size-1) == 1)
				born[i][1] = 1;
			else 
				born[i][1] = 0;
		}
		return born;
	}	
		
} // fin de la classe Grid