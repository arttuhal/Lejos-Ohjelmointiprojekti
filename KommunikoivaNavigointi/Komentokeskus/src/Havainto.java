import lejos.robotics.navigation.Waypoint;

public class Havainto {

	private int numero;
	private float x;
	private float y;
	private long aika;
	private int arvo;

	public Havainto(int numero, float x, float y, long aika, int arvo) {
		this.numero = numero;
		this.x = x;
		this.y = y;
		this.aika = aika;
		this.arvo = arvo;
	}

	public Havainto() {

	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public long getAika() {
		return aika;
	}

	public void setAika(long aika) {
		this.aika = aika;
	}

	public int getArvo() {
		return arvo;
	}

	public void setArvo(int arvo) {
		this.arvo = arvo;
	}

	@Override
	public String toString() {
		return "Havainto " + numero + ". koordinaateissa (" + x + "," + y + "). Aika: " + aika + ". Arvo: " + arvo
				+ ".";
	}
}
