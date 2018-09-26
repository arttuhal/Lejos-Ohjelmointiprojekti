package KommunikoivaRobotti;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import KommunikoivaRobotti.Kartta;
import KommunikoivaRobotti.Tracking;
import KommunikoivaRobotti.Captain;
import KommunikoivaRobotti.Tyoskentele;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {

	public static void main(String[] args) {
		RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.B);
		RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.C);
		Wheel wheel1 = WheeledChassis.modelWheel(right, 3.12).offset(-8.75);
		// offset on renkaan keskipisteen etäisyys robotin keskipisteestä,
		// vasen miinusmerkkisenä
		Wheel wheel2 = WheeledChassis.modelWheel(left, 3.12).offset(8.75);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		PoseProvider poser = chassis.getPoseProvider();
		MovePilot pilotti = new MovePilot(chassis);
		Navigator nav = new Navigator(pilotti, poser);
		Tracking tracking = new Tracking(poser);
		tracking.start();
		Kartta kartta = new Kartta();

		
		try {
			ServerSocket serv = new ServerSocket(1111);
			Socket s = serv.accept();
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			LukijaThread lukija = new LukijaThread(in, out, kartta);
			lukija.start();
			in.close();
			try {
				Behavior tyoskentele = new Tyoskentele();
				Behavior captain = new Captain(nav, kartta, tracking);
				Behavior[] bArray = { captain, tyoskentele };
				Arbitrator arby = new Arbitrator(bArray);
				arby.go();
			} catch (DestinationUnreachableException e) {
				e.printStackTrace();
			}
			s.close();
			serv.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

}
