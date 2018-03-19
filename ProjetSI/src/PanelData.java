import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelData extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6895598465320634344L;
	private Object [][]data;
	private Object [] title; /*= {
			"ID","Nombre mLitre","Average flow ml/s", "Average Flow l/min" ,"Time" 
	};*/
	private JTable value;
	private int UUID;
	private DefaultTableModel model;
	public PanelData(int UUID ,String titData)
	{
		this.UUID=UUID;
		this.setPreferredSize(new Dimension(1280,720));
		this.title = titData.split(";");
		
		this.setLayout(new BorderLayout());
		JButton graph = new JButton("Graph");
		this.add(graph,BorderLayout.NORTH );
		graph.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.getInstance().setContentPane(PanelManager.getInstance().getJPanel(0));
				Frame.getInstance().pack();
				
			}
		});
		
		model = new DefaultTableModel(data,title);
		value = new JTable(model);
		

		this.add(new JScrollPane(value),BorderLayout.CENTER);
		value.setAutoResizeMode(5);	
		value.setEnabled(false);
		
		
	}
	
	@Override
	public int getID() {
		
		return UUID;
	}

	@Override
	public void addline(String[] str) {
		model.insertRow(model.getRowCount(), str);
		model.fireTableDataChanged();
	 
	}

	

}
