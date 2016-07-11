import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Properties;

public class Main {
 public static void main(String[] args){
	 JFrame frame=new JFrame("Database");
	 Properties props=new Properties();
	 ConnectDialog dialog=new ConnectDialog(frame,"Database Connector",props);
	dialog.setVisible(true);
	 if(dialog.isCancelled)
	 {System.exit(0);}
	 Connector con=new Connector(dialog.getProps(),new String(dialog.pass.getPassword()));
	 if(!con.open())
		 System.exit(0);
	 Database dPanel=new Database(con);
	 frame.setSize(800, 600);
	 frame.add(dPanel);
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.setVisible(true);
     frame.setLayout(null);
 }
}
