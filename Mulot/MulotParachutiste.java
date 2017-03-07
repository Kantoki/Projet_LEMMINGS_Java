package Mulot;

import static outils.Outils.*;

import java.awt.Point;

import Jeu.Mulot;
import Jeu.Niveau;


public class MulotParachutiste extends MulotBasique {

	
	public MulotParachutiste(int x,int y, char num,int sens){
		
		super(x,y,num,sens);
	}
	
	public MulotParachutiste(Mulot m){
		super(m);
		
	}
	

	@Override
	public boolean mort() {
		return false;
		
	}
	
	public char gettype() {
		return 'p';
	}

	
	
}
