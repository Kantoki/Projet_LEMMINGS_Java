package Mulot;
import java.awt.Point;

import Jeu.Mulot;
import Jeu.Niveau;


public class MulotCuirassé extends MulotBasique{

	public MulotCuirassé(int x, int y, int chute, char num,int sens) {
		super(x, y, chute, num,sens);
	}

	
	public MulotCuirassé(Mulot m){
		super(m);
	}
	
	@Override
	public boolean sefaitpiéger(){
		return false;
	}
	
	@Override
	public char gettype() {
		return 'c';
	}

	
}
