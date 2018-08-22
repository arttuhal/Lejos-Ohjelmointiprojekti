package vartiontirobotti;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) {
		RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.C);
		RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.B);
		
		Motors motors = new Motors(mL, mR);
		
		motors.DriveStraigth(1450, true);
		motors.StopAndRotate(false, 100);
		//motors.DriveStraigth(700, true);
		//motors.StopAndRotate(true, 200);
		//motors.DriveStraigth(1500, true);
		//motors.TurnWhileMoving(left, howMuch, howLong);
		mL.startSynchronization();
		mR.stop(true);
		mL.stop(true);
		mL.endSynchronization();
	}

}
