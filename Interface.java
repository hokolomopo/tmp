public class Interface {
	/*Cette classe va permettre l'affichage en tant que tel de la grille, des nouvelles generations
	 * ainsi que des compteurs pour les cellules des bords droit et gauche */

	private Grid grid;
	private int[][] alive;
	private int[][] dead;
	
	/*Ces 2 matrices auront sur la 1e colonne le nombre de vivants (ou morts) des BorderCell
	 *de gauche et sur la 2e colonne, le nombre de vivants (ou morts) des BorderCell de droite */

	public Interface (Grid g) {
		grid = g;
		int a[][] = new int [grid.size][2];
		int d[][] = new int [grid.size][2];
		alive = a;
		dead = d;
	}	

	public void display() {
		/*Cette methode va afficher la grille ainsi que les compteurs des cellules vivantes et
		 * mortes sur les bords gauche et droite*/
		
		int count = 0;

		for (int i=0; i<grid.size; i++) {
			for (int j=0; j<grid.size; j++) {
		
				if(j==0) {
					count = grid.modified(i,j);
					
				if (count==1)
					dead[i][0]+=1;
			
				if(count==2)
					alive[i][0]+=1;
				
				
				String disp = new String(); 
				disp += "[+" ;
				disp += String.format ("%3d" , alive[i][0]) + " / -";
				
				disp += String.format ("%3d" , dead[i][0]) + "]";
				
				System.out.print(disp);
				}
				
				if (j==grid.size-1) {
					count = grid.modified(i,j);
					
					if(count==1)
						dead[i][1]+=1;
					
					if(count==2)
						alive[i][1]+=1;
					
				}
	    
				boolean [][] disp = grid.getGrid();
				
				if(disp[i][j]==true)
					System.out.print(" * ");
				
				else
					System.out.print(" - ");
			}

			String disp = new String(); 
			disp += "[+" ;
			disp += String.format ("%3d" , alive[i][1]) + " / -";
			
			disp += String.format ("%3d" , dead[i][1])+ "]";
			
			System.out.println(disp);
		}

	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	