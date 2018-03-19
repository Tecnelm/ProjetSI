import javax.swing. * ;

public abstract class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	// cette classe permet de recuperer les fonctions dans toute les classe
	public abstract int getID();
	public abstract void addline(String[] str);
	

}