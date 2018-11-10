package pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * By Dr. Samer Zain, Birzeit University
 */
public class DatabaseAPI {

	public static Connection connection=null;
        private static DatabaseAPI handler = null;

        
        
	public DatabaseAPI() {
            try {
                // Load the JDBC driver
                createConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

        public static DatabaseAPI getInstance()
        {
            if(handler == null)
                handler = new DatabaseAPI();
            return handler;
        }
        
	public static Connection createConnection() throws SQLException {

            try 
            {
               
               // Class.forName("com.mysql.jdbc.Driver");
                 
                connection = DriverManager.getConnection("jdbc:mysql://localhost/pharmacy1", "root", "");
               
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
		return connection;
	}

	public ResultSet read(String sql) throws SQLException {
		//getConnection();
		Statement statement = connection.createStatement();

		// Execute a statement
		ResultSet resultSet = statement.executeQuery(sql);
		
		return resultSet;
	}
	
	public int write(String sql) throws SQLException{
//		getConnection();
		Statement statement = connection.createStatement();
		
		int x = statement.executeUpdate(sql);
		connection.close();
		connection = null;
		return x;
	}

	
/*
    static Connection con = null;

    public static Connection get_connection() {
        try {
            if(con != null && !con.isClosed())
                return con;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/driving_school", "root", "");
                if (!con.isClosed()) {
                    System.out.println("Successfully connected to MySQL server...");
                    return con;
                }
            } catch (Exception e) {
                System.err.println("Exception: " + e.getMessage());
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
*/
}
