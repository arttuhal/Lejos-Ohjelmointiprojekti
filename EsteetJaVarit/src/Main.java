import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) {
		InfraredSensor IR = new InfraredSensor();
		ColorSensor colors = new ColorSensor();
		RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.C);
		RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.B);
		Motors motors = new Motors(mL, mR);
		// Actions
		motors.Stop();
		colors.calibrateColors();
		motors.Forward();
		IR.testDistance();
		motors.Backward();
		Delay.msDelay(1000);
		

	}

}
