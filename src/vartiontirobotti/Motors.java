package vartiontirobotti;

import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Motors {
	private RegulatedMotor mL; //= new EV3LargeRegulatedMotor(MotorPort.C);
	private RegulatedMotor mR; //= new EV3LargeRegulatedMotor(MotorPort.B);
	//private int speed = 1000;
	final int DEFAULTSPEED = 1000;
	
	public Motors(RegulatedMotor mL, RegulatedMotor mR) {
		this.mL = mL;
		this.mR = mR;
		mL.setSpeed(DEFAULTSPEED);
		mR.setSpeed(DEFAULTSPEED);
		mL.synchronizeWith(new RegulatedMotor[] { mR });
	}
	/*
	public void Drive() {
		mL.setSpeed(speed);
		mR.setSpeed(speed);
		
		mL.startSynchronization();
		mL.forward();
		mR.backward();
		mL.endSynchronization();
		Delay.msDelay(1000);
		mL.startSynchronization();
		mL.backward();
		mR.forward();
		mL.endSynchronization();
		Delay.msDelay(1000);
		
		
		mR.stop(true);
		mL.stop(true);
	}*/
	
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
	
	public void StopAndRotate(boolean left, int degrees) {
		mL.startSynchronization();
		if (left) {
			mR.rotate(degrees);
			mL.rotate(-degrees);
		} else {
			mL.rotate(degrees);
			mR.rotate(-degrees);
		}
		mL.endSynchronization();
	}
	
	public void TurnWhileMoving(boolean left, int howMuch, int howLong) {
		mL.startSynchronization();
		if (left) {
			mR.setSpeed(DEFAULTSPEED + howMuch);
		} else {
			mL.setSpeed(DEFAULTSPEED + howMuch);
		}
		mL.forward();
		mR.forward();
		mL.endSynchronization();
		Delay.msDelay(howLong);
	}
}
