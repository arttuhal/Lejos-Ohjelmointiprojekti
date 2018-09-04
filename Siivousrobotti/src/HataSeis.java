import lejos.hardware.Button;
import lejos.robotics.subsumption.*;

public class HataSeis extends Thread {

	private Behavior b1, b2;
	private Arbitrator arby;
	private Boolean valmis = false;

	public HataSeis(Behavior b1, Behavior b2) {
		this.b1 = b1;
		this.b2 = b2;
	}

	public void run() {
		while(Button.UP.isUp()) {
			
		}
		System.exit(0);
	}

	public void lopeta() {
		valmis = true;
	}
}
