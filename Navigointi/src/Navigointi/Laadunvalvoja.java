package Navigointi;

public class Laadunvalvoja {
	public static boolean tyoValmis = true;
	
	public static void jatkaMatkaa() {
		tyoValmis = true;
	}
	
	public static void aloitaTyo() {
		tyoValmis = false;
	}
}
