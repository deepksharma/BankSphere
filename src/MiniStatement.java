import java.awt.Color;
import java.sql.*;

import javax.swing.*;


public class MiniStatement extends JFrame {
	
	MiniStatement(String pinnumber){
		setTitle("Mini Statement");
		
		setLayout(null);
		
		JLabel mini = new JLabel();
		add(mini);
		
		JLabel bank = new JLabel();
		bank.setBounds(150, 20, 100, 20);
		add(bank);
		
		JLabel card = new JLabel();
		card.setBounds(20, 80, 300, 20);
		add(card);
		
		JLabel balance = new JLabel();
		balance.setBounds(20, 400, 300, 20);
		add(balance);
		
		try {
			connec conn = new connec();
			ResultSet rs = conn.s.executeQuery("Select * from login where pin = '"+pinnumber+"'");
			while(rs.next()) {
				card.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX".substring(12));
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		try {
			connec conn = new connec();
			int bal =0;
			ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"'  ");
			while(rs.next()) {
				mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
				
				if(rs.getString("type").equals("Deposit")) {
					bal += Integer.parseInt(rs.getString("amount"));
				}else {
					bal -= Integer.parseInt(rs.getString("amount"));
				}
			}
			balance.setText("Current Account Balance: Rs " + bal);
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		mini.setBounds(20, 140 ,400, 200);
		
		setSize(400, 600);
		setLocation(20, 20);
		getContentPane().setBackground(Color.WHITE);
	}
	

	public static void main(String[] args) {
		new MiniStatement("");

	}

}
