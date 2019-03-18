package Mobs;

public class Arbre{
	private boolean enfeu;
	private int x;
	private int y;
	private String S;

	public Arbre(int x, int y) {
		this.x=x;
		this.y=y;
		S="T";
	}
	
	public boolean isEnfeu() {
		return enfeu;
	}
	public void setEnfeu(boolean enfeu) {
		this.enfeu = enfeu;
		
	}
	public String getS() {
		return S;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}	
}
