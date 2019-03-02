package Mobs;

import Monde.Monde;

public class Braconnier {
	private int x;
	private int y;
	public Braconnier(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void move(int dx, int dy) {
		int x1,x2;
		for(int i = (this.getX() - 1 + dx)%dx; i <= (this.getX() + 1 + dx)%dx; i++) {
			for(int j = (this.getY() - 1+ dy )%dy; j <= (this.getY() + 1 + dy)%dy; j++) {
				for(int m=0; m < Monde.getCarte().size();m++) {
					if(Monde.getCarte().get(m) instanceof M && ((M) Monde.getCarte().get(m)).getX() == i && ((M) Monde.getCarte().get(m)).getY() == j) {
						if (Math.random() < 0.85) {
							this.x = ((M) Monde.getCarte().get(m)).getX();
							this.y = ((M) Monde.getCarte().get(m)).getY();
							return;							
						}
					}
				}
			}
		}
		do {
			x1= (int) (Math.random()*3) -1;
			x2= (int) (Math.random()*3) -1;
		}while(Monde.yaArbres(((x+x1+dx)%dx),((y+x2+dy)%dy)));
		x=(x+x1+dx)%dx;
		y=(y+x2+dy)%dy;
	}
	public static void chasser() {
		for (int c=0;c<Monde.getCarte().size();c++) {

			if (Monde.getCarte().get(c) instanceof Braconnier) {
				for(int i = ((Braconnier)Monde.getCarte().get(c)).getX() - 1; i <= ((Braconnier)Monde.getCarte().get(c)).getX() + 1; i++) { //Parcours voisin selon voisinage de Moore
					for(int j = ((Braconnier)Monde.getCarte().get(c)).getY() - 1; j <= ((Braconnier)Monde.getCarte().get(c)).getY() + 1; j++) {
						for(int m=0; m < Monde.getCarte().size();m++) {
							if (Monde.getCarte().get(m) instanceof M1 && ((M1) Monde.getCarte().get(m)).getX() == i && ((M1) Monde.getCarte().get(m)).getY() == j) {// si M1 voisin
								if (Math.random() <0.3)
									Monde.getCarte().remove(Monde.getCarte().get(m));
									return ;
							}
							if (Monde.getCarte().get(m) instanceof M2 && ((M2) Monde.getCarte().get(m)).getX() == i && ((M2) Monde.getCarte().get(m)).getY() == j) {// si M2 voisin
								if (Math.random() <0.4)
									Monde.getCarte().remove(Monde.getCarte().get(m));
									return ;
							}
						}
					}
				}
			}
		}
	}
}
