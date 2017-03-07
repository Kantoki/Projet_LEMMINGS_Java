package Mulot;
import static outils.Outils.*;
import java.awt.Point;

import Jeu.Mulot;
import Jeu.Niveau;

public class MulotBasique implements Mulot{
	private Point mulot;
	private int chute;
	private char numéro;
	private int sens;
	
	public MulotBasique(int x,int y,int chute,char num,int sens){
		mulot = new Point(x,y);
		this.chute = chute;
		numéro = num;
		this.sens = sens;

	}
	
	
	public MulotBasique(Mulot m){
		mulot = new Point (m.getmulot().x,m.getmulot().y);
		chute = m.getchute();
		sens = m.sens();
	
	}
	
	public MulotBasique(int x, int y, char num, int sens2) {
		mulot = new Point(x,y);
		numéro = num;
		sens = sens2;
	}


	@Override
	public boolean avance(Niveau n, int i) {
		if (n.get(mulot.x+sens,mulot.y) == '|'){
		sens = -sens;
		}
		if (mulot.x + 1 + i >= n.getnbc() || (n.get(mulot.x + 1 + i, mulot.y) != ' '
				&& n.get(mulot.x + 1 + i, mulot.y) != 'S' && n.get(mulot.x + 1 + i, mulot.y) != 'P' && n.get(mulot.x+ 1 + i,mulot.y) != '|')){
			return false;
		}
		chute = 0;
		if (sens == -1){
				return n.déplacer(this, -1, 0, chute, i);	
		}
		return n.déplacer(this, 1, 0,chute,i);
	}

	public boolean chute(Niveau n, int i) {
		if (mulot.y + 1 >= n.getnbl() || (n.get(mulot.x, mulot.y + 1) != ' '
				&& n.get(mulot.x, mulot.y + 1) != 'S')){
			return false;
		}
		chute += 1;
		if (this.mort()){
			System.out.println("lol"+this.gettype());
		}
		return n.déplacer(this, 0, 1,chute,i);
	}
	

	@Override
	public Point getmulot() {
		return mulot;
	}

	@Override
	public boolean mort() {
		return chute > 8;
	}

	@Override
	public int getchute() {
		return chute;
	}
	@Override
	public void setchute(int i){
		chute = i;
	}

	@Override
	public char gettype() {
		return 'm';
	}

	@Override
	public boolean sefaitpiéger() {
		return true;
	}

	@Override
	public char getnum() {
		return numéro;
	}


	@Override
	public int sens(){
		return sens;
	}
	
	
	@Override
	public void setsens(int i){
		sens = i;
	}
	
}
