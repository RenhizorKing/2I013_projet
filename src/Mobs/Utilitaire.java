package Mobs;

public class Utilitaire {
	
	public static double distance2O(M m, Braconnier b) {
		double x1= Math.abs(m.getX() - b.getX()) ;
		double x2 =Math.abs(m.getY() - b.getY());
		double r = Math.sqrt(x1*x1 + x2*x2);
		if (r<=1) {
			System.out.println(""+r);
			//System.out.println(""+m.getX()+" "+m.getY());
			//System.out.println(""+b.getX()+" "+b.getY());
			System.out.println("-------");
			//System.exit(0);
		}
		return r;
	
	}
}
