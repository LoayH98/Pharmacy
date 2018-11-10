package pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
/*
 * By Dr. Samer Zain, Birzeit University
 */
public class DatabaseAPI {

	public static Connection connection=null;
        private static DatabaseAPI handler = null;
        private static PreparedStatement insertStatement1=null;
        private static PreparedStatement insertStatement2=null;
        private static Statement statement=null ;
        
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
               
          //      Class.forName("com.mysql.jdbc.Driver");
                


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
        public static void insert1(String personID ,String pname ,String gender ,String address,String email,String phone) throws SQLException {
	    Statement statement = connection.createStatement();  
            String insertString1 = "insert into person (personid,pname,gender,address,email,phone) values (?, ?, ?, ?, ?, ?);";
                
		insertStatement1 = connection.prepareStatement(insertString1);
               

		int id = Integer.parseInt(personID);
                
                insertStatement1.setInt(1, id);
		insertStatement1.setString(2, pname);
		insertStatement1.setString(3, gender);
		insertStatement1.setString(4, address);
		insertStatement1.setString(5, email);
		insertStatement1.setString(6,phone );


		insertStatement1.executeUpdate();
	}
               public static void insert2(String pharmacistid ,String username ,String password ,String shift,String Salary) throws SQLException {
		Statement statement = connection.createStatement();
                String insertString2 = "insert into pharmacist (pharmacistid,username,password,shift,Salary) values (?, ?, ?, ?, ?);";
		
                insertStatement2 = connection.prepareStatement(insertString2);

		int id = Integer.parseInt(pharmacistid);
		insertStatement2.setInt(1,id);
		insertStatement2.setString(2,username );
		insertStatement2.setString(3,password);
		insertStatement2.setString(4,shift);
		insertStatement2.setString(5,Salary);



		insertStatement2.executeUpdate();
	}
               // ,String UserPH,String passPH,String SihftPH,String SalaryPH
               public static void update1(String pid , String nph, String gender,String address,String email,String phone
              ) throws SQLException {
		Statement statement = connection.createStatement();
		int i = 0;
		String s = "UPDATE person set ";
		String[] ss;
		String tmp;
		
		if(pid != null && !pid.equals("")) {
			s = s + "personid = " +"'"+ Integer.parseInt(pid)+"'";
			i++;
		}
		
		if(nph != null && !nph.equals("")){
			if(i != 0)
				s += " ,";
			s = s + "pname = " + "'"+nph+"'";
			i++;
		}
                if(gender != null && !gender.equals("")){
			if(i != 0)
				s += " ,";
			s = s + "gender = " +"'"+ gender+"'";
			i++;
		}
		
		if(address != null && !address.equals("")){
			if(i != 0)
				s += " ,";
			s = s + "address = " + "'"+address+"'";
			i++;
		}
		
		if(email != null && !email.equals("")){
			if(i != 0)
				s += " ,";
			s = s + "email = " +"'"+ email+"'";
			i++;
		}
		
		if(phone != null && phone.length() > 0){
			if(i != 0)
				s += ",";
			s = s + "phone = " + Integer.parseInt(phone);
			i++;
		}
		if(i == 0)
			return;
		
		s += " where personid = " + Integer.parseInt(pid);
                
		statement.execute(s);
	}
                             
               public static void update2(String id,String UserPH,String passPH,String SihftPH,String SalaryPH) throws SQLException {
		Statement statement = connection.createStatement();
		int i = 0;
		String s = "UPDATE pharmacist set ";
		String[] ss;
		String tmp;
                System.out.println("test0");
		
		if(UserPH != null && !UserPH.equals("")) {
			s = s + "username = " +"'"+ UserPH+"'";
			i++;
		}
                System.out.println("test1");
		
		if(passPH != null && !passPH.equals("")){
			if(i != 0)
				s += ",";
			s = s + "password = " +"'"+passPH+"'";
			i++;
		}
		                System.out.println("test2");
		if(SihftPH != null && !SihftPH.equals("")){
			if(i != 0)
				s += ",";
			s = s + "shift = " +"'" +SihftPH+"'";
			i++;
		}
		
		if(SalaryPH != null && !SalaryPH.equals("")){
			if(i != 0)
				s += ",";
			s = s + "Salary = " + Double.parseDouble(SalaryPH);
			i++;
		}
		
		
		if(i == 0)
			return;
		
		s += " where pharmacistid = " + Integer.parseInt(id);
		statement.execute(s);
	}
               
                public static void insertSale(String iid  ,String price,String quantity,String typeOfSale,String discount) throws SQLException {
	    Statement statement = connection.createStatement(); 
                                           LocalDateTime ldt = LocalDateTime.now().plusDays(0);
             DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

System.out.println("7");
              String formatter = formmat1.format(ldt);
            String insertString1 = "insert into sailes (iid,sdate,sprice,squanity,typeOfSale,discount) values (?, ?, ?, ?, ?, ?);";
                
		insertStatement1 = connection.prepareStatement(insertString1);
               System.out.println("8");

		//int id = Integer.parseInt("1");
                System.out.println("9");
                insertStatement1.setInt(1, Integer.parseInt(iid));
                System.out.println("10");
		insertStatement1.setString(2, formatter);
                System.out.println("11");
                insertStatement1.setDouble(3,Double.parseDouble(price) );
                System.out.println("12");
		insertStatement1.setInt(4, Integer.parseInt(quantity));
		System.out.println("13");
		insertStatement1.setString(5, typeOfSale);
                System.out.println("14");
		insertStatement1.setDouble(6,Double.parseDouble(discount) );
                System.out.println("15");
		insertStatement1.execute();
                System.out.println("16");
               Statement statement2 = connection.createStatement();
               Statement statement3 = connection.createStatement();
               statement2.executeUpdate("UPDATE inventory set quantity=quantity-"+"'"+Integer.parseInt(quantity)+"'" +"where iid="+" '"+Integer.parseInt(iid)+"'");
              
               statement3.executeUpdate("UPDATE inventory set totalSales=totalSales+"+"'"+(Double.parseDouble(price))+"'"+"where iid="+" '"+Integer.parseInt(iid)+"'");
               statement3.close();
                                 Statement stat = connection.createStatement();
            Connection conn = DatabaseAPI.createConnection();
          
            // Execute query and store result in a resultset
          ResultSet  res1 = stat.executeQuery("SELECT quantity"
                    + " FROM  inventory \n"
         +  "WHERE iid="+" '"+Integer.parseInt(iid)+"' ");
          
          if(res1.next())
          {
              if(Integer.parseInt(String.valueOf(res1.getString(1)))<5 )
              
                  ShowMessage("Quantity of Inventory of iid "+iid+"is lower than 5 ");
              
          }
      
	}

               private static void ShowMessage(String s) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(s);

		alert.showAndWait();   
                
	}
         ////////////////////////////////////////////////////////////////////////
          
               //    delete pharmacist  //
               
 

          

               public static void delete(String id) throws SQLException {
                   Statement statement = connection.createStatement();
		statement.execute("delete from person where personid = " +Integer.parseInt(id) );
               
	}


}
