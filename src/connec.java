import java.sql.*;

public class connec {
	Connection c;
	Statement s;
	public connec() {
		try {
			
			c= DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "Deepak@12345");
			s = c.createStatement();
;		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
