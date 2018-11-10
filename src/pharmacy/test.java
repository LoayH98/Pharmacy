package pharmacy;


/*
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class test {

	@FXML
	private TextField eidT;

	@FXML
	private TextField pidT;

	@FXML
	private TextField ratingT;

	@FXML
	private TextField addressT;

	@FXML
	private TextField baseSalaryT;

	@FXML
	private TextField loginNameT;

	@FXML
	private PasswordField loginPasswordP;

	@FXML
	private DatePicker startDateD;

	@FXML
	private DatePicker endDateD;

	@FXML
	private TextField cityidT;

	@FXML
	private DatePicker livesFromD;

	@FXML
	private DatePicker livesToD;

	@FXML
	void addEmployee(ActionEvent e) throws NumberFormatException, SQLException {
		try {
			if (eidT.getText().equals("") || pidT.getText().equals("") || ratingT.getText().equals("")
					|| addressT.getText().equals("") || baseSalaryT.getText().equals("")
					|| loginNameT.getText().equals("") || loginPasswordP.getText().equals("")
					|| startDateD.getValue() == null || endDateD.getValue() == null || cityidT.getText().equals("")
					|| livesFromD.getValue() == null || livesToD.getValue() == null) {
				throw new Exception();
			} else {
				DatabaseAPI.insert(eidT.getText(), pidT.getText(), ratingT.getText(), addressT.getText(),
						baseSalaryT.getText(), loginNameT.getText(), loginPasswordP.getText(), startDateD.getValue(),
						endDateD.getValue(), cityidT.getText(), livesFromD.getValue(), livesToD.getValue());

				showSuccess("Adding a new employee was done successfully");
			}
		} catch (Exception e3) {
			showError();
		}
	}

	private void showSuccess(String s) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(s);

		alert.showAndWait();
	}

	private void showError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setTitle("Error !!");
		alert.setHeaderText("Input Error");
		alert.setContentText("Make sure you fill all the inputs properly");

		alert.showAndWait();
	}

	@FXML
	void updateEmployee(ActionEvent e) throws SQLException {
		try {
			DatabaseAPI.update(eidT.getText(), pidT.getText(), ratingT.getText(), addressT.getText(),
					baseSalaryT.getText(), loginNameT.getText(), loginPasswordP.getText(), startDateD.getValue(),
					endDateD.getValue(), cityidT.getText(), livesFromD.getValue(), livesToD.getValue());

			showSuccess("Updating the information of employee " + eidT.getText() + " was done successfully");
		} catch (Exception e3) {
			showError();
		}
	}

	@FXML
	void deleteEmployee(ActionEvent e) throws SQLException {
		try {
			DatabaseAPI.delete(eidT.getText());
			showSuccess("Employee " + eidT.getText() + " was deleted successfully");
		} catch (Exception e3) {
			showError();
		}
	}

	@FXML
	void clearFields(ActionEvent e) {
		eidT.setText(null);
		pidT.setText(null);
		ratingT.setText(null);
		addressT.setText(null);
		baseSalaryT.setText(null);
		loginNameT.setText(null);
		loginPasswordP.setText(null);
		startDateD.setValue(null);
		endDateD.setValue(null);
		cityidT.setText(null);
		livesFromD.setValue(null);
		livesToD.setValue(null);
	}

}



package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DatabaseAPI {

	public static Connection connection;
	private static final String DBname = "hotel";
	private static final String user = "javaP";
	private static final String password = "hotelP";
	private static PreparedStatement insertStatement;
	private static Statement statement;

	public static void InitializeDB() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/" + DBname + "?useLegacyDatetimeCode=true&serverTimezone=UTC&useSSL=false",
				user, password);

		String insertString = "insert into hotel.employee (eid, pid, rating, address, baseSalary, log_inName, log_inPassward, startDate, endDate, city_id, liveFrom, liveTo) value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		insertStatement = connection.prepareStatement(insertString);
		statement = connection.createStatement();
	}
	
	public static void delete(String eid) throws SQLException {
		statement.execute("delete from employee where eid = " + eid);
	}
	
	public static void update(String eid, String pid, String rating, String address, String baseSalary, String log_inName,
			String log_inPassward, LocalDate startDate, LocalDate endDate, String city_id, LocalDate liveFrom,
			LocalDate liveTo) throws SQLException {
		
		int i = 0;
		String s = "UPDATE employee set ";
		String[] ss;
		String tmp;
		
		if(pid != null && !pid.equals("")) {
			s = s + "pid = " + pid;
			i++;
		}
		
		if(rating != null && !rating.equals("")){
			if(i != 0)
				s += ",";
			s = s + "rating = " + rating;
			i++;
		}
		
		if(address != null && !address.equals("")){
			if(i != 0)
				s += ",";
			s = s + "address = " + address;
			i++;
		}
		
		if(baseSalary != null && !baseSalary.equals("")){
			if(i != 0)
				s += ",";
			s = s + "baseSalary = " + baseSalary;
			i++;
		}
		
		if(log_inName != null && log_inName.length() > 0){
			if(i != 0)
				s += ",";
			s = s + "log_inName = " + log_inName;
			i++;
		}
		
		if(log_inPassward != null && log_inPassward.length() > 0){
			if(i != 0)
				s += ",";
			s = s + "log_inPassward = " + log_inPassward;
			i++;
		}
		
		if(startDate != null){
			if(i != 0)
				s += ",";
			ss = startDate.toString().split("-");
			tmp = ss[0] + ss[1] + ss[2];
			s = s + "startDate = " + tmp;
			i++;
		}
		
		if(endDate != null){
			if(i != 0)
				s += ",";
			ss = endDate.toString().split("-");
			tmp = ss[0] + ss[1] + ss[2];
			s = s + "endDate = " + tmp;
			i++;
		}
		
		if(city_id != null && !city_id.equals("")){
			if(i != 0)
				s += ",";
			s = s + "city_id = " + city_id;
			i++;
		}
		
		if(liveFrom != null){
			if(i != 0)
				s += ",";
			ss = liveFrom.toString().split("-");
			tmp = ss[0] + ss[1] + ss[2];
			s = s + "liveFrom = " + tmp;
			i++;
		}
		
		if(liveTo != null){
			if(i != 0)
				s += ",";
			ss = liveTo.toString().split("-");
			tmp = ss[0] + ss[1] + ss[2];
			s = s + "liveTo = " + tmp;
			i++;
		}
		
		if(i == 0)
			return;
		
		s += " where eid = " + eid;
		statement.execute(s);
	}

	public static void insert(String eid, String pid, String rating, String address, String baseSalary, String log_inName,
			String log_inPassward, LocalDate startDate, LocalDate endDate, String city_id, LocalDate liveFrom,
			LocalDate liveTo) throws SQLException {
		
		String[] start = startDate.toString().split("-");
		String[] end = endDate.toString().split("-");
		
		String[] from = liveFrom.toString().split("-");
		String[] to = liveTo.toString().split("-");
		
		insertStatement.setString(1, eid);
		insertStatement.setString(2, pid);
		insertStatement.setString(3, rating);
		insertStatement.setString(4, address);
		insertStatement.setString(5, baseSalary);
		insertStatement.setString(6, log_inName);
		insertStatement.setString(7,  log_inPassward);
		insertStatement.setString(8,  start[0] + start[1] + start[2]);
		insertStatement.setString(9,  end[0] + end[1] + end[2]);
		insertStatement.setString(10, city_id);
		insertStatement.setString(11,  from[0] + from[1] + from[2]);
		insertStatement.setString(12,  to[0] + to[1] + to[2]);

		insertStatement.executeUpdate();
	}

}*/