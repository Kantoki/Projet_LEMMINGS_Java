package Mulot;
import java.awt.Point;

import Jeu.Mulot;
import Jeu.Niveau;


public class MulotCuirass� extends MulotBasique{

	public MulotCuirass�(int x, int y, int chute, char num,int sens) {
		super(x, y, chute, num,sens);
	}

	
	public MulotCuirass�(Mulot m){
		super(m);
	}
	
	@Override
	public boolean sefaitpi�ger(){
		return false;
	}
	
	@Override
	public char gettype() {
		return 'c';
	}

	
}
