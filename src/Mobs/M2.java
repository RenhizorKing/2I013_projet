package Mobs;

import java.util.ArrayList;

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
		if(nb_pomme_manger >= 30 && evolution == false) {
			evolution = true;
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
		if (Monde.testC((this.x-1+Monde.getDx())%Monde.getDx(), this.y, Mobs.Arbre.class).size() != 0 && 
				Monde.testC((this.x+1+Monde.getDx())%Monde.getDx(), this.y, Mobs.Arbre.class).size() != 0
				&& Monde.testC(this.x, (this.y-1+Monde.getDy())%Monde.getDy(), Mobs.Arbre.class).size() != 0
				&& Monde.testC(this.x, (this.y+1+Monde.getDy())%Monde.getDy(), Mobs.Arbre.class).size() != 0) {
			
			((Arbre) Monde.testC((this.x-1+Monde.getDx())%Monde.getDx(), this.y,Mobs.Arbre.class).get(0)).setEnfeu(true);
			((Arbre) Monde.testC((this.x+1+Monde.getDx())%Monde.getDx(), this.y,Mobs.Arbre.class).get(0)).setEnfeu(true);
			((Arbre) Monde.testC(this.x, (this.y-1+Monde.getDy())%Monde.getDy(),Mobs.Arbre.class).get(0)).setEnfeu(true);
			((Arbre) Monde.testC(this.x, (this.y+1+Monde.getDy())%Monde.getDy(),Mobs.Arbre.class).get(0)).setEnfeu(true);
			Monde.getCarte().remove(this);
			return ;
		}
		
		for(int m=  0; m < Monde.getCarte().size();m++) {
			if((Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x-1 && ((Pomme) Monde.getCarte().get(m)).getY() == this.y) ||
					(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x+1 && ((Pomme) Monde.getCarte().get(m)).getY() == this.y) ||
					(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x && ((Pomme) Monde.getCarte().get(m)).getY() == this.y+1) ||
					(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x && ((Pomme) Monde.getCarte().get(m)).getY() == this.y-1)) {
				
				if(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x-1 && ((Pomme) Monde.getCarte().get(m)).getY() == this.y) {
					this.sens = 0;
					return ;
				}
				if(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x+1 && ((Pomme) Monde.getCarte().get(m)).getY() == this.y) {
					this.sens = 1;
					return ;
				}
				if(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x && ((Pomme) Monde.getCarte().get(m)).getY() == this.y+1) {
					this.sens = 2;
					return ;
				}
				if(Monde.getCarte().get(m) instanceof Pomme && ((Pomme) Monde.getCarte().get(m)).getX() == this.x && ((Pomme) Monde.getCarte().get(m)).getY() == this.y-1) {
					this.sens = 3;
					return ;
				}
			}
		}
		int x1,y1;
		do {
			this.sens = (int)(Math.random()*4);
			x1=this.x;
			y1=this.y;
			if (this.sens == 0) {
				x1=(x1-1+Monde.getDx())%Monde.getDx();
			}
			if (this.sens == 1) {
				x1=(x1+1+Monde.getDx())%Monde.getDx();
			}
			if (this.sens == 2) {
				y1=(y1+1+Monde.getDy())%Monde.getDy();
			}
			if (this.sens == 3) {
				y1=(y1-1+Monde.getDy())%Monde.getDy();
			}
		}while(Monde.testC(x1, y1,Mobs.Arbre.class).size() ==1);
}
	public int getSens() {
		return this.sens;
	}
	
}