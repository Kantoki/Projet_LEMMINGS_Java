package Mulot;

import java.awt.Point;

import Jeu.Mulot;
import Jeu.Niveau;


public class MulotMarcheur  extends MulotBasique {

	
	
	
	public MulotMarcheur(int x,int y,int chute,char num,int sens){
		super(x,y,chute,num,sens);
	}
	
	public MulotMarcheur(int x,int y,char num,int sens){
		super(x,y,num,sens);
	}
	
	
	
	public MulotMarcheur(Mulot m){
		super(m);
	}
	
	
	
	
}
