package Monde;

import java.util.ArrayList;

import Mobs.Arbre;
import Mobs.Braconnier;
import Mobs.M;
import Mobs.M1;
import Mobs.M2;
import Mobs.Pomme;

public class Monde {
	private static int dx;
	private static int dy;
	private static ArrayList<Object> carte = new ArrayList<>();
	private static int direction;
	private int cpt = 0;
	
	
	public static ArrayList<Object> getCarte() {
		return carte;
	}

	public Monde(int x, int y, int nb_A) {//Initialisation de la liste des agents à mettre dans le monde
		dx=x;
		dy=y;/*
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
		}*/
		
		carte.add(new M1(11,10));
		carte.add(new Arbre(9,10));
		carte.add(new Arbre(11,10));
		carte.add(new Arbre(10,9));
		carte.add(new Arbre(10,11));
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
		System.out.println("Taille :"+carte.size());//pour debuger lors des erreurs
	}
	
	public static ArrayList<Object> testC(int x,int y,Class<?> O) {//retourne une ArrayList composé de class de O ayant comme coordonéées [x,y]
		ArrayList<Object> agent_XY = new ArrayList<>();
		//System.out.println(""+carte.size());
		for (int i=0;i<carte.size();i++) {
			//System.out.println(""+carte.get(i).getClass()+" " + "class Mobs.Arbre"+ " "+ carte.get(i).getClass().equals(O));
			if (carte.get(i).getClass().equals(O) && carte.get(i) instanceof M && ((M) carte.get(i)).getX() == x && ((M) carte.get(i)).getY() == y) //verification de la position et de la classe
				agent_XY.add((M) carte.get(i));
			if (carte.get(i).getClass().equals(O) && carte.get(i) instanceof Pomme && ((Pomme) carte.get(i)).getX() == x && ((Pomme) carte.get(i)).getY() == y)
				agent_XY.add((Pomme) carte.get(i));
			if (carte.get(i).getClass().equals(O) && carte.get(i) instanceof Braconnier && ((Braconnier) carte.get(i)).getX() == x && ((Braconnier) carte.get(i)).getY() == y)
				agent_XY.add((Braconnier) carte.get(i));
			if (carte.get(i).getClass().equals(O) && carte.get(i) instanceof Arbre && ((Arbre) carte.get(i)).getX() == x && ((Arbre) carte.get(i)).getY() == y)
				agent_XY.add((Arbre) carte.get(i));
		}
		return agent_XY;//S'il n'y a pas d'agent sur la case présent
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
			if (carte.get(i) instanceof Arbre && ((Arbre) carte.get(i)).getX() == x && ((Arbre) carte.get(i)).getY() == y)
				agent_XY.add((Arbre) carte.get(i));
			
		}
		return agent_XY;//S'il n'y a pas d'agent sur la case présent
	}
	
	public void Refresh() {
		for (int i=0;i<carte.size();i++) {
			if (carte.get(i) instanceof M) {
			
				((M) carte.get(i)).move(dx, dy);
				
			}
			if (carte.get(i) instanceof Braconnier)
				((Braconnier) carte.get(i)).move(dx, dy);
			if (carte.get(i) instanceof Pomme) {
				((Pomme) carte.get(i)).pourrir();
			}
		}
	}
	public static int getDirection() {
		return direction;
	}
	/*
	public void Afficher() {
		for (int j=0;j<dx;j++) {
			for (int i=0;i<dy;i++) {
				
				if (testC(i, j) != null) {
					if (carte.get(i).getClass() == Mobs.M1.class)
						System.out.print(((M) carte.get(i)).getS()+" ");
					if (carte.get(i).getClass() == Mobs.M2.class)
						System.out.print(((M) carte.get(i)).getS()+" ");
					if (carte.get(i).getClass() == Mobs.Arbre.class)
						System.out.print(((Arbre) carte.get(i)).getS()+" ");
					
				}else {
					System.out.print("- ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}*/

	public static int getDx() {
		return dx;
	}

	public static int getDy() {
		return dy;
	}
	
}