package vartiontirobotti;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) {
		final RegulatedMotor mC = Motor.C;
		final RegulatedMotor mB = Motor.B;
		
		Motors motors = new Motors();
		motors.Drive(mB, mC);
		Motor.B.forward();
		Motor.C.backward();
		Delay.msDelay(1000);
		Motor.C.forward();
		Motor.B.backward();
		Delay.msDelay(1000);
		Motor.B.stop();
		Motor.C.stop();
		
	}

}
