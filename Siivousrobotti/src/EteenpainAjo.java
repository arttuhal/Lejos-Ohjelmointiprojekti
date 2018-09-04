import lejos.hardware.Button;
import lejos.robotics.subsumption.*;

public class EteenpainAjo implements Behavior {
	private volatile boolean suppressed = false;
	private Motors motors;

	public EteenpainAjo(Motors motors) {
		this.motors = motors;
	}

	public boolean takeControl() {
//		if (!Button.UP.isUp()) {
//			return false;
//		} else {
//			return true;
//		}
		return true;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		motors.Backward();
		while (!suppressed)
			Thread.yield();
		motors.Stop();
	}

}
