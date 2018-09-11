package Navigointi;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.PoseProvider;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class NavMain {

	public static void main(String[] args) {
		RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.C);
		Wheel wheel1 = WheeledChassis.modelWheel(right, 3.12).offset(-8.9);
		// offset on renkaan keskipisteen etäisyys robotin keskipisteestä,
		// vasen miinusmerkkisenä
		Wheel wheel2 = WheeledChassis.modelWheel(left, 3.12).offset(8.9);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		PoseProvider poser = chassis.getPoseProvider();
		MovePilot pilotti = new MovePilot(chassis);
		Navigator nav = new Navigator(pilotti, poser);

		
		Tracking tracking = new Tracking(poser);
		tracking.start();
		Kartta kartta = new Kartta();
		
		try {
			Behavior tyoskentele = new Tyoskentele();
			Behavior captain = new Captain(nav, kartta, tracking);
			Behavior[] bArray = { captain, tyoskentele };
			Arbitrator arby = new Arbitrator(bArray);
			arby.go();
		} catch (DestinationUnreachableException e) {
			e.printStackTrace();
		}

	}
}