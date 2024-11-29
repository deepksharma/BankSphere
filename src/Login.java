
import java.awt.event.*;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{
	
	JButton login , signup , clear;
	JTextField cardtextfield;
	JPasswordField pintextfield;
	
	Login(){
		
		setTitle("ATM(Deepak National Bank)");
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bankpic.jpg"));
		Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel label = new JLabel(i3);
		label.setBounds(70,10, 100 , 100);
		add(label);
		
		JLabel text = new JLabel("Welcome to ATM");
		text.setFont(new Font("Osward",Font.BOLD,38));
		text.setBounds(240, 40 , 400 , 40);
		add(text);
		
		JLabel cardno = new JLabel("Card No : ");
		cardno.setFont(new Font("Osward",Font.BOLD,28));
		cardno.setBounds(120,150 , 150 , 30);
		add(cardno);
		
		cardtextfield = new JTextField();
		cardtextfield.setBounds(300 ,150, 230, 30);
		cardtextfield.setFont(new Font("Arial" ,Font.BOLD , 14));
		add(cardtextfield);
		
		JLabel pin = new JLabel("PIN : ");
		pin.setFont(new Font("Osward",Font.BOLD,28));
		pin.setBounds(120, 220 , 250 , 30);
		add(pin);
		
		pintextfield = new JPasswordField();
		pintextfield.setBounds(300 ,220, 230, 30);
		pintextfield.setFont(new Font("Arial" ,Font.BOLD , 14));
		add(pintextfield);
		
		login = new JButton("SIGN IN");
		login.setBounds(300 , 300 , 100, 30);
		login.setBackground(Color.BLACK);
		login.setForeground(Color.WHITE);
		login.addActionListener(this);
		add(login);
		
		clear = new JButton("CLEAR");
		clear.setBounds(430 , 300 , 100, 30);
		clear.setBackground(Color.BLACK);
		clear.setForeground(Color.WHITE);
		clear.addActionListener(this);
		add(clear);
		
		signup = new JButton("SIGN UP");
		signup.setBounds(300 , 350 , 230, 30);
		signup.setBackground(Color.BLACK);
		signup.setForeground(Color.WHITE);
		signup.addActionListener(this);
		add(signup);
		
		getContentPane().setBackground(Color.WHITE);
		
		setSize(800 , 480);
		setVisible(true);
		setLocation(350, 200);
		
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == clear) {
			cardtextfield.setText("");
			pintextfield.setText("");
		}else if(ae.getSource() == login) {
			connec conn = new connec();
			String cardnumber = cardtextfield.getText();
			String pinnumber = pintextfield.getText();
			String query = "select * from login where cardnumber = '"+cardnumber+ "' and pin = '"+pinnumber+"'   ";
			try {
				 ResultSet rs = conn.s.executeQuery(query);
				 if(rs.next()) {
					 setVisible(false);
					 new Transactions(pinnumber).setVisible(true);
				 }else {
					 JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
				 }
				
			}catch(Exception e) {
				System.out.println(e);
			}
			
		}else if(ae.getSource() == signup) {
			setVisible(false);
			new SignupOne().setVisible(true);
		}
		
	}

	public static void main(String[] args) {
		new Login();

	}

}
