package vartiontirobotti;

import lejos.hardware.motor.*;
import lejos.robotics.RegulatedMotor;

public class Motors {
	private RegulatedMotor mC = Motor.C;
	private RegulatedMotor mB = Motor.B;
	private int speed = 1000;
	
	public void Drive(RegulatedMotor mC, RegulatedMotor mB) {
		this.mC = mC;
		this.mB = mB;
		this.mC.setSpeed(speed);
		this.mB.setSpeed(speed);
		mC.synchronizeWith(new RegulatedMotor[] { mB });
		
	}
}
