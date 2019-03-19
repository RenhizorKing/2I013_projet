package Mobs;

import java.util.ArrayList;

import Monde.Monde;

public abstract class M {
	private static int id=0;
	protected int x;
	protected int y;
	protected int sens;
	protected String S;
	protected int nb_pomme_manger;
	protected boolean evolution;



	protected int step;
	protected  int atk;
	protected  int pv;
	
	public M(int x, int y) {
		this.x=x;
		this.y=y;
		id++;
		step =0;
		nb_pomme_manger = 0;
		evolution = false;
		
	}
	public void move(int dx, int dy) {
		System.out.println("++++");
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

	public void manger_pomme(Pomme apple , ArrayList<Object> monde) {
		for(int i = 0; i < monde.size(); i++) {
				if(monde.get(i).equals(apple)){
					if (apple.isEstPourrie()) 
						nb_pomme_manger += 1 ;
					else 
						nb_pomme_manger += 2 ;
					monde.remove(i);
					return ;
			}
		}
	}
	public abstract String getS();
	public abstract void evoluer();
	public static int getId() {
		return id;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getStep() {
		return step;
	}
	

	public void setStep() {
		this.step += 1;
	}	
	public int getAtk() {
		return atk;
	}

	public int getPv() {
		return pv;
	}
	public int getNb_pomme_manger() {
		return nb_pomme_manger;
	}
	public boolean getEvolution() {
		return evolution;
	}
	public int getSens() {
		return this.sens;
	}
	
	public static void reproduction() {
		ArrayList<Object> carte = Monde.getCarte();
		int taille = carte.size();
		for (int i=0; i<taille; i++) {
			if (carte.get(i) instanceof M && ((M) carte.get(i)).getStep() >20) {
				for (int j=0;j<taille ;j++) {
					if (!(carte.get(j).equals(carte.get(i))) && carte.get(j).getClass().equals(carte.get(i).getClass()) && ((M) carte.get(j)).getStep() >20 && ((M)carte.get(j)).getX() == ((M)carte.get(i)).getX() && ((M)carte.get(j)).getY() == ((M)carte.get(i)).getY() ) {
						if (carte.get(i) instanceof M1) {
							//System.out.println("toto");
							((M1) carte.get(i)).step=0;
							((M1) carte.get(j)).step=0;
							carte.add(new M1(((M)carte.get(j)).getX(), ((M)carte.get(j)).getY()));
							break ;
						}
						if (carte.get(i) instanceof M2) {
							//System.out.println("toto");
							((M2) carte.get(i)).step=0;
							((M2) carte.get(j)).step=0;
							carte.add(new M2(((M)carte.get(j)).getX(), ((M)carte.get(j)).getY()));
							break ;
						}
					}
				}
			}
		}
	}
	
	
}