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
		motors.StopAndRotate(false, 440);
		motors.DriveStraigth(950, true);
		motors.StopAndRotate(true, 800);
		motors.DriveStraigth(1350, true);
		motors.TurnWhileMoving(false, 550, 4150);
		motors.StopAndRotate(false, 730);
		motors.DriveStraigth(1200, true);
		motors.StopAndRotate(true, 725);
		motors.DriveStraigth(1200, true);
		// Turn back and return
		motors.StopAndRotate(true, 1250);
		motors.DriveStraigth(1100, true);
		motors.StopAndRotate(false, 725);
		motors.DriveStraigth(1100, true);
		motors.StopAndRotate(true, 900);
		motors.TurnWhileMoving(true, 550, 4150);
		motors.DriveStraigth(900, true);
		motors.StopAndRotate(false, 800);
		motors.DriveStraigth(950, true);
		motors.StopAndRotate(true, 440);
		motors.DriveStraigth(1450, true);
		motors.StopAndRotate(true, 1250);

		mL.startSynchronization();
		mR.stop(false);
		mL.stop(false);
		mL.endSynchronization();

		Delay.msDelay(100);
	}

}
