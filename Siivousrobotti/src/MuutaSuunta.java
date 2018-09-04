import java.util.Random;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class MuutaSuunta implements Behavior {

	private volatile boolean suppressed = false;
	private volatile boolean actionRunning = false;
	private Motors motors;
	private InfraredSensor IR;
	private ColorSensor colors;
	private Random random;

	public MuutaSuunta(Motors motors, InfraredSensor IR, ColorSensor colors) {
		this.motors = motors;
		this.colors = colors;
		this.IR = IR;
		random = new Random();
	}

	public boolean takeControl() {
		if (actionRunning) {
			return false;
		} else {
			float distance = IR.getDistance();
			if (Button.UP.isUp()) {
				if ((distance <= 10.0 && distance > 0.0) || colors.checkForRed()) {
					return true;
				}
				return false;
			}
			return false;
		}
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		if (!actionRunning) {
			actionRunning = true;
			suppressed = false;
			motors.Forward();
			Delay.msDelay(800); // Backwards delay
			motors.StopAndRotate(random.nextBoolean(), (int) random.nextInt(1000) + 300, this);
			while (!suppressed)
				Thread.yield();
			motors.Stop();
			actionRunning = false;
		}
	}
}
