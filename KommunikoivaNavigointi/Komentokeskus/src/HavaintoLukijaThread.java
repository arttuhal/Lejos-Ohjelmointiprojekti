import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class HavaintoLukijaThread extends Thread {
	
	private DataInputStream in;
	private boolean lopeta;
	private List<Havainto> havainnot;
	private int index;
	
	public HavaintoLukijaThread(DataInputStream in) {
		this.in = in;
		havainnot = new ArrayList<>();
		//setDaemon(true);
	}
	
	public void run() {
		while (!lopeta) {
			try {
				String input = in.readUTF();
				if (input.contains("havainto")) {
					havainnot.add(new Havainto());
					havainnot.get(index).setNumero(index + 1);
					havainnot.get(index).setX(in.readFloat());
					havainnot.get(index).setY(in.readFloat());
					havainnot.get(index).setAika(in.readLong());
					havainnot.get(index).setArvo(in.readInt());
					index++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (havainnot.size() >= 3) {
				for (int i = 0; i < havainnot.size(); i++) {
					System.out.println(havainnot.get(i).toString());
				}
				lopeta();
			}
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void lopeta() {
		lopeta = true;
	}
}
