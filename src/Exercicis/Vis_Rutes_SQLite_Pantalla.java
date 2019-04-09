package Exercicis;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;




public class Vis_Rutes_SQLite_Pantalla extends JFrame implements ActionListener{
    
    JComboBox combo;
    JButton eixir = new JButton("Eixir");
    JTextArea area = new JTextArea();


   
    
    public void iniciar() throws SQLException, ClassNotFoundException{
        // sentències per a fer la connexió
    	 Connection con = null;
    	 Statement st = null;
    	 String sentSQL = null;
    	 
		 Class.forName("org.sqlite.JDBC");
         String url = "jdbc:sqlite:Rutes.sqlite";
     
         con = DriverManager.getConnection(url);
         st = con.createStatement();
        
	
        this.setBounds(100, 100, 450, 300);
        this.setLayout(new BorderLayout());
        
        JPanel panell1 = new JPanel(new FlowLayout());
        JPanel panell2 = new JPanel(new BorderLayout());
        this.add(panell1,BorderLayout.NORTH);
        this.add(panell2,BorderLayout.CENTER);
        
        ArrayList<String> llista_rutes = new ArrayList<String>();
        // sentències per a omplir l'ArrayList amb el nom de les rutes
        
        sentSQL = "SELECT nom_r FROM RUTES";
        ResultSet rs = st.executeQuery(sentSQL);
        while (rs.next()){
        	llista_rutes.add(rs.getString(1));
        }
        
        st.close();
        
        combo = new JComboBox(llista_rutes.toArray());
        
        panell1.add(combo);
        panell1.add(eixir);
        
        panell2.add(new JLabel("LLista de punts de la ruta:"),BorderLayout.NORTH);
        panell2.add(area,BorderLayout.CENTER);
        
        this.setVisible(true);
        combo.addActionListener(this);
        eixir.addActionListener(this);
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == combo){
            //accions quan s'ha seleccionat un element del combobox, i que han de consistir en omplir el JTextArea
        	area.setText("");
        	Connection con2 = null;
       	    Statement st2 = null;
        	String sentSQL2 = null;
            String url2 = "jdbc:sqlite:Rutes.sqlite";
            
            try {
				con2 = DriverManager.getConnection(url2);
				st2 = con2.createStatement();
				sentSQL2 = "SELECT p.* FROM PUNTS p JOIN RUTES r ON r.num_r = p.num_r WHERE r.nom_r = '" + combo.getSelectedItem().toString() + "'";
				//System.out.println(sentSQL2);
				ResultSet rs2 = st2.executeQuery(sentSQL2);
				
				while(rs2.next()) {
					area.append(rs2.getString(3) + "(");
					area.append(rs2.getString(4) + ",");
					area.append(rs2.getString(5) + ")\n");
				}
				
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
         
           
      
        
        	sentSQL2 = "SELECT * FROM PUNTS";
        	
        	
        	
        	
            
        }

        if (e.getSource() == eixir){
            //accions quan s'ha apretat el botó d'eixir
            
        }
    }
}