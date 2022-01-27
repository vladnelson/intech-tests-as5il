package jardin.flore;

public abstract class Vegetal {

	protected char dessin[];
	protected Etat etat;
	
	public Etat getEtat() {
		return etat;
	}
	
	public Vegetal() {
		dessin = new char[6];
		dessin[0] = '_';
		dessin[1] = '.';
		dessin[2] = '|';
		dessin[5] = '#';	
		etat = Etat.GRAINE;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.dessin[etat.ordinal()]);
	}
	
	public void grandir() {
		grandir(1);
	}
	
	public void grandir(int step) {
		this.etat = Etat.values()[this.etat.ordinal()+step];
	}
	
}