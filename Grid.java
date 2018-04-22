import java.util.Random;

public class Grid  {
	/*Cette classe contient toutes les methodes qui sont relatives a la grille du jeu en elle-meme,
	 *  pas aux cellules */
	
int size;
int secondsize;
private Cell [][] grid;
boolean [] findNeighbors = new boolean [9];


	public Grid (int n,int m) throws ErrorsException { 
		size = n;
		secondsize=m;
	
		if (abs(m)%2 != abs(n)%2)
			/*On utilise la valeur absolue pour que, si m ou n est negatif, l'operateur modulo
			 * soit toujours adequat pour la verification de la parite */
			throw new ErrorsException(0);
		if (m>n)
			throw new ErrorsException(1);
		if (m<=0 || n<=0)
			throw new ErrorsException(2);
		if (m>500 || n>500)
			throw new ErrorsException(3);
		    
		/*Le jeu ne fonctione pas si m et n ont certaines valeurs particulieres ou ne sont pas
		 * compatibles*/
	
		grid = new Cell[size][size];	
	
	
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
		
				if (i<(n-m)/2 || j <(n-m)/2 || i>=(n+m)/ 2 || j>=(n+m)/2) {
					if (j==0 || j==size-1) {
					/*On considere les cellules des bords droits et gauches de la grille */						
						
						grid[i][j] = new BorderCell(i, j, false, this);
					
					}
					else 
						grid[i][j] = new Cell (i, j, false, this);
				}
				else {
					Random rand = new Random();
					boolean randState= rand.nextBoolean();
					if (j==0 || j==size-1 ) {
						grid[i][j] = new BorderCell(i, j, randState, this);
						/*Le but ici est que, si m=n, on ait quand meme des 
						 * cellules de type BorderCell*/
	
							if (randState == true)
								((BorderCell)this.grid[i][j]).nb=2;	
								/*L'initialisation est comptee comme un changement
								 * d'etat si pour les cellules des bords */
							
					}
					
					else {					
						grid [i][j]=  new Cell(i, j, randState, this);
					}
				}
			}
		}
	}

	private int abs(int x) {
		//Methode qui calcule la valeur absolue d'un nombre 
		if(x<0)
			x = -x;
		return x;
	}

	public Cell[] findNeighbors (int row, int column) {
		/*Cette methode renvoie a la celulle un vecteur compose de ses 8 voisins (et d'elle meme)
		* pour qu'elle puisse apres compter le nombre de ses voisins vivants*/
	
		Cell[] neighbors = new Cell[9];
		int k=0;
		for (int i=row-1; i<=row+1; i++){
			for (int j=column-1; j<=column+1; j++) {
				if (i<0 || j<0 || i>=size || j>=size)
					neighbors[k]= null;
					/*Si la cellule n'a pas de voisins a droite, a gauche, en haut, ou en bas, 
					* la cellule correspondante sera de type Null*/
				
				else 
					neighbors[k]=this.grid[i][j];		
					k++;
			}
		}
		return neighbors;
	}

	public void newGeneration() {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++){
				this.grid[i][j].findNextState();
			}
		}
		/*On trouve les nouveaus State de chaque cellule en fonction des State courants des 
		 * cellules voisines*/
	
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++){
				this.grid[i][j].update();
			}
		}
		/*On change ensuite le State de chaque cellule ce qui permet d'obtenir les cellules de la
		 * nouvelle generation */
	}

	public int modified (int row, int column) {

		int count = ((BorderCell)this.grid[row][column]).count();
		return count;
	}


	public boolean[][] getGrid(){
		/* Cette methode permet d'obtenir la grille de booleens qui correspondent aux etats de
		* chaque cellule*/
	
		boolean[][] sta = new boolean [size][size];
		for (int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				sta[i][j]= this.grid[i][j].getState();
			}
		}
		return sta;
	}

}
