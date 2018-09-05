package Navigointi;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.navigation.*;

public class NavMain {
	
	// TODO: Vaihda ketjut renkaisiin. Liikkuu tasaisemmin ja helpompi mitata.
	
	public static void main(String[] args) {
		RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.C);
		Wheel wheel1 = WheeledChassis.modelWheel(right, 3.12).offset(-8.9);
		// offset on renkaan keskipisteen etäisyys robotin keskipisteestä,
		// vasen miinusmerkkisenä
		Wheel wheel2 = WheeledChassis.modelWheel(left, 3.12).offset(8.9);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot pilotti = new MovePilot(chassis);
		// metri eteenpäin
		//pilotti.travel(100);
		// käänny kolme kierrosta oikealle
		pilotti.rotate(-1080);
		// kaarta pitkin: 50 cm säde, 90 asteen keskuskulma
		//pilotti.arc(50, 90);
	}
}