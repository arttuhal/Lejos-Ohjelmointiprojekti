import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
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
		colors.calibrateColors();
		LCD.drawString("Place robot on start line", 0, 0);
		LCD.drawString("Press ENTER to start.", 0, 2);
		Button.ENTER.waitForPressAndRelease();
		motors.Backward();
		IR.testDistance();
		motors.Forward();
		while (!colors.checkForRed()) {
			LCD.drawString("Searching for color", 0, 0);
			Delay.msDelay(10);
		}
		motors.Stop();
		Delay.msDelay(100);
	}

}
