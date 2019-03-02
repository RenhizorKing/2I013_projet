package Mobs;

import java.util.ArrayList;

import Monde.Monde;

public abstract class M {
	private static int id=0;
	protected int x;
	protected int y;
	protected String S;
	protected int nb_pomme_manger;
	protected int nb_evolution;


	protected int step;
	protected  int atk;
	protected  int pv;
	
	public M(int x, int y) {
		this.x=x;
		this.y=y;
		id++;
		step =0;
		nb_pomme_manger = 0;
		nb_evolution = 0;
		
	}
	public void move(int dx, int dy) {
		int x1= (int) (Math.random()*3) -1;
		int x2= (int) (Math.random()*3) -1;
		x=(x+x1+dx)%dx;
		y=(y+x2+dy)%dy;
	}
	public void manger_pomme(Pomme apple , ArrayList<Object> monde) { // A Continuer
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
	
	public static void finB() {
		for (int c=0;c<Monde.getCarte().size();c++) {

			if (Monde.getCarte().get(c) instanceof M) {
				for(int i = ((M)Monde.getCarte().get(c)).getX() - 1; i <= ((M)Monde.getCarte().get(c)).getX() + 1; i++) {
					for(int j = ((M)Monde.getCarte().get(c)).getY() - 1; j <= ((M)Monde.getCarte().get(c)).getY() + 1; j++) {
						for(int m=0; m < Monde.getCarte().size();m++) {
							if (Monde.getCarte().get(m) instanceof Braconnier && ((Braconnier) Monde.getCarte().get(m)).getX() == i && ((Braconnier) Monde.getCarte().get(m)).getY() == j) {
								if (((M) Monde.getCarte().get(c)).getNb_evolution() == 0 && Math.random() < 0.1) {
									Monde.getCarte().remove(Monde.getCarte().get(m));
									return ;
								}
								if (((M) Monde.getCarte().get(c)).getNb_evolution() == 1 && Math.random() < 0.2) {
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

	public int getAtk() {
		return atk;
	}

	public int getPv() {
		return pv;
	}
	public int getNb_pomme_manger() {
		return nb_pomme_manger;
	}
	public int getNb_evolution() {
		return nb_evolution;
	}
	
	
}