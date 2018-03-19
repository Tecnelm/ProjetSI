import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PanelOption extends Panel {
	
	

	private static final long serialVersionUID = 1L;
	private int ID ;
	public PanelOption(int ID) {
		
		this.ID = ID;
		this.setLayout(null);
		
		this.setPreferredSize(new Dimension(550 , 500));
		
		
		
		JOptionPane jop3 = new JOptionPane();
		
		JTextFieldLimit nbCourbe = new JTextFieldLimit(2);
		JTextFieldLimit nbData = new JTextFieldLimit(2);
		JTextField titreCourbe = new JTextField();
		JTextField titreData = new JTextField();
		JTextField titrefichier = new JTextField();
		
		JTextField titreGraph = new JTextField();
		JTextField titreAxeX = new JTextField();
		JTextField titreAxeY = new JTextField();
		
		
		JLabel lnbCourbe = new JLabel("nombre de courbe:");
		JLabel ltitreCourbe = new JLabel("nom des courbes séparé d'un \"  ;  \" :");
		JLabel lnbData = new JLabel("nombre d'information:");
		JLabel ltitreData = new JLabel("nom des informationsséparé d'un \"  ;  \":");
		
		JLabel ltitreGraph = new JLabel("Nom du graphique:");
		JLabel ltitreAxeX = new JLabel("Nom axe X:");
		JLabel ltitreAxeY = new JLabel("Nom axe Y:");
		JLabel ltitrefichier = new JLabel("Titre du fichier:");
		
		JComboBox<String>format = new JComboBox<>();
		
		format.addItem(".csv");
		format.addItem(".txt");
		
		nbCourbe.setBounds(10,30,30,25);
		titreCourbe.setBounds(70,30,470,25);
		
		nbData.setBounds(10,100,25,25);
		titreData.setBounds(70,100,470,25);
		
		titreGraph.setBounds(50,175,450,25);
		titreAxeX.setBounds(50,250,450,25);
		titreAxeY.setBounds(50,325,450,25);
		titrefichier.setBounds(50, 400, 400, 25);
		format.setBounds(450, 400, 50, 25);
		
		nbCourbe.setText("2");
		titreCourbe.setText("nb mLitre;average flow");
		nbData.setText("5");
		titreData.setText("UID;Nombre mLitre;Average flow ml/s;Average Flow l/min;Time" );
		titreGraph.setText("flowmeter");
		titreAxeX.setText( "Time (seconds)");
		titreAxeY.setText("Volume/ml - ml/s");
		titrefichier.setText("Value");
		
		
		Font font = new Font("Arial",Font.BOLD,11);
		
		lnbCourbe.setBounds(0,0,120,25);
		ltitreCourbe.setBounds(150,0,250,25);
		lnbData.setBounds(0,70,140,25);
		ltitreData.setBounds(150,70,250,25);
		
		ltitreGraph.setBounds(50,150,450,25);
		ltitreAxeX.setBounds(50,225,450,25);
		ltitreAxeY.setBounds(50,300,450,25);
		
		ltitrefichier.setBounds(50,375,450,25);
		
		lnbCourbe.setFont(font);
		ltitreCourbe.setFont(font);
		lnbData.setFont(font);
		ltitreData.setFont(font);
		ltitreGraph.setFont(font);
		ltitreAxeX.setFont(font);
		ltitreAxeY.setFont(font);
		ltitrefichier.setFont(font);
		
		
		JButton b = new JButton("Valider");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if((nbCourbe.getText().length()== 0||titreCourbe.getText().length()== 0||nbData.getText().length()== 0||titreData.getText().length()== 0||titreGraph.getText().length()==0||titreAxeX.getText().length()==0||titreAxeY.getText().length()==0||titrefichier.getText().length()==0))
					jop3.showMessageDialog(null, "L'un des champs est vide", "Erreur", JOptionPane.ERROR_MESSAGE);
				else {
					String infoGraph = titreGraph.getText() + ";"+titreAxeX.getText() +";"+titreAxeY.getText();
					int nbcourbe = Math.min(Integer.parseInt(nbCourbe.getText()), titreCourbe.getText().split(";").length);
					PanelManager.getInstance().setup(nbcourbe,titreCourbe.getText(),Integer.parseInt(nbData.getText()),titreData.getText(),infoGraph, titrefichier.getText(),format.getSelectedItem().toString() );
					Frame.getInstance().setResizable(true);
					Frame.getInstance().setContentPane(PanelManager.getInstance().getJPanel(0));
					Frame.getInstance().pack();
					Frame.getInstance().setLocationRelativeTo(null);}
			}
		});
		
		b.setBounds(210, 437, 100, 50);
		this.add(nbCourbe);
		this.add(titreCourbe);
		this.add(nbData);
		this.add(titreData);
		this.add(titreGraph);
		this.add(titreAxeX);
		this.add(titreAxeY);
		this.add(titrefichier);
		
		this.add(lnbCourbe);
		this.add(ltitreCourbe);
		this.add(lnbData);
		this.add(ltitreData);
		this.add(b);
		this.add(ltitreGraph);
		this.add(ltitreAxeX);
		this.add(ltitreAxeY);
		this.add(ltitrefichier);
		this.add(format);
		this.updateUI();
			
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public void addline(String[] str) {
		// TODO Auto-generated method stub
		
	}

}
