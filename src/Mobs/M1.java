package Mobs;

import java.util.ArrayList;

import Monde.Monde;

public class M1 extends M{
	public M1(int x, int y) {
		super(x, y);
		atk=(int) (Math.random()*100);
		pv=500;
		S="X";
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
	public void manger_pomme(Pomme apple , ArrayList<Object> monde) {
		for(int i = 0; i < monde.size(); i++) {
				if(monde.get(i).equals(apple)){
					if (apple.isEstPourrie()) 
						nb_pomme_manger += 1 ;
					else 
						nb_pomme_manger += 2 ;
					monde.remove(i);
					evoluer();
					return ;
			}
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