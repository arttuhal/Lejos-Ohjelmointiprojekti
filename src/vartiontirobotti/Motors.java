package vartiontirobotti;

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Motors {
	private RegulatedMotor mL;
	private RegulatedMotor mR;
	final int DEFAULTSPEED = 1000;

	public Motors(RegulatedMotor mL, RegulatedMotor mR) {
		this.mL = mL;
		this.mR = mR;
		mL.setSpeed(DEFAULTSPEED);
		mR.setSpeed(DEFAULTSPEED);
		mL.synchronizeWith(new RegulatedMotor[] { mR });
	}

	public void DriveStraigth(int howLong, boolean forward) {
		mL.startSynchronization();
		if (forward) {
			mL.forward();
			mR.forward();
		} else {
			mL.backward();
			mR.backward();
		}
		mL.endSynchronization();
		Delay.msDelay(howLong);
	}

	public void StopAndRotate(boolean left, int howLong) {
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
	}

	public void TurnWhileMoving(boolean left, int howMuch, int howLong) {
		if (left) {
			mL.setSpeed(DEFAULTSPEED - howMuch);
		} else {
			mR.setSpeed(DEFAULTSPEED - howMuch);
		}
		mL.startSynchronization();
		mL.forward();
		mR.forward();
		mL.endSynchronization();
		Delay.msDelay(howLong);
		mL.setSpeed(DEFAULTSPEED);
		mR.setSpeed(DEFAULTSPEED);
	}
}
