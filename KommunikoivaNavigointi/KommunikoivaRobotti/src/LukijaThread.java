import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LukijaThread extends Thread {

	private DataInputStream in;
	private ObjectInputStream objIn;
	private boolean lopeta = false;

	public LukijaThread(DataInputStream in) {
		this.in = in;
		try {
			objIn = new ObjectInputStream(this.in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//setDaemon(true);
	}

	public void run() {
		while (!lopeta) {
			try {
				System.out.println(objIn.readObject().getClass().getName());
				objIn.close();
				Thread.sleep(5000);
				lopeta();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void lopeta() {
		lopeta = true;
	}
}
