import javax.swing.JFrame;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Frame instance;
	public static Frame getInstance() {
		return instance;
	}
	Panel pan;
	
	public Frame(String name) {
		instance = this;
		this.setTitle(name);
		//this.setPreferredSize(new Dimension(1280,720));
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new PanelOption(2));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}

}
