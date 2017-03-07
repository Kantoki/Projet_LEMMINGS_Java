package Jeu;
import java.awt.Point;


public interface Mulot {
	
	public boolean avance(Niveau n,int i);
	public boolean chute(Niveau n, int i);
	public Point getmulot();
	public boolean mort();
	public int getchute();
	public char gettype();
	public boolean sefaitpiéger();
	public char getnum();
	int sens();
	void setsens(int i);
	void setchute(int i);
	
	
	
	
}
