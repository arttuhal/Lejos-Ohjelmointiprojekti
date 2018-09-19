import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import lejos.hardware.lcd.LCD;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ServerSocket serv = new ServerSocket(1111);
			Socket s = serv.accept();
			DataInputStream in = new DataInputStream(s.getInputStream());
			LukijaThread lukija = new LukijaThread(in);
			//DataOutputStream out = new DataOutputStream(s.getOutputStream());
			lukija.start();
			
			in.close();
			s.close();
			serv.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
