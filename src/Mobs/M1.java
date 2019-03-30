package Mobs;

import java.util.ArrayList;

import Monde.Monde;
import Monde.Terrain;

public class M1 extends M{
	public M1(int x, int y) {
		super(x, y);
		int x1=this.x,y1=this.y;
		if (this.sens == 0) {
			x1=(x1-1+Monde.getDx())%Monde.getDx();
			if(Terrain.getTerrain()[x1][y1][1] < 205 || Terrain.getTerrain()[x1][y1][1] >= 239) {
				this.sens=4;
				return;
			}
		}
		if (this.sens == 1) {
			x1=(x1+1+Monde.getDx())%Monde.getDx();
			if(Terrain.getTerrain()[x1][y1][1] < 205 || Terrain.getTerrain()[x1][y1][1] >= 239) {
				this.sens=5;
				return;
			}
		}
		if (this.sens == 2) {
			y1=(y1+1+Monde.getDy())%Monde.getDy();
			if(Terrain.getTerrain()[x1][y1][1] < 205 || Terrain.getTerrain()[x1][y1][1] >= 239) {
				this.sens=6;
				return;
			}
		}
		if (this.sens == 3) {
			y1=(y1-1+Monde.getDy())%Monde.getDy();
			if(Terrain.getTerrain()[x1][y1][1] < 205 || Terrain.getTerrain()[x1][y1][1] >= 239) {
				this.sens=7;
				return;
			}
		}
		atk=(int) (Math.random()*100);
		pv=500;
		S="X";
	}

	public String getS() {
		return S;
	}

	public void move(int dx, int dy) {
		if (this.sens == 0) {
			this.x=(this.x-1+dx)%dx;
		}
		if (this.sens == 1) {
			this.x=(this.x+1+dx)%dx;
		}
		if (this.sens == 2) {
			this.y=(this.y+1+dy)%dy;
		}
		if (this.sens == 3) {
			this.y=(this.y-1+dy)%dy;
		}
		for (int m=  0; m < Monde.getcarte_P().size();m++) {
			if (Monde.getcarte_P().get(m) instanceof Pomme && ((Pomme) Monde.getcarte_P().get(m)).getX() == this.x && ((Pomme) Monde.getcarte_P().get(m)).getY() == this.y) {
				manger_pomme((Pomme) Monde.getcarte_P().get(m));
			}
		}
		
	}
	
	public void evoluer() {
		if(nb_pomme_manger >= 30 && evolution == false) {
			evolution =true;
		}
	}
	public void manger_pomme(Pomme apple) {
		for(int i = 0; i < Monde.getcarte_P().size(); i++) {
				if(Monde.getcarte_P().get(i).equals(apple)){
					if (apple.isEstPourrie()) 
						nb_pomme_manger += 1 ;
					else 
						nb_pomme_manger += 2 ;
					Monde.getcarte_P().remove(i);
					evoluer();
					return ;
			}
		}
	}
	public void setSens() {
		for(int m=  0; m < Monde.getcarte_P().size();m++) {
			if(((Pomme) Monde.getcarte_P().get(m)).getX() == this.x-1 && ((Pomme) Monde.getcarte_P().get(m)).getY() == this.y) {
					this.sens = 0;
					return ;
				}
				if(((Pomme) Monde.getcarte_P().get(m)).getX() == this.x+1 && ((Pomme) Monde.getcarte_P().get(m)).getY() == this.y) {
					this.sens = 1;
					return ;
				}
				if(((Pomme) Monde.getcarte_P().get(m)).getX() == this.x && ((Pomme) Monde.getcarte_P().get(m)).getY() == this.y+1) {
					this.sens = 2;
					return ;
				}
				if(((Pomme) Monde.getcarte_P().get(m)).getX() == this.x && ((Pomme) Monde.getcarte_P().get(m)).getY() == this.y-1) {
					this.sens = 3;
					return ;
				}
			
		}
		if ((Terrain.getTerrain()[(this.x-1+Monde.getDx())%Monde.getDx()][this.y][2] == 1 || Terrain.getTerrain()[(this.x-1+Monde.getDx())%Monde.getDx()][this.y][1] <= 10) 
				&& (Terrain.getTerrain()[(this.x+1+Monde.getDx())%Monde.getDx()][this.y][2] == 1 || Terrain.getTerrain()[(this.x+1+Monde.getDx())%Monde.getDx()][this.y][1] <= 10)
				&& (Terrain.getTerrain()[this.x][(this.y+1+Monde.getDy())%Monde.getDy()][2] == 1 || Terrain.getTerrain()[this.x][(this.y+1+Monde.getDy())%Monde.getDy()][1] <= 10) 
				&& (Terrain.getTerrain()[this.x][(this.y-1+Monde.getDy())%Monde.getDy()][2] == 1 || Terrain.getTerrain()[this.x][(this.y-1+Monde.getDy())%Monde.getDy()][1] <= 10)) 
		{
			Monde.getcarte_Ag().remove(this);
			return;
		}
		int x1,y1;
		boolean boolA=false;
		do {
			boolA=false;
			x1=this.x;
			y1=this.y;
			this.sens = (int)(Math.random()*4);
			if (this.sens == 0) {
				x1=(x1-1+Monde.getDx())%Monde.getDx();
				if(Terrain.getTerrain()[x1][y1][1] < 205 || Terrain.getTerrain()[x1][y1][1] >= 239) {
					this.sens=4;
					return;
				}
			}
			if (this.sens == 1) {
				x1=(x1+1+Monde.getDx())%Monde.getDx();
				if(Terrain.getTerrain()[x1][y1][1] < 205 || Terrain.getTerrain()[x1][y1][1] >= 239) {
					this.sens=5;
					return;
				}
			}
			if (this.sens == 2) {
				y1=(y1+1+Monde.getDy())%Monde.getDy();
				if(Terrain.getTerrain()[x1][y1][1] < 205 || Terrain.getTerrain()[x1][y1][1] >= 239) {
					this.sens=6;
					return;
				}
			}
			if (this.sens == 3) {
				y1=(y1-1+Monde.getDy())%Monde.getDy();
				if(Terrain.getTerrain()[x1][y1][1] < 205 || Terrain.getTerrain()[x1][y1][1] >= 239) {
					this.sens=7;
					return;
				}
			}
			for (int i=0;i<Monde.getcarte_Ab().size();i++) {
				if ((Monde.getcarte_Ab().get(i).getX() == x1 && Monde.getcarte_Ab().get(i).getY() == y1)) {
					boolA=true;
					break;
				}
			}
		}while (boolA);
	}
	
	public int getSens() {
		return this.sens;
	}
}