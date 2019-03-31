package Sprite;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;

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

public class SpriteDemo extends JPanel implements KeyListener,MouseListener,MouseWheelListener{


	private JFrame frame;
	
	private Image waterSprite;
	private Image grassSprite;
	private Image grassSpriteH;
	private Image grassSpriteA;
	private Image tSprite;
	private Image tSpriteH;
	private Image tSpriteA;
	private Image arbrecrame;
	private Image terreSprite;
	private Image[][] PokemonFeuMove;
	private Image[][] PokemonFeuEvolueMove;
	private Image[][] PokemonEauMove;
	private Image[][] PokemonEauEvolueMove;
	private Image Apple;
	private Image ApplePourri;
	private Image Chasseur;
	private Image Flamme;
	private Image rochevolcan;
	private Image Lave;
	private Image pluie;
	private Image pluieF;
	private Image nuitF;
	private Image soleilF;
	public static int dx;
	public static int dy;
	private int x;
	private int y;
	private static int spriteLength = 40;
	private static int pas = 0;
	private static int marcher = 0;
	private static int cpt_pas = 0;
	private static int step;
	private static int cycle_volcan=0;
	private int a1;
	private int a2;
	private int wx;
	private int wy;
	public int vitesse;
	private int jour;
	private long time_init;
	private long laps;
	private boolean duree;


	public SpriteDemo()
	{
		try
		{
			waterSprite = ImageIO.read(new File("water.png"));
			grassSprite = ImageIO.read(new File("herbeP.png"));
			grassSpriteH = ImageIO.read(new File("herbePN.png"));
			grassSpriteA = ImageIO.read(new File("herbePA.png"));
			tSprite = ImageIO.read(new File("test1.png"));
			tSpriteH = ImageIO.read(new File("test1N.png"));
			tSpriteA = ImageIO.read(new File("test1A.png"));
			arbrecrame = ImageIO.read(new File("test1crame.png"));
			terreSprite = ImageIO.read(new File("terre.png"));
			Apple = ImageIO.read(new File("pomme.png"));
			ApplePourri = ImageIO.read(new File("pommeP.png"));
			Chasseur = ImageIO.read(new File("chasseur.png"));
			Flamme = Toolkit.getDefaultToolkit().createImage("Flamme.gif");
			rochevolcan = ImageIO.read(new File("roche1.png"));
			Lave = ImageIO.read(new File("lava.png"));
			pluie = Toolkit.getDefaultToolkit().createImage("Pluie.gif");
			pluieF=ImageIO.read(new File("PluiF.png"));
			nuitF=ImageIO.read(new File("NuitF.png"));
			soleilF=ImageIO.read(new File("SoleilF.png"));
			time_init= (System.nanoTime()/1000000000);
			jour=0;
			duree=false;
			
			PokemonFeuMove = new Image[4][8]; //Hericendre
			PokemonFeuMove[0][0] = ImageIO.read(new File("Hericendre_walkdown1.png"));
			PokemonFeuMove[0][1] = ImageIO.read(new File("Hericendre_walkdown2.png"));  //Deplacement vers le bas
			PokemonFeuMove[0][2] = ImageIO.read(new File("Hericendre_walkdown3.png"));
			PokemonFeuMove[0][3] = ImageIO.read(new File("Hericendre_walkdown4.png"));
			PokemonFeuMove[0][4] = ImageIO.read(new File("Hericendre_walkdown1.png"));
			PokemonFeuMove[0][5] = ImageIO.read(new File("Hericendre_walkdown2.png"));  //Deplacement vers le bas
			PokemonFeuMove[0][6] = ImageIO.read(new File("Hericendre_walkdown3.png"));
			PokemonFeuMove[0][7] = ImageIO.read(new File("Hericendre_walkdown4.png"));
			
			PokemonFeuMove[1][0] = ImageIO.read(new File("Hericendre_walkup1.png"));
			PokemonFeuMove[1][1] = ImageIO.read(new File("Hericendre_walkup2.png"));  //Deplacement vers le haut
			PokemonFeuMove[1][2] = ImageIO.read(new File("Hericendre_walkup3.png"));
			PokemonFeuMove[1][3] = ImageIO.read(new File("Hericendre_walkup4.png"));
			PokemonFeuMove[1][4] = ImageIO.read(new File("Hericendre_walkup1.png"));
			PokemonFeuMove[1][5] = ImageIO.read(new File("Hericendre_walkup2.png"));  //Deplacement vers le haut
			PokemonFeuMove[1][6] = ImageIO.read(new File("Hericendre_walkup3.png"));
			PokemonFeuMove[1][7] = ImageIO.read(new File("Hericendre_walkup4.png"));
			
			PokemonFeuMove[2][0] = ImageIO.read(new File("Hericendre_walkleft1.png"));
			PokemonFeuMove[2][1] = ImageIO.read(new File("Hericendre_walkleft2.png"));  //Deplacement vers la gauche
			PokemonFeuMove[2][2] = ImageIO.read(new File("Hericendre_walkleft3.png"));
			PokemonFeuMove[2][3] = ImageIO.read(new File("Hericendre_walkleft4.png"));
			PokemonFeuMove[2][4] = ImageIO.read(new File("Hericendre_walkleft1.png"));
			PokemonFeuMove[2][5] = ImageIO.read(new File("Hericendre_walkleft2.png")); //Deplacement vers la gauche
			PokemonFeuMove[2][6] = ImageIO.read(new File("Hericendre_walkleft3.png"));
			PokemonFeuMove[2][7] = ImageIO.read(new File("Hericendre_walkleft4.png"));
			
			PokemonFeuMove[3][0] = ImageIO.read(new File("Hericendre_walkright1.png"));
			PokemonFeuMove[3][1] = ImageIO.read(new File("Hericendre_walkright2.png"));  //Deplacement vers la droite
			PokemonFeuMove[3][2] = ImageIO.read(new File("Hericendre_walkright3.png"));
			PokemonFeuMove[3][3] = ImageIO.read(new File("Hericendre_walkright4.png"));
			PokemonFeuMove[3][4] = ImageIO.read(new File("Hericendre_walkright1.png"));
			PokemonFeuMove[3][5] = ImageIO.read(new File("Hericendre_walkright2.png"));  //Deplacement vers la droite
			PokemonFeuMove[3][6] = ImageIO.read(new File("Hericendre_walkright3.png"));
			PokemonFeuMove[3][7] = ImageIO.read(new File("Hericendre_walkright4.png"));
			
			
			
			PokemonFeuEvolueMove = new Image[4][8]; //Feurisson
			PokemonFeuEvolueMove[0][0] = ImageIO.read(new File("Feurisson_walkdown1.png"));
			PokemonFeuEvolueMove[0][1] = ImageIO.read(new File("Feurisson_walkdown2.png"));
			PokemonFeuEvolueMove[0][2] = ImageIO.read(new File("Feurisson_walkdown3.png"));
			PokemonFeuEvolueMove[0][3] = ImageIO.read(new File("Feurisson_walkdown4.png")); //deplacement vers le bas
			PokemonFeuEvolueMove[0][4] = ImageIO.read(new File("Feurisson_walkdown1.png"));
			PokemonFeuEvolueMove[0][5] = ImageIO.read(new File("Feurisson_walkdown2.png"));
			PokemonFeuEvolueMove[0][6] = ImageIO.read(new File("Feurisson_walkdown3.png"));
			PokemonFeuEvolueMove[0][7] = ImageIO.read(new File("Feurisson_walkdown4.png"));
			
			PokemonFeuEvolueMove[1][0] = ImageIO.read(new File("Feurisson_walkup1.png"));
			PokemonFeuEvolueMove[1][1] = ImageIO.read(new File("Feurisson_walkup2.png"));
			PokemonFeuEvolueMove[1][2] = ImageIO.read(new File("Feurisson_walkup3.png"));
			PokemonFeuEvolueMove[1][3] = ImageIO.read(new File("Feurisson_walkup4.png")); //deplacement vers le haut
			PokemonFeuEvolueMove[1][4] = ImageIO.read(new File("Feurisson_walkup1.png"));
			PokemonFeuEvolueMove[1][5] = ImageIO.read(new File("Feurisson_walkup2.png"));
			PokemonFeuEvolueMove[1][6] = ImageIO.read(new File("Feurisson_walkup3.png"));
			PokemonFeuEvolueMove[1][7] = ImageIO.read(new File("Feurisson_walkup4.png"));
			
			PokemonFeuEvolueMove[2][0] = ImageIO.read(new File("Feurisson_walkleft1.png"));
			PokemonFeuEvolueMove[2][1] = ImageIO.read(new File("Feurisson_walkleft2.png"));
			PokemonFeuEvolueMove[2][2] = ImageIO.read(new File("Feurisson_walkleft3.png"));
			PokemonFeuEvolueMove[2][3] = ImageIO.read(new File("Feurisson_walkleft4.png")); //deplacement vers la gauche
			PokemonFeuEvolueMove[2][4] = ImageIO.read(new File("Feurisson_walkleft1.png"));
			PokemonFeuEvolueMove[2][5] = ImageIO.read(new File("Feurisson_walkleft2.png"));
			PokemonFeuEvolueMove[2][6] = ImageIO.read(new File("Feurisson_walkleft3.png"));
			PokemonFeuEvolueMove[2][7] = ImageIO.read(new File("Feurisson_walkleft4.png"));
			
			PokemonFeuEvolueMove[3][0] = ImageIO.read(new File("Feurisson_walkright1.png"));
			PokemonFeuEvolueMove[3][1] = ImageIO.read(new File("Feurisson_walkright2.png"));
			PokemonFeuEvolueMove[3][2] = ImageIO.read(new File("Feurisson_walkright3.png"));
			PokemonFeuEvolueMove[3][3] = ImageIO.read(new File("Feurisson_walkright4.png")); //deplacement vers la droite
			PokemonFeuEvolueMove[3][4] = ImageIO.read(new File("Feurisson_walkright1.png"));
			PokemonFeuEvolueMove[3][5] = ImageIO.read(new File("Feurisson_walkright2.png"));
			PokemonFeuEvolueMove[3][6] = ImageIO.read(new File("Feurisson_walkright3.png"));
			PokemonFeuEvolueMove[3][7] = ImageIO.read(new File("Feurisson_walkright4.png"));
			
			
			
			PokemonEauMove = new Image[4][8];  //carapuce
			PokemonEauMove[0][0] = ImageIO.read(new File("Carapuce_walkdown1.png"));
			PokemonEauMove[0][1] = ImageIO.read(new File("Carapuce_walkdown2.png"));
			PokemonEauMove[0][2] = ImageIO.read(new File("Carapuce_walkdown3.png"));
			PokemonEauMove[0][3] = ImageIO.read(new File("Carapuce_walkdown4.png"));
			PokemonEauMove[0][4] = ImageIO.read(new File("Carapuce_walkdown1.png"));  //deplacement vers le bas
			PokemonEauMove[0][5] = ImageIO.read(new File("Carapuce_walkdown2.png"));
			PokemonEauMove[0][6] = ImageIO.read(new File("Carapuce_walkdown3.png"));
			PokemonEauMove[0][7] = ImageIO.read(new File("Carapuce_walkdown4.png"));
			
			PokemonEauMove[1][0] = ImageIO.read(new File("Carapuce_walkup1.png"));
			PokemonEauMove[1][1] = ImageIO.read(new File("Carapuce_walkup2.png"));
			PokemonEauMove[1][2] = ImageIO.read(new File("Carapuce_walkup3.png"));
			PokemonEauMove[1][3] = ImageIO.read(new File("Carapuce_walkup4.png"));
			PokemonEauMove[1][4] = ImageIO.read(new File("Carapuce_walkup1.png"));  //deplacement vers le haut
			PokemonEauMove[1][5] = ImageIO.read(new File("Carapuce_walkup2.png"));
			PokemonEauMove[1][6] = ImageIO.read(new File("Carapuce_walkup3.png"));
			PokemonEauMove[1][7] = ImageIO.read(new File("Carapuce_walkup4.png"));
			
			PokemonEauMove[2][0] = ImageIO.read(new File("Carapuce_walkleft1.png"));
			PokemonEauMove[2][1] = ImageIO.read(new File("Carapuce_walkleft2.png"));
			PokemonEauMove[2][2] = ImageIO.read(new File("Carapuce_walkleft3.png"));
			PokemonEauMove[2][3] = ImageIO.read(new File("Carapuce_walkleft4.png"));
			PokemonEauMove[2][4] = ImageIO.read(new File("Carapuce_walkleft1.png"));  //deplacement vers la gauche
			PokemonEauMove[2][5] = ImageIO.read(new File("Carapuce_walkleft2.png"));
			PokemonEauMove[2][6] = ImageIO.read(new File("Carapuce_walkleft3.png"));
			PokemonEauMove[2][7] = ImageIO.read(new File("Carapuce_walkleft4.png"));
			
			PokemonEauMove[3][0] = ImageIO.read(new File("Carapuce_walkright1.png"));
			PokemonEauMove[3][1] = ImageIO.read(new File("Carapuce_walkright2.png"));
			PokemonEauMove[3][2] = ImageIO.read(new File("Carapuce_walkright3.png"));
			PokemonEauMove[3][3] = ImageIO.read(new File("Carapuce_walkright4.png"));
			PokemonEauMove[3][4] = ImageIO.read(new File("Carapuce_walkright1.png"));  //deplacement vers la droite
			PokemonEauMove[3][5] = ImageIO.read(new File("Carapuce_walkright2.png"));
			PokemonEauMove[3][6] = ImageIO.read(new File("Carapuce_walkright3.png"));
			PokemonEauMove[3][7] = ImageIO.read(new File("Carapuce_walkright4.png"));
			
			PokemonEauEvolueMove = new Image[4][8];  //carabaffe
			PokemonEauEvolueMove[0][0] = ImageIO.read(new File("Carabaffe_walkdown1.png"));
			PokemonEauEvolueMove[0][1] = ImageIO.read(new File("Carabaffe_walkdown2.png"));
			PokemonEauEvolueMove[0][2] = ImageIO.read(new File("Carabaffe_walkdown3.png"));
			PokemonEauEvolueMove[0][3] = ImageIO.read(new File("Carabaffe_walkdown4.png"));
			PokemonEauEvolueMove[0][4] = ImageIO.read(new File("Carabaffe_walkdown1.png"));  //deplacement vers le bas
			PokemonEauEvolueMove[0][5] = ImageIO.read(new File("Carabaffe_walkdown2.png"));
			PokemonEauEvolueMove[0][6] = ImageIO.read(new File("Carabaffe_walkdown3.png"));
			PokemonEauEvolueMove[0][7] = ImageIO.read(new File("Carabaffe_walkdown4.png"));
			
			PokemonEauEvolueMove[1][0] = ImageIO.read(new File("Carabaffe_walkup1.png"));
			PokemonEauEvolueMove[1][1] = ImageIO.read(new File("Carabaffe_walkup2.png"));
			PokemonEauEvolueMove[1][2] = ImageIO.read(new File("Carabaffe_walkup3.png"));
			PokemonEauEvolueMove[1][3] = ImageIO.read(new File("Carabaffe_walkup4.png"));
			PokemonEauEvolueMove[1][4] = ImageIO.read(new File("Carabaffe_walkup1.png"));  //deplacement vers le haut
			PokemonEauEvolueMove[1][5] = ImageIO.read(new File("Carabaffe_walkup2.png"));
			PokemonEauEvolueMove[1][6] = ImageIO.read(new File("Carabaffe_walkup3.png"));
			PokemonEauEvolueMove[1][7] = ImageIO.read(new File("Carabaffe_walkup4.png"));
			
			PokemonEauEvolueMove[2][0] = ImageIO.read(new File("Carabaffe_walkleft1.png"));
			PokemonEauEvolueMove[2][1] = ImageIO.read(new File("Carabaffe_walkleft2.png"));
			PokemonEauEvolueMove[2][2] = ImageIO.read(new File("Carabaffe_walkleft3.png"));
			PokemonEauEvolueMove[2][3] = ImageIO.read(new File("Carabaffe_walkleft4.png"));
			PokemonEauEvolueMove[2][4] = ImageIO.read(new File("Carabaffe_walkleft1.png"));  //deplacement vers la gauche
			PokemonEauEvolueMove[2][5] = ImageIO.read(new File("Carabaffe_walkleft2.png"));
			PokemonEauEvolueMove[2][6] = ImageIO.read(new File("Carabaffe_walkleft3.png"));
			PokemonEauEvolueMove[2][7] = ImageIO.read(new File("Carabaffe_walkleft4.png"));
			
			PokemonEauEvolueMove[3][0] = ImageIO.read(new File("Carabaffe_walkright1.png"));
			PokemonEauEvolueMove[3][1] = ImageIO.read(new File("Carabaffe_walkright2.png"));
			PokemonEauEvolueMove[3][2] = ImageIO.read(new File("Carabaffe_walkright3.png"));
			PokemonEauEvolueMove[3][3] = ImageIO.read(new File("Carabaffe_walkright4.png"));
			PokemonEauEvolueMove[3][4] = ImageIO.read(new File("Carabaffe_walkright1.png"));  //deplacement vers la droite
			PokemonEauEvolueMove[3][5] = ImageIO.read(new File("Carabaffe_walkright2.png"));
			PokemonEauEvolueMove[3][6] = ImageIO.read(new File("Carabaffe_walkright3.png"));
			PokemonEauEvolueMove[3][7] = ImageIO.read(new File("Carabaffe_walkright4.png"));
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}

		frame = new JFrame("World of Sprite");
		frame.add(this);
		x=dx*spriteLength;
		y=dy*spriteLength;
		frame.setSize(x,y+37);
		frame.setVisible(true);
		vitesse=30;
		a1=0;
		a2=0;
		wx=dx;
		wy=dy;
	}

	public void paint(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ) {
					try{
						if (jour%12 < 3)
							g2.drawImage(grassSprite,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						if (jour%12 >=3 && jour%12<6)
							g2.drawImage(grassSprite,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						if (jour%12>=6 && jour%12<9)	
							g2.drawImage(grassSpriteA,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
						if (jour%12>=9)
							g2.drawImage(grassSpriteH,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
					if (Terrain.getTerrain()[i][j][1] >= (Terrain.getTerre() - 2) && Terrain.getTerrain()[i][j][1] <= (Terrain.getTerre()+2))
						g2.drawImage(terreSprite,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
					if (Terrain.getTerrain()[i][j][1] < Terrain.getEau())
						g2.drawImage(waterSprite,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
					for (int a=0;a<Monde.getcarte_Ab().size();a++) {
						if (Monde.getcarte_Ab().get(a).getX()==i && Monde.getcarte_Ab().get(a).getY()==j) {
							if (Monde.getcarte_Ab().get(a).isEnfeu()) {
								g2.drawImage(arbrecrame,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
								g2.drawImage(Flamme,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
							}
							else {
								if (Monde.getcarte_Ab().get(a).getGrille()) {
									g2.drawImage(arbrecrame,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}else {
									if (jour%12 < 6)
										g2.drawImage(tSprite,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
									if (jour%12>=6 && jour%12<9)
										g2.drawImage(tSpriteA,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
									if (jour%12>=9)
										g2.drawImage(tSpriteH,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);

								}
							}
						}
												}
					if (Terrain.getTerrain()[i][j][0] >= Terrain.contourRoche() && Terrain.getTerrain()[i][j][0] < Terrain.sommetVolcan()) {
						if(Terrain.getTerrain()[i][j][0] < (Terrain.sommetVolcan()-7)) {
							if(Terrain.getTerrain()[i][j][0] == Terrain.contourRoche()) {
								g2.drawImage(rochevolcan,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength,spriteLength, frame);
							}
							else {
								g2.drawImage(rochevolcan,spriteLength*(i-a1)-25,spriteLength*(j-a2)-10,spriteLength+25,spriteLength+10, frame);
							}
						}else {
							g2.drawImage(rochevolcan,spriteLength*(i-a1)-10,spriteLength*(j-a2)-25,spriteLength+10,spriteLength+25, frame);
						}
					}
					}catch(Exception E) {
						
					}
					for (int p=0;p<Monde.getcarte_P().size();p++) {
						try{
						if (Monde.getcarte_P().get(p).getX()==i && Monde.getcarte_P().get(p).getY()==j) {
							if (Monde.getcarte_P().get(p).isEstPourrie() == false)
								g2.drawImage(Apple,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength-10,spriteLength-10, frame);
							if (Monde.getcarte_P().get(p).isEstPourrie() == true)
								g2.drawImage(ApplePourri,spriteLength*(i-a1),spriteLength*(j-a2),spriteLength-10,spriteLength-10, frame);		
						}}catch(Exception E) {
							
						}
					}
			}
		}
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ) {
				try {
					if(Terrain.getTerrain()[i][j][0] >= (Terrain.contourRoche() + 3) && Terrain.getTerrain()[i][j][0] < (Terrain.sommetVolcan() - 2)) {
						g2.drawImage(rochevolcan,spriteLength*(i-a1)-35,spriteLength*(j-a2)-15,spriteLength+35,spriteLength+15, frame);
					}
					
				}catch(Exception e) {}
			}
		}
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ) {
				try {
					if(Terrain.getTerrain()[i][j][0] >= (Terrain.contourRoche() + 7) && Terrain.getTerrain()[i][j][0] <= (Terrain.sommetVolcan() - 1)) {
						g2.drawImage(rochevolcan,spriteLength*(i-a1)-35,spriteLength*(j-a2)-15,spriteLength+35,spriteLength+15, frame);
					}
					
				}catch(Exception e) {}
			}
		}
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ) {
				try {
					if(Terrain.getTerrain()[i][j][1] == Terrain.SolLave()) {
						g2.drawImage(Lave,spriteLength*(i-a1)-35,spriteLength*(j-a2)-15,spriteLength+35,spriteLength+15, frame);
					}
					
				}catch(Exception e) {}
			}
		}
		for ( int i = a1 ; i < wx ; i++ ) {
			for ( int j = a2 ; j < wy ; j++ ){
					ArrayList<Object> array_m=Monde.getcarte_Ag();
					for (int m=0;m<array_m.size();m++) {
						if (array_m.get(m) instanceof M1 && ((M1)array_m.get(m)).getX()==i && ((M1)array_m.get(m)).getY()==j) {
							M1 Hericendre = (M1)(array_m.get(m));
							
							if(Hericendre.getEvolution() == false) {
								if ( Hericendre.getSens() == 0 ) { //va a gauche
									g2.drawImage(PokemonFeuMove[2][pas],spriteLength*(i-a1) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 1 ) { //va a droite
									g2.drawImage(PokemonFeuMove[3][pas],spriteLength*(i-a1) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 2 ) { //va en bas
									g2.drawImage(PokemonFeuMove[0][pas],spriteLength*(i-a1) ,spriteLength*(j-a2) + SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 3 ) { //va en haut
									g2.drawImage(PokemonFeuMove[1][pas],spriteLength*(i-a1) ,spriteLength*(j-a2) - SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 4 ) { //reste sur place
									g2.drawImage(PokemonFeuMove[2][pas],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 5 ) { //reste sur place
									g2.drawImage(PokemonFeuMove[3][pas],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 6 ) { //reste sur place
									g2.drawImage(PokemonFeuMove[0][pas],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 7 ) { //reste sur place
									g2.drawImage(PokemonFeuMove[1][pas],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
							}
							if(Hericendre.getEvolution() == true) {
								
								if ( Hericendre.getSens() == 0 ) { //va a gauche
									g2.drawImage(PokemonFeuEvolueMove[2][pas],spriteLength*(i-a1) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								
								if ( Hericendre.getSens() == 1 ) { //va a droite
									g2.drawImage(PokemonFeuEvolueMove[3][pas],spriteLength*(i-a1) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 2 ) { //va en bas
									g2.drawImage(PokemonFeuEvolueMove[0][pas],spriteLength*(i-a1) ,spriteLength*(j-a2) + SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 3 ) { //va en haut
									g2.drawImage(PokemonFeuEvolueMove[1][pas],spriteLength*(i-a1) ,spriteLength*(j-a2) - SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 4 ) { //reste sur place
									g2.drawImage(PokemonFeuEvolueMove[1][pas],spriteLength*(i-a1) ,spriteLength*(j-a2) ,spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 5 ) { //reste sur place
									g2.drawImage(PokemonFeuEvolueMove[3][pas],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 6 ) { //reste sur place
									g2.drawImage(PokemonFeuEvolueMove[0][pas],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Hericendre.getSens() == 7 ) { //reste sur place
									g2.drawImage(PokemonFeuEvolueMove[1][pas],spriteLength*(i-a1) ,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
							}
							continue;
						}

						if (array_m.get(m) instanceof M2 && ((M2)array_m.get(m)).getX()==i && ((M2)array_m.get(m)).getY()==j) {
							M2 Carapuce = (M2)(array_m.get(m));
							
							if(Carapuce.getEvolution() == false) {
								if ( Carapuce.getSens() == 0 ) { //va a gauche
									g2.drawImage(PokemonEauMove[2][pas],spriteLength*(i-a1) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 1 ) { //va a droite
									g2.drawImage(PokemonEauMove[3][pas],spriteLength*(i-a1) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 2 ) { //va en bas
									g2.drawImage(PokemonEauMove[0][pas],spriteLength*(i-a1) ,spriteLength*(j-a2) + SpriteDemo.marcher,spriteLength-((int)spriteLength/5),spriteLength, frame);
								}
								if ( Carapuce.getSens() == 3 ) { //va en haut
									g2.drawImage(PokemonEauMove[1][pas],spriteLength*(i-a1) ,spriteLength*(j-a2) - SpriteDemo.marcher,spriteLength-((int)spriteLength/5),spriteLength, frame);
								}
							}
							if(Carapuce.getEvolution() == true) {
								if ( Carapuce.getSens() == 0 ) { //va a gauche
									g2.drawImage(PokemonEauEvolueMove[2][pas],spriteLength*(i-a1) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								
								if ( Carapuce.getSens() == 1 ) { //va a droite
									g2.drawImage(PokemonEauEvolueMove[3][pas],spriteLength*(i-a1) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 2 ) { //va en bas
									g2.drawImage(PokemonEauEvolueMove[0][pas],spriteLength*(i-a1) ,spriteLength*(j-a2) + SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
								if ( Carapuce.getSens() == 3 ) { //va en haut
									g2.drawImage(PokemonEauEvolueMove[1][pas],spriteLength*(i-a1) ,spriteLength*(j-a2) - SpriteDemo.marcher,spriteLength,spriteLength, frame);
								}
							}
							continue;
						}
						if (array_m.get(m) instanceof Braconnier && ((Braconnier)array_m.get(m)).getX()==i && ((Braconnier)array_m.get(m)).getY()==j) {
							Braconnier braconnier = (Braconnier)(array_m.get(m));
							
								if ( braconnier.getSens() == 0 ) { //va a gauche
									g2.drawImage(Chasseur,spriteLength*(i-a1) - SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( braconnier.getSens() == 1 ) { //va a droite
									g2.drawImage(Chasseur,spriteLength*(i-a1) + SpriteDemo.marcher,spriteLength*(j-a2),spriteLength,spriteLength, frame);
								}
								if ( braconnier.getSens() == 2 ) { //va en bas
									g2.drawImage(Chasseur,spriteLength*(i-a1) ,spriteLength*(j-a2) + SpriteDemo.marcher,spriteLength-((int)spriteLength/5),spriteLength, frame);
								}
								if ( braconnier.getSens() == 3 ) { //va en haut
									g2.drawImage(Chasseur,spriteLength*(i-a1) ,spriteLength*(j-a2) - SpriteDemo.marcher,spriteLength-((int)spriteLength/5),spriteLength, frame);
								}
							continue;
						}
					
						
					}
			}
		}
		if (jour%12 >=3 && jour%12<6)
			g2.drawImage(soleilF,0,0,spriteLength*wx,spriteLength*wy, frame);
		
		laps= Math.abs((time_init-(System.nanoTime()/1000000000)));
		if (laps%10<5) {
			g2.drawImage(nuitF,0,0,spriteLength*wx,spriteLength*wy, frame);
			duree=true;
		}else {
			if (duree)
				jour+=1;
			duree=false;
		}
	}
	@Override
	public void keyPressed(KeyEvent evmt) {
		//System.out.println(""+(wx-a1)*spriteLength+" "+this.getBounds().width+" | "+a1*spriteLength);
		int source =evmt.getKeyCode();
		if (source == KeyEvent.VK_RIGHT) {
			if (vitesse == 20)
				vitesse=10;
			if (vitesse == 30)
				vitesse=20;
			
		}
		if (source == KeyEvent.VK_LEFT) {
			if (vitesse == 20)
				vitesse=30;
			if (vitesse == 10)
				vitesse=20;			
		}
		if (source == 107) {
			spriteLength+=10;

			x=dx*spriteLength;
			y=dy*spriteLength;
			frame.setSize(x,y+37);
			frame.setVisible(true);
		}
		if (source == 109) {
			spriteLength-=10;
			x=dx*spriteLength;
			y=dy*spriteLength;
			frame.setSize(x,y+37);
			frame.setVisible(true);
		}
		if (source == KeyEvent.VK_Z) {
			if (a2>0) {
				a2-=1;
				wy-=1;	
			}
		}
		if (source == KeyEvent.VK_S) {
			if ((wy-a2)*spriteLength > this.getBounds().height+(a2+1)*spriteLength) {
				a2+=1;
				wy+=1;				
			}			
		}
		if (source == KeyEvent.VK_Q) {
			if (a1>0) {
				a1-=1;
				wx-=1;
			}
		}
		if (source == KeyEvent.VK_D) {
			if ((wx-a1)*spriteLength >= this.getBounds().width+(a1+1)*spriteLength) {
				a1+=1;
				wx+=1;				
			}
		}
}						
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//e.getLocationOnScreen()
		//System.out.println(""+(e.getLocationOnScreen().x-this.x) + " "+(e.getLocationOnScreen().y-this.getWidth()));
		
		if (e.getButton()==1) {
			System.out.println(""+(e.getX())+ " "+(e.getY()));
			System.out.println(""+(e.getX()/spriteLength)+ " "+(e.getY()/spriteLength));
			//System.out.println(""+((e.getX() - frame.getX())/spriteLength)+ " "+((e.getY() - frame.getY()-37))/spriteLength);
			System.out.println("----------");
			//a1=(e.getX()/spriteLength);
			//a2=(e.getY()/spriteLength);
			a1=Math.abs((e.getX()/spriteLength)-1);
			a2=Math.abs((e.getY()/spriteLength)-1);
			wx=a1+3;
			wy=a2+3;
			//spriteLength+=2;
			//spriteLength+=10;
			//spriteLength+=10;
			//a1=2;
			//a2=2;
			//System.exit(0);			
		}
		if (e.getButton()==3) {
			System.out.println(""+(e.getX() - frame.getX())+ " "+(e.getY() - frame.getY()+27));
			//spriteLength+=10;
			a1=0;
			a2=0;
			wx=dx;
			wy=dy;
			//spriteLength=40;
			//spriteLength-=10;
			//System.exit(0);			
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
             spriteLength+=1;  // scroll ver le haut 
        } else {
           if (frame.getWidth()+10 <= spriteLength*dx || frame.getWidth()+10 <= spriteLength*dy) 
        	   spriteLength-=1; // scroll ver le haut 
          }
    } 
	
	public static void main(String[] args) {
		Terrain terrain= new Terrain(dx=100,dy=100);
		Monde monde = new Monde(dx,dy,0.01,0.5);
		SpriteDemo a =new SpriteDemo();
		//System.exit(0);
        a.addKeyListener(a);
        a.addMouseListener(a);
        a.addMouseWheelListener(a);
        a.setFocusable(true);
		//System.out.println(""+((M1) Monde.getCarte().get(0)).getSens());
		//monde.detail();
		//System.exit(123);
		cpt_pas = 0;
		marcher = 0;
		step = 0;
		while(true) {
			if(pas < 7) {
				a.repaint();
	            pas += 1;
			}
			else {
				a.repaint();
				pas = 0;
			}
			if(cpt_pas % 8 == 0) {
				cycle_volcan++;
				monde.pomme_pop(step);
				Pomme.duree();
				Pomme.delete();
				monde.Refresh();
				cpt_pas = 0;
				marcher = 0;
			//	terrain.Stockage_passage();
				Monde.grandir();
			//	M.reproduction();
				if(cycle_volcan % 100 == 0) {
					terrain.partir_lave();
				}
				terrain.propagation_lave();
				terrain.eruption();
				monde.arbreMourir();
				monde.depart_feu();
				monde.propagation_F();
				monde.enfeu();
			}
			marcher += (int)(spriteLength/8);
			try{
			Thread.sleep(a.vitesse); // en ms
			}catch(Exception e){
				e.printStackTrace();
			}
			cpt_pas += 1;
			step++;
		}
	}

}
