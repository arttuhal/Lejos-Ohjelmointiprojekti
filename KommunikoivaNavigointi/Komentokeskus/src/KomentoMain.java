import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

import lejos.robotics.geometry.Rectangle;

public class KomentoMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Kartta kartta = new Kartta();
		
		try {
			Socket s = new Socket("10.0.1.1", 1111);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			kartta.sendKartta(out);
			
			HavaintoLukijaThread havaintoLukija = new HavaintoLukijaThread(in);
			havaintoLukija.start();
			try {
				havaintoLukija.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//out.flush();
			//out.close();
			//in.close();
			//s.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
