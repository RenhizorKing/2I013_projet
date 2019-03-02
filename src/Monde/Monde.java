package Monde;

import java.util.ArrayList;

import Mobs.Arbre;
import Mobs.Braconnier;
import Mobs.M;
import Mobs.M1;
import Mobs.M2;
import Mobs.Pomme;

public class Monde {
	private int dx;
	private int dy;
	private static ArrayList<Object> carte = new ArrayList<>();
	
	public static ArrayList<Object> getCarte() {
		return carte;
	}
	public Monde(int x, int y, int nb_A) {//Initialisation de la liste des agents à mettre dans le monde
		dx=x;
		dy=y;
		for (int i=0;i<nb_A;i++) {
			double p = Math.random();
			if (p <= 0.4) {
				int x1= (int) (Math.random()*dx);
				int y1 =(int) (Math.random()*dy);
				Arbre arbres = new Arbre(x1, y1);
				carte.add(arbres);
			}else {
				double p1 =  Math.random();
				if (p1 <= 0.5) {
					int x1= (int) (Math.random()*dx);
					int y1 =(int) (Math.random()*dy);
					M1 monstre = new M1(x1, y1);
					carte.add(monstre);
				}else {
					int x1= (int) (Math.random()*dx);
					int y1 =(int) (Math.random()*dy);
					M2 monstre = new M2(x1, y1);
					carte.add(monstre);
				}
			}
		}
		int x1,y1;
		carte.add(new Braconnier(12, 12));
		//carte.add(new M1(13, 12));
		//carte.add(new M2(13, 12));
	}
	
	public void pomme_pop(int cpt) { //fait apparaitre des pomme sur la carte
		if (cpt % 2 == 0) {
			int x1;
			int y1;
			do {
				x1= (int) (Math.random()*dx);
				y1 =(int) (Math.random()*dy);
			}while (Monde.testC(x1, y1).size()!=0);
			Pomme apple = new Pomme(x1, y1);
			carte.add(apple);
		}
	}
	public void detail() {
		System.out.println("Taille :"+carte.size());
		for (int i=0;i<carte.size();i++) {
			System.out.println(carte.get(i).getClass() == Mobs.Arbre.class);//pour debuger lors des erreurs
		}
	}
	public static ArrayList<Object> testC(int x,int y) {//retourne la classe de l'agent présent sur la case [x,y]
		ArrayList<Object> agent_XY = new ArrayList<>();
		//System.out.println(""+carte.size());
		for (int i=0;i<carte.size();i++) {
			if ( carte.get(i) instanceof M1 && ((M1) carte.get(i)).getX() == x && ((M1) carte.get(i)).getY() == y) //verification de la position et de la classe
				agent_XY.add((M1) carte.get(i));
			if (carte.get(i) instanceof M2 && ((M2) carte.get(i)).getX() == x && ((M2) carte.get(i)).getY() == y)
				agent_XY.add((M2) carte.get(i));
			if (carte.get(i) instanceof Arbre && ((Arbre) carte.get(i)).getX() == x && ((Arbre) carte.get(i)).getY() == y)
				agent_XY.add((Arbre) carte.get(i));
			if (carte.get(i) instanceof Pomme && ((Pomme) carte.get(i)).getX() == x && ((Pomme) carte.get(i)).getY() == y)
				agent_XY.add((Pomme) carte.get(i));
			if (carte.get(i) instanceof Braconnier && ((Braconnier) carte.get(i)).getX() == x && ((Braconnier) carte.get(i)).getY() == y)
				agent_XY.add((Braconnier) carte.get(i));
		}
		return agent_XY;//S'il n'y a pas d'agent sur la case présent
	}
	
	public void Refresh() {
		for (int i=0;i<carte.size();i++) {
			if (carte.get(i) instanceof Braconnier)
				((Braconnier) carte.get(i)).move(dx, dy);
			}
		for (int i=0;i<carte.size();i++) {
			if (carte.get(i) instanceof M)
				((M) carte.get(i)).move(dx, dy);
			if (carte.get(i) instanceof Pomme) {
				((Pomme) carte.get(i)).pourrir();
			}
		}
	}
	public static boolean yaArbres(int x, int y) {
		if (carte.size()==0)
			return false;
		for (int i=0;i<carte.size();i++) {
			if (carte.get(i) instanceof Arbre && ((Arbre)carte.get(i)).getX()==x && ((Arbre)carte.get(i)).getY()==y)
				return true;
		}
		return false;
	}
	public void Afficher() {
		for (int j=0;j<dx;j++) {
			for (int i=0;i<dy;i++) {
				
				if (testC(i, j).size() != 0) {
					if (carte.get(i) instanceof M1)
						System.out.print(((M) carte.get(i)).getS()+" ");
					if (carte.get(i) instanceof M2)
						System.out.print(((M) carte.get(i)).getS()+" ");
					if (carte.get(i) instanceof Arbre)
						System.out.print(((Arbre) carte.get(i)).getS()+" ");
					
				}else {
					System.out.print("- ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public static boolean ch_m() {
		for (int i=0;i<carte.size();i++) {
			if (carte.get(i) instanceof Braconnier) {
				for (int j=0;j<carte.size();j++) {
					if ((carte.get(j) instanceof M) && ((Braconnier)(carte.get(i))).getX() == ((M) carte.get(j)).getX() && ((Braconnier)(carte.get(i))).getY() == ((M) carte.get(j)).getY()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
}