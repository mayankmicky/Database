
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Database extends JPanel {
	static JTextArea sql=new JTextArea(); 
	JLabel prompt=new JLabel("please enter your SQL statement below: ");
	JButton exe=new JButton("Execute");
	JButton reset=new JButton("Reset");
	static JTable table=new JTable();
	static DefaultTableModel model=(DefaultTableModel)table.getModel();
    static Connector dc;
	public Database(Connector con){
		dc=con;
		add(prompt);
		JScrollPane spane=new JScrollPane(sql);
		spane.setPreferredSize(new Dimension(750,150));
		add(spane);
		exe.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				execute();
			}	
		});
		
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			model.setColumnCount(0);
			model.setRowCount(0);
			}	
		});
		add(exe);
		add(reset);
		JScrollPane rpane=new JScrollPane(table);
		rpane.setPreferredSize(new Dimension(750,350));
		add(rpane);
	}
	private static void execute(){
	model.setColumnCount(0);
	model.setRowCount(0);
		String s=sql.getText();
		try{
			if(s.length()>=6 &&  s.substring(0,6).equalsIgnoreCase("SELECT")){
			ResultSet rs=dc.executeQuery(s);
			ResultSetMetaData rsmd=rs.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++){
				model.addColumn(rsmd.getColumnName(i));
			}
			while(rs.next()){
				String[] data=new String[rsmd.getColumnCount()];
			for(int i=1;i<=rsmd.getColumnCount();i++){
				data[i-1]=rs.getString(i);
				}
				model.addRow(data);
				
			}}
			else
				dc.executeUpdate(s);
		
		}
		catch(SQLException e){
			System.out.println("error:Exception"+e);
		}
		
	}
	
}
