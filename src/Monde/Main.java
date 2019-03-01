package Monde;

public class Main {
	public static void main(String[] args) {
		Monde monde = new Monde(18, 18, 100);
		//monde.detail();
		//System.exit(0);
		int step=0;
		while(true) {
			monde.Afficher();
			monde.Refresh();
			monde.pomme_pop(step);
			try{
				Thread.sleep(500); // en ms
			}catch(Exception e){
				e.printStackTrace();
			}
			step++;
		}
	}
}