import java.util.ArrayList;

// cette classe va gerer le pannel a afficher 
public class PanelManager {
	private ArrayList < Panel > jPanels = new ArrayList < >();
	private static PanelManager instance = new PanelManager();
	// permet d'eviter de  regénerer le pannel manageur a chaque fois
	public static PanelManager getInstance() {
		return instance;
	}
	public void setup( int nbcourbe ,String titCourbe ,int nbData, String titData, String info ,String nomFichier,String format) {
		jPanels.add(new PanelGraph(0 ,nbcourbe,titCourbe,nbData,titData,info,nomFichier , format));
		jPanels.add(new PanelData(1,titData));
		instance = this;
	}

	public ArrayList < Panel > getJPanels() {
		return jPanels;
	}
	// parcoure la liste pour touver le bon pannel a afficher
	public Panel getJPanel(int id) {
		for (Panel panel: jPanels) {
			if (panel.getID() == id) return panel;
		}
		return null;
	}
}