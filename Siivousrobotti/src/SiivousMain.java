import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.*;
import lejos.utility.Delay;

public class SiivousMain {

	public static void main(String[] args) {
		
		try {
			EV3IRSensor IRSensor = new EV3IRSensor(SensorPort.S3);
			ColorSensor colors = new ColorSensor();
			RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.C);
			RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.B);
			Motors motors = new Motors(mL, mR);
			colors.calibrateColors();
			
			KaukoOhjain kaukoOhjain = new KaukoOhjain(IRSensor);
			kaukoOhjain.setDaemon(true);
			kaukoOhjain.start();
			
			Behavior b1 = new EteenpainAjo(motors);
			Behavior b2 = new MuutaSuunta(motors, kaukoOhjain, colors);
			Behavior b3 = new Keskeyta(motors, kaukoOhjain);
			Behavior[] bArray = { b1, b2, b3 };
			
			Arbitrator arby = new Arbitrator(bArray);

			HataSeis hataseis = new HataSeis();
			hataseis.start();
			
			arby.go();
			arby.stop();
			motors.Stop();
		} catch (IllegalArgumentException e) {
			LCD.drawString("Sensors are", 0, 0);
			LCD.drawString("missing or", 0, 1);
			LCD.drawString("they are on", 0, 2);
			LCD.drawString("the wrong ports", 0, 3);
			LCD.drawString("Press any button", 0, 5);
			LCD.drawString("to exit", 0, 6);
			Button.waitForAnyPress();
		}
		
		

	}

}
