package Mobs;

import Monde.Monde;

public class M2 extends M{

	public M2(int x, int y) {
		super(x, y);
		atk=(int) (Math.random()*100);
		pv=500;
		S="0";
	}

	public String getS() {
		return S;
	}
	
	public void move(int dx, int dy) {
		
	if(this.sens == 0) {
		this.x=(this.x-1+dx)%dx;
	}
	if(this.sens == 1) {
		this.x=(this.x+1+dx)%dx;
	}
	if(this.sens == 2) {
		this.y=(this.y+1+dx)%dx;
	}
	if(this.sens == 3) {
		this.y=(this.y-1+dx)%dx;
	}
	for(int m=  0; m < Monde.getCarte().size();m++) {
		if(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x && ((Pomme) Monde.getCarte().get(m)).getY() == this.y) {
			manger_pomme((Pomme) Monde.getCarte().get(m), Monde.getCarte());
		}
		
	}
		
		}
	
	public void evoluer() {
		if(nb_pomme_manger >= 30 && nb_evolution == 0) {
			nb_evolution ++;
		}
	}
	public void setSens() {
		for(int m=  0; m < Monde.getCarte().size();m++) {
			if((Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x-1 && ((Pomme) Monde.getCarte().get(m)).getY() == this.y) ||
					(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x+1 && ((Pomme) Monde.getCarte().get(m)).getY() == this.y) ||
					(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x && ((Pomme) Monde.getCarte().get(m)).getY() == this.y+1) ||
					(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x && ((Pomme) Monde.getCarte().get(m)).getY() == this.y-1)) {
				if(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x-1 && ((Pomme) Monde.getCarte().get(m)).getY() == this.y) {
					this.sens = 0;
				}
				if(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x+1 && ((Pomme) Monde.getCarte().get(m)).getY() == this.y) {
					this.sens = 1;
				}
				if(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x && ((Pomme) Monde.getCarte().get(m)).getY() == this.y+1) {
					this.sens = 2;
				}
				if(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x && ((Pomme) Monde.getCarte().get(m)).getY() == this.y-1) {
					this.sens = 3;
				}
			}
			else {
				this.sens = (int)(Math.random()*4);
			}
		}
	}
	public int getSens() {
		return this.sens;
	}
	
}