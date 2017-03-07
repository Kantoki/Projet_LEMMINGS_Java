package FabriqueMulot;

import static outils.Outils.*;
import Jeu.Mulot;
import Mulot.MulotCuirassé;
import Mulot.MulotGrimpeur;
import Mulot.MulotMarcheur;
import Mulot.MulotParachutiste;



public class FabriqueMulot {
	
	public FabriqueMulot(){
	}
	
	public Mulot Fabriquer(char c,Mulot m){
		switch(Character.toLowerCase(c)){
		case 'p':
			return new MulotParachutiste(m);
			
		case 'm':
			return new MulotMarcheur(m);
		case 'c':
			return new MulotCuirassé(m);
		case 'g':
			return new MulotGrimpeur(m);
		default:
			return m;
		}	
	}
	
	public Mulot Fabriquer(char c,int x,int y,int chute,char num,int sens){
		switch(Character.toLowerCase(c)){
		case 'p':
			return new MulotParachutiste(x,y,num,sens);
		case 'm':
			return new MulotMarcheur(x,y,chute,num,sens);
		case 'c':
			return new MulotCuirassé(x,y,chute,num,sens);
		case 'g':
			return new MulotGrimpeur(x,y,chute,num,sens);
		default:
			return null;
		}	
	}
	
	public Mulot Fabriquer(int x,int y,int chute,char num){
		return new MulotMarcheur(x,y,chute,num,1);
	}
	
		
}
