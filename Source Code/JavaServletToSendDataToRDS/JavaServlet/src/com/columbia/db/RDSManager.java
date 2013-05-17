package com.columbia.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class RDSManager {

	Connection connection;

	public RDSManager() {
		init();
	}

	private void init() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://mydb.cqxzi0jagmm9.us-east-1.rds.amazonaws.com:3306/";
			String userName = "mydb";
			String password = "1234567890";
			String dbName = "mydb";
			connection = DriverManager.getConnection(url + dbName, userName, password);

		} catch (Exception e)
		{
			System.out.println("ERROR!!!!" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void setGyroscopeData(String cn, String t, String x, String y, String z)
	{
		try {
			String insertQuery = "insert into s2aas_wreckwatch (users_cellnumber_id, time, x_ang, y_ang, z_ang) values (?,?,?,?,?)";
			System.out.println("gyro");
			PreparedStatement preparedStatement5 = connection.prepareStatement(insertQuery);
			preparedStatement5.setString(1, cn);
			preparedStatement5.setString(2, t);
			preparedStatement5.setDouble(3, Double.parseDouble(x));
			preparedStatement5.setDouble(4, Double.parseDouble(y));
			preparedStatement5.setDouble(5, Double.parseDouble(z));
			preparedStatement5.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("ERROR!!!!!!! " + e.getMessage());
			e.printStackTrace();
		}
	}
	public void setRotationalVectorData(String cn, String t, String x, String y, String z)
	{
		try {
			String insertQuery = "insert into s2aas_rotational_vector (users_cellnumber_id, time, x_rot, y_rot, z_rot) values (?,?,?,?,?)";
			System.out.println("rotvec");
			PreparedStatement preparedStatement5 = connection.prepareStatement(insertQuery);
			preparedStatement5.setString(1, cn);
			preparedStatement5.setString(2, t);
			preparedStatement5.setDouble(3, Double.parseDouble(x));
			preparedStatement5.setDouble(4, Double.parseDouble(y));
			preparedStatement5.setDouble(5, Double.parseDouble(z));
			preparedStatement5.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("ERROR!!!!!!! " + e.getMessage());
			e.printStackTrace();
		}
	}
	public void setLinearAccelerometerData(String cn, String t, String x, String y, String z)
	{
		try {
			String insertQuery = "insert into s2aas_linear_acceleration (users_cellnumber_id, time, x_LA, y_LA, z_LA) values (?,?,?,?,?)";
			System.out.println("linacc");
			PreparedStatement preparedStatement5 = connection.prepareStatement(insertQuery);
			preparedStatement5.setString(1, cn);
			preparedStatement5.setString(2, t);
			preparedStatement5.setDouble(3, Double.parseDouble(x));
			preparedStatement5.setDouble(4, Double.parseDouble(y));
			preparedStatement5.setDouble(5, Double.parseDouble(z));
			preparedStatement5.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("ERROR!!!!!!! " + e.getMessage());
			e.printStackTrace();
		}
	}
	public String setUsersData(String cn, String name, String bat, String linacc, String gyro, String prox, String baro, String mic, String photo, String rotvec, String gps)
	{
		String s = "1";
		try {
			System.out.println("userdata");
			s += " try ";
			String selectQuery = "select * from s2aas_users where users_cellnumber=?";
			PreparedStatement preparedStatement9 = connection.prepareStatement(selectQuery);
			preparedStatement9.setString(1, cn);
			ResultSet rs = preparedStatement9.executeQuery();
			if(rs.next())
			{
				s += " if ";
				String insertQuery = "update s2aas_users set name=?, bat=?, linacc=?, gyro=?, prox=?, baro=?, mic=?, photo=?, rotvec=?, gps=? where users_cellnumber=?";
				PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
				preparedStatement1.setString(1, name);
				preparedStatement1.setInt(2, Integer.parseInt(bat));
				preparedStatement1.setInt(3, Integer.parseInt(linacc));
				preparedStatement1.setInt(4, Integer.parseInt(gyro));
				preparedStatement1.setInt(5, Integer.parseInt(prox));
				preparedStatement1.setInt(6, Integer.parseInt(baro));
				preparedStatement1.setInt(7, Integer.parseInt(mic));
				preparedStatement1.setInt(8, Integer.parseInt(photo));
				preparedStatement1.setInt(9, Integer.parseInt(rotvec));
				preparedStatement1.setInt(10, Integer.parseInt(gps));
				preparedStatement1.setString(11, cn);
				preparedStatement1.executeUpdate();
				s += cn + " " + name + " " + bat + " " + linacc + " " + gyro + " " + prox + " " + baro + " " + mic + " " + photo + " " + rotvec + " " + gps + " ";
			}
			else
			{
				s += " else ";
				String insertQuery = "insert into s2aas_users (users_cellnumber, name, bat, linacc, gyro, prox, baro, mic, photo, rotvec, gps) values (?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
				preparedStatement1.setString(1, cn);
				preparedStatement1.setString(2, name);
				preparedStatement1.setInt(3, Integer.parseInt(bat));
				preparedStatement1.setInt(4, Integer.parseInt(linacc));
				preparedStatement1.setInt(5, Integer.parseInt(gyro));
				preparedStatement1.setInt(6, Integer.parseInt(prox));
				preparedStatement1.setInt(7, Integer.parseInt(baro));
				preparedStatement1.setInt(8, Integer.parseInt(mic));
				preparedStatement1.setInt(9, Integer.parseInt(photo));
				preparedStatement1.setInt(10, Integer.parseInt(rotvec));
				preparedStatement1.setInt(11, Integer.parseInt(gps));
				preparedStatement1.executeUpdate();
				s += cn + " " + name + " " + bat + " " + linacc + " " + gyro + " " + prox + " " + baro + " " + mic + " " + photo + " " + rotvec + " " + gps + " ";
			}
			s += "2";
		}
		catch (Exception e) {
			s += " catch ";
			System.out.println("ERROR!!!!!!! " + e.getMessage());
			e.printStackTrace();
		}
		return s;
	}
	public void setBatteryData(String c, String t, String l) {

		try {
			String insertQuery = "insert into s2aas_battery (users_cellnumber_id, time, level) values (?,?,?)";
			System.out.println("bat");
			PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
			preparedStatement1.setString(1, c);
			preparedStatement1.setString(2, t);
			preparedStatement1.setInt(3, Integer.parseInt(l));
			preparedStatement1.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("ERROR!!!!!!! " + e.getMessage());
			e.printStackTrace();
		}
	}
	public void setProximityData(String c, String t, String prox) {

		try {
			String insertQuery = "insert into s2aas_proximity (users_cellnumber_id, time, proximity) values (?,?,?)";
			System.out.println("prox");
			PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
			preparedStatement1.setString(1, c);
			preparedStatement1.setString(2, t);
			preparedStatement1.setDouble(3, Double.parseDouble(prox));
			preparedStatement1.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("ERROR!!!!!!! " + e.getMessage());
			e.printStackTrace();
		}
	}
	public void setPressureData(String c, String t, String pres) {

		try {
			String insertQuery = "insert into s2aas_barometer (users_cellnumber_id, time, pressure) values (?,?,?)";
			System.out.println("baro");
			PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
			preparedStatement1.setString(1, c);
			preparedStatement1.setString(2, t);
			preparedStatement1.setDouble(3, Double.parseDouble(pres));
			preparedStatement1.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("ERROR!!!!!!! " + e.getMessage());
			e.printStackTrace();
		}
	}
	public void setLightData(String c, String t, String l) {

		try {
			String insertQuery = "insert into s2aas_photometer (users_cellnumber_id, time, ambient_light) values (?,?,?)";
			System.out.println("photo");
			PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
			preparedStatement1.setString(1, c);
			preparedStatement1.setString(2, t);
			preparedStatement1.setDouble(3, Double.parseDouble(l));
			preparedStatement1.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("ERROR!!!!!!! " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setGpsData(String c, String t, String x, String y) {

		try {
			String insertQuery = "insert into s2aas_gps (users_cellnumber_id, time, x_axis, y_axis) values (?,?,?,?)";
			System.out.println("gps");
			PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
			preparedStatement1.setString(1, c);
			preparedStatement1.setString(2, t);
			preparedStatement1.setDouble(3, Double.parseDouble(x));
			preparedStatement1.setDouble(4, Double.parseDouble(y));
			preparedStatement1.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("ERROR!!!!!!! " + e.getMessage());
			e.printStackTrace();
		}
	}
}
