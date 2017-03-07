package Mulot;

import Jeu.Mulot;
import Jeu.Niveau;

public class MulotGrimpeur extends MulotBasique{

	private int anciensens;
	
	public MulotGrimpeur(int x, int y,int chute, char num, int sens2) {
		super(x, y,chute, num, sens2);
		if (sens2 != 0){
			anciensens = sens2;
		}else {
			anciensens = 1;
		}
	} 

	
	
	public MulotGrimpeur(Mulot m) {
		super(m);
		if (m.sens() != 0){
		anciensens = m.sens();
	}else {
		anciensens = 1;
	}
	
	}



	@Override
	public boolean avance(Niveau n,int i){
		if (n.get(this.getmulot().x + anciensens, this.getmulot().y) == '|' || n.get(this.getmulot().x + anciensens , this.getmulot().y + anciensens ) == '|'){
			System.out.println("pas lol");
			setsens(0);
			
		} else {
			System.out.println("lol");
			System.out.println(anciensens);
			setsens(anciensens);
		}
		
		if (this.sens() == 0){
			if (this.getmulot().x + 1 > n.getnbc() || this.getmulot().x -1 < 0 ||this.getmulot().y + 1 > n.getnbl() || this.getmulot().y -1 < 0){
				this.setchute(90);
				return false;
			}
			return n.déplacer(this, 0, -1, this.getchute(), i);
		}else{
			
			return super.avance(n,i);
		}
			
		
		
	}
	
	
	
	@Override
	public boolean chute(Niveau n,int i){
		if(this.sens() == 0){
			return false;
		}
		if (this.getmulot().y + 1 >= n.getnbl() || (n.get(this.getmulot().x, this.getmulot().y + 1) != ' '
				&& n.get(this.getmulot().x, this.getmulot().y + 1) != 'S')){
			return false;
		}
		this.setchute(this.getchute() + 1);
		if (this.mort()){
			System.out.println("lol"+this.gettype());
		}
		return n.déplacer(this, 0, 1,this.getchute(),i);
	}
	
	
	@Override
	public char gettype(){
		return 'g';
	}
}
