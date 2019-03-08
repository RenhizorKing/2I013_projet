package Monde;

import Mobs.M;
import Mobs.M1;
import Mobs.M2;

public class Terrain {
	private static int[][] terrain;
	private int dx;
	private int dy;
	
	public Terrain(int dx,int dy) {
		this.dx=dx;
		this.dy=dy;
		terrain = new int[dx][dy];
		for (int i=0;i<dx;i++) {
			for (int j=0;j<dy;j++) {
				terrain[i][j]=0;
			}
		}
	}
	public int cpt;
	public void Stockage_passage() {
		for (int i=0;i<Monde.getCarte().size();i++) {
			if (Monde.getCarte().get(i) instanceof M1)
				terrain[((M) Monde.getCarte().get(i)).getX()][((M) Monde.getCarte().get(i)).getY()]-=1;
			if (Monde.getCarte().get(i) instanceof M2)
				terrain[((M) Monde.getCarte().get(i)).getX()][((M) Monde.getCarte().get(i)).getY()]+=1;
		}
	}
	public void afficher() {
		for (int i=0;i<dx;i++) {
			for (int j=0;j<dy;j++) {
				System.out.print(""+terrain[i][j]+" ");
			}
			System.out.println("");
		}
	}
	public static int[][] getTerrain() {
		return terrain;
	}
	
}