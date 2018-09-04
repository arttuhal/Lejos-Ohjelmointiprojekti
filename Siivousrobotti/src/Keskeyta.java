import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.*;

public class Keskeyta implements Behavior {
	private volatile boolean suppressed = false;
	private Motors motors;
	private int counter;
	private KaukoOhjain kaukoOhjain;
	private int komento;
	private boolean kaukosaadinKaytossa = false;

	public Keskeyta(Motors motors, KaukoOhjain kaukoOhjain) {
		this.motors = motors;
		this.counter = 0;
		this.kaukoOhjain = kaukoOhjain;
	}

	public boolean takeControl() {
		komento = kaukoOhjain.getCommand();
		
		if (komento == 9 || Button.DOWN.isDown()) {
			if (komento == 9) {
				kaukosaadinKaytossa = true;
			} else {
				kaukosaadinKaytossa = false;
			}
			return true;
		} else {
			return false;
		}
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		motors.Stop();
		while (!suppressed && (Button.DOWN.isDown() || kaukoOhjain.getCommand() == 9))
			Thread.yield();
		if (!kaukosaadinKaytossa) {
			Button.ENTER.waitForPressAndRelease();
		}
	}

}
