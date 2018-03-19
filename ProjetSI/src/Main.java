
public class Main {

	public static void main(String[] args) {
		
		Thread main = new Thread() {
			public void run () {
				new Frame( "ProjetSI");
			}
		};
		main.start();
		

	}

}
