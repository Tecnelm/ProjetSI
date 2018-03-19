import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.fazecast.jSerialComm.SerialPort;


public class PanelGraph extends Panel
{
	
	private static final long serialVersionUID = 1L;
	private int UUID;
	private SerialPort aPort;
	private int x = 0;
	private int n=0;
	private Thread getData;
	public Panel pan = this;
	private String titCourbe[];
	
	private String infoGraph[];
	
	private JOptionPane jop3 = new JOptionPane();
	
	ArrayList<XYSeries> seriesL = new ArrayList<>();
	XYSeries series;
	private BufferedWriter folder = null;
	
	
	public PanelGraph(int UUID, int nbCourbe ,String titCourbe ,int nbData, String titData,String info, String nomFichier , String format)
	{
		this.UUID= UUID;
		this.titCourbe = titCourbe.split(";");
		this.infoGraph = info.split(";");
		
		JPanel buttonPanel = new JPanel();
		this.setPreferredSize(new Dimension(1280,720));
		this.setLayout(new BorderLayout());
		JComboBox<String>com = new JComboBox<>();
		SerialPort [] serialcom = SerialPort.getCommPorts();
		for(SerialPort port : serialcom)
			com.addItem(port.getSystemPortName());
		
		XYSeriesCollection sCollection = new XYSeriesCollection();
		sCollection.setAutoWidth(true);
		
		for(int i = 0 ; i < nbCourbe ; i++) {
			series = new XYSeries((String)this.titCourbe[i]);
			seriesL.add(series);
			sCollection.addSeries(series);
			}
		

		JFreeChart chart =ChartFactory.createXYLineChart(this.infoGraph[0],this.infoGraph[1],this.infoGraph[2], sCollection);
		
		
		ChartPanel chartP = new ChartPanel(chart);
		this.add(chartP, BorderLayout.CENTER);
		JButton save  = new JButton("Save");
		JButton data = new JButton ("Data");
		JButton connect = new JButton("Connect");	
		buttonPanel.add(com);
		buttonPanel.add(connect);
		buttonPanel.add(save);
		buttonPanel.add(data);
		
		JLabel l = new JLabel();
		l.setIcon(new ImageIcon(new ImageIcon(".\\Image\\image.gif").getImage().getScaledInstance(100, 70, Image.SCALE_DEFAULT)));
		buttonPanel.add(l);
		
		JLabel l1 = new JLabel("By GARRIGUES Clément");
		buttonPanel.add(l1, BorderLayout.EAST);
		
		this.add(buttonPanel , BorderLayout.NORTH);
		data.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Frame.getInstance().setContentPane(PanelManager.getInstance().getJPanel(1));
				Frame.getInstance().pack();		
			}
		});
		data.setEnabled(true);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedImage bI = new BufferedImage(pan.getWidth(),pan.getHeight(),BufferedImage.TYPE_BYTE_INDEXED); 
				chartP.paint(bI.getGraphics()); 
				
				try { 
					File f = new File("imagefilename"+n+".jpg"); 
					n++;
					FileOutputStream fichier = new FileOutputStream(f); 
					ImageIO.write(bI, "jpg", fichier); 
					fichier.close(); 
				} catch (Exception a) {jop3.showConfirmDialog(null, "Impossible d'enregistrer l'image", "Erreur d'enregistrement", JOptionPane.YES_NO_OPTION);} 
				
			}
		});
		
		connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(connect.getText().equals("Connect"))
				{
					int option = JOptionPane.CANCEL_OPTION;
					try {
						aPort = SerialPort.getCommPort(com.getSelectedItem().toString());
						aPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0,0);
					do {
						
						if(aPort.openPort()){
							
							connect.setText("disconect");
							com.setEnabled(false);
							for(XYSeries m: seriesL)
								m.clear();
							File file = new File(nomFichier+format);
							try {
								String tete = "";
								BufferedWriter folder =  new BufferedWriter(new FileWriter(file,true)) ;
								folder.append(titData.replace(" ", "-"));
								folder.newLine();
								folder.close();	
								
								pan.updateUI();
								
								
							}catch(Exception p) {}
							 getData = new Thread() {
								public void run()
								{
									Scanner data = new Scanner(aPort.getInputStream());
									
									while(data.hasNextLine())
									{
										try {
											String line1 =data.nextLine();	
											String line[]=line1.split(";");
											String lineF = data.nextLine();
											
											for (String s : line)
												System.out.print(s+";");
											
											System.out.println();
											System.out.println(lineF);
											System.out.println("------------------------------");
											PanelManager.getInstance().getJPanel(1).addline(lineF.split(";"));
											
											
											for(int i = 0 ; i < nbCourbe; i++) {
												
												seriesL.get(i).add(x ,Integer.parseInt(line[i]));
											}
											x++;

											
											File file = new File(nomFichier+format);
											try {
												
											    folder =  new BufferedWriter(new FileWriter(file,true)) ;
												
												folder.append(lineF.replace(".", ","));
												folder.newLine();
												folder.close();	
												
												
											}catch(Exception e) {}
											
											
										
										}catch(Exception e) {};
																
									}
									data.close();	
									
								}						
							};
							getData.start();
						 }	
						else { 
							 option = jop3.showConfirmDialog(null, "réessayer de se connecter au port?", "Erreur d'ouverture port", JOptionPane.YES_NO_OPTION); 
						}
						}while(option == JOptionPane.OK_OPTION);
					
					}catch(Exception m) {
						jop3.showMessageDialog(null, "Il n'y a pas de port", "Erreur", JOptionPane.ERROR_MESSAGE);
						SerialPort [] serialcom = SerialPort.getCommPorts();
						for(SerialPort port : serialcom)
							com.addItem(port.getSystemPortName());};
					}
				
					
				else
				{
					
					if(aPort.closePort())
					{
						com.setEnabled(true);
						connect.setText("Connect");
						try {
							folder.close();
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						x=0;
					}
				}		
			}
		});	
	}
	

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return UUID;
	}
	@Override
	public void addline(String[] str) {
		// TODO Auto-generated method stub
		
	}
}
