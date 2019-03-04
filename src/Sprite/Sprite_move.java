package Sprite;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Mobs.Arbre;
import Mobs.Braconnier;
import Mobs.M;
import Mobs.M1;
import Mobs.M2;
import Mobs.Pomme;
import Monde.Monde;
import Monde.Terrain;

public class Sprite_move extends JPanel {


	private JFrame frame;
	
	private Image waterSprite;
	private Image grassSprite;
	private Image treeSprite;
	private Image tSprite;
	private Image PokemonFeu;
	private Image PokemonFeuEvolue;
	private Image PokemonEau;
	private Image PokemonEauEvolue;
	private Image Apple;
	private Image ApplePourri;
	private Image Chasseur;
	private Image terre;
	public static int dx;
	public static int dy;
	private int spriteLength = 40;
	public int pas;
	Graphics2D g2;
	
	public Sprite_move()
	{
		pas=0;
		try
		{
			
			waterSprite = ImageIO.read(new File("water.png"));
			treeSprite = ImageIO.read(new File("arbref.png"));
			grassSprite = ImageIO.read(new File("grass.png"));
			tSprite = ImageIO.read(new File("tree.png"));
			PokemonFeu = ImageIO.read(new File("hericendre.png"));
			PokemonFeuEvolue = ImageIO.read(new File("FeurissonTrans.png")); 
			PokemonEau = ImageIO.read(new File("carapuce.png"));
			PokemonEauEvolue = ImageIO.read(new File("CarabaffeTrans.png")); 
			Apple = ImageIO.read(new File("pomme.png"));
			ApplePourri = ImageIO.read(new File("pommeP.png"));
			Chasseur = ImageIO.read(new File("chasseur.png"));
			terre = ImageIO.read(new File("terre.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		frame = new JFrame("World of Sprite");
		frame.add(this);
		frame.setSize(965,1000);
		frame.setVisible(true);
	}
	public void terrain() {
		
		for ( int i = 0 ; i < dx ; i++ ) {
			for ( int j = 0 ; j < dy ; j++ ) {
				if (Terrain.getTerrain()[i][j]<-5)
					g2.drawImage(terre,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				if (Terrain.getTerrain()[i][j]>=-5)
					g2.drawImage(grassSprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
				
			}
		}
	}
	public void paint(Graphics g)
	{
		g2= (Graphics2D)g;
		this.terrain();
		for ( int i = 0 ; i < dx ; i++ )
			for ( int j = 0 ; j < dy ; j++ )
			{
				try {
					if ( Monde.testC(i, j).size() == 0 ) {
						continue;
					}else {
						for (int l=0;l<Monde.testC(i, j).size();l++) {
								if (Monde.testC(i, j).get(l) instanceof M1) {
									if (((M1) Monde.testC(i, j).get(l)).getNb_evolution() == 0)
										g2.drawImage(PokemonFeu,spriteLength*i+pas,spriteLength*j,spriteLength,spriteLength, frame);
									if (((M1) Monde.testC(i, j).get(l)).getNb_evolution() == 1)
										g2.drawImage(PokemonFeuEvolue,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
								}
						}
					}
				}catch(Exception E) {
				}
		}
	
	}
	
	public static void main(String[] args) {
		Monde monde = new Monde(dx=20,dy=20,20);
		Sprite_move a =new Sprite_move();
		Terrain terrain= new Terrain(dx,dy);
		int step=0;
		while(true) {
			a.repaint();
			a.pas+=4;
			//monde.Refresh();
			//monde.pomme_pop(step);
			//Pomme.duree();
			//Pomme.delete();
			//Braconnier.chasser();
			//monde.addB();
			//monde.reproduction();
			//M.finB();
			//terrain.Stockage_passage();
			try{
				Thread.sleep(150); // en ms
			}catch(Exception e){
				e.printStackTrace();
			}
			step++;
		}
	}
}
