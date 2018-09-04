

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Motors {
	private RegulatedMotor mL;
	private RegulatedMotor mR;
	final int DEFAULTSPEED = 600;

	public Motors(RegulatedMotor mL, RegulatedMotor mR) {
		this.mL = mL;
		this.mR = mR;
		mL.setSpeed(DEFAULTSPEED);
		mR.setSpeed(DEFAULTSPEED);
		mL.synchronizeWith(new RegulatedMotor[] { mR });
	}

	
	public void Backward() {
		mL.startSynchronization();
		mL.backward();
		mR.backward();
		mL.endSynchronization();
	}

	public void Forward() {
		mL.startSynchronization();
		mL.forward();
		mR.forward();
		mL.endSynchronization();
	}

	public void Stop() {
		mL.startSynchronization();
		mL.stop();
		mR.stop();
		mL.endSynchronization();
	}
	
	public boolean StopAndRotate(boolean left, int howLong, Behavior behavior) {
		mL.startSynchronization();
		if (left) {
			mR.forward();
			mL.backward();
		} else {
			mL.forward();
			mR.backward();
		}
		mL.endSynchronization();
		Delay.msDelay(howLong);
		behavior.suppress();
		return true;
	}

}
