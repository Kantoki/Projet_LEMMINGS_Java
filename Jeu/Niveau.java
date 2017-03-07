package Jeu;
import java.awt.Point;

import static outils.Outils.*;

import java.util.ArrayList;
import java.util.Arrays;

import FabriqueMulot.FabriqueMulot;

/**
 * Classe repr�sentant un niveau de jeu.
 * 
 * Attention, cette classe est intentionnellement mal con�ue. En particulier,
 * elle met en oeuvre des donn�es redondantes.
 */
public class Niveau {
	// nombre de lignes et de colonnes du niveau
	private int nbc, nbl;
	// ce qui doit �tre affich�
	private char[] tab;
	// coordonn�es de l'entr�e et de la sortie (redondance avec tab)
	private Point entr�e, sortie;
	// liste des coordonn�es des mulots (redondance avec tab)
	private ArrayList<Mulot> mulots = new ArrayList<Mulot>();
	private int victoire;
	private int mulotsGG = 0;
	

	private static char convert(int num) {
		return (char) ('0' + num);
	}

	public char get(int x, int y) {
		return tab[y * nbc + x];
	}

	private void set(int x, int y, char c) {
		tab[y * nbc + x] = c;
	}

	private void setl(int x, int y,int i,char c){
		tab[x*nbc + i*nbc] = c;
	}
	public int getnbc() {
		return nbc;
	}

	public int getnbl() {
		return nbl;
	}

	public Point getsortie() {
		return sortie;
	}

	/**
	 * Construit un niveau vide.
	 * 
	 * @param nbc
	 *            et nbl Nombre de lignes et de colonnes
	 * @param ex
	 *            et ey Coordonn�e de l'entr�e
	 * @param sx
	 *            et sy Coordonn�e de la sortie
	 */
	public Niveau(int nbc, int nbl, int ex, int ey, int sx, int sy,int victoire) {
		this.nbc = nbc;
		this.nbl = nbl;
		tab = new char[nbc * nbl];
		Arrays.fill(tab, ' ');
		entr�e = new Point(ex, ey);
		sortie = new Point(sx, sy);
		set(ex, ey, 'E');
		set(sx, sy, 'S');
		this.victoire = victoire;
	}

	/**
	 * Ajoute une plateforme au niveau.
	 * 
	 * @param gx
	 *            et gy Coordonn�e du point gauche de la palteforme
	 * @param nb
	 *            Longueur de la plateforme
	 */
	public void plateforme(int gx, int gy, int nb) {
		for (int col = gx; col < gx + nb; ++col)
			set(col, gy, '=');
	}
	
	
	
	
	public void mur(int gx, int gy, int nb) {
		for (int i = 0; i < nb;i++){
			set (gx,gy,'|');
			gx = gx + nbc;
		}
	}
	
	public void pi�ge(int x,int y){
		set(x,y,'P');
	}
	
	

	/**
	 * Affiche l'�tat courant du niveau.
	 */
	public void afficher() {
		for (int lig = 0; lig < nbl; ++lig) {
			for (int col = 0; col < nbc; ++col)
				System.out.print(get(col, lig));
			System.out.println();
		}
	}

	/**
	 * Ajoute un mulot au niveau.
	 */
	public void naissance() {
		// la case en dessous de l'entr�e doit �tre libre
		if (get(entr�e.x, entr�e.y + 1) != ' ')
			return;
		// un mulot est repr�sent� par une entr�e dans la liste
		// la r�f�rence est mise � null lorsqu'il sort du niveau
		FabriqueMulot f = new FabriqueMulot();
		Mulot m = f.Fabriquer(entr�e.x,entr�e.y + 1,0,convert(mulots.size() + 1));
		System.out.println(m.sens());
		mulots.add(m);
		set(entr�e.x, entr�e.y + 1,m.getnum());
	}

	/**
	 * Fait avancer chaque mulot encore pr�sent.
	 */
	public void tic() {
		int i = 0;
		for (Mulot mulot : mulots) {
			if (mulot != null ){
				if (!mulot.mort()){	
					System.out.println(mulot.gettype());
					if (!mulot.chute(this,i))
						
						mulot.avance(this,i);
				}
				else{
					set(mulot.getmulot().x,mulot.getmulot().y, ' ');
				}
			}
			i++;
		}

	}

	public boolean d�placer(Mulot coord, int dx, int dy, int chute,int i) {
		
		FabriqueMulot f = new FabriqueMulot();
		int x = coord.getmulot().x;
		int y = coord.getmulot().y;
		
		
		Mulot c = f.Fabriquer(coord.gettype(),x + dx, y + dy, chute,coord.getnum(),coord.sens());		// sortie des limites de l'ecran ?
		if (c.getmulot().x >= nbc || c.getmulot().y >= nbl){
			return false;
			}
		
		if(get(c.getmulot().x,c.getmulot().y)=='P'){

			pi�ger(coord,dx,dy);
			return false;
		}
		
		
		set(x, y, ' '); // on efface le mulot
		set(c.getmulot().x, c.getmulot().y,convert(i+1)); // le revoila juste a cote
		mulots.set(i, c);
		// il est sorti ?
		if (c.getmulot().equals(sortie)) {
			set(c.getmulot().x, c.getmulot().y, 'S');
			mulots.set(i, null);
			mulotsGG++;
		}
		
		return true;
		
		
	}

	

	private void pi�ger(Mulot coord,int dx,int dy) {
		pause(0.5);
		System.out.println(coord.gettype());
		if (coord.gettype() == 'c'){
		set (coord.getmulot().x + dx,coord.getmulot().y + dy,' ');
		}
		if (coord.gettype() != 'c'){
			set(coord.getmulot().x,coord.getmulot().y,' ');
			tuermulot(coord);
		}
		
	}

	
	

	private void tuermulot(Mulot coord) {
		mulots.set(mulots.indexOf(coord),null);
		
	}

	public void changerprofession(int i, char c) {
		FabriqueMulot f = new FabriqueMulot();
		
		Mulot m = f.Fabriquer(c,mulots.get(i-1));
		mulots.set(i-1, m);
	}
	
	/**
	 * Indique si le niveau est fini (i.e. tous les mulots sont sortis).
	 */
	
	public boolean fini() {
		boolean fini = true;
		
		
		for (Mulot c : mulots)
			if (c != null )
				if(get(c.getmulot().x, c.getmulot().y) != ' ')
				fini = false;
		if (mulotsGG >= victoire){
			fini = true;
			victoire = 100;
		}
		return fini;
	}
	
	
	public int getvictoire(){
		return victoire;
	}
	
}
