package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Util.SQLiteJDBCDriverConnection;

public class CreateTableDAO {
	/**
	 * creates the required tables for the application
	 * @author  Manan Tyagi
	 */
	public void createTable() {

		Connection conn= SQLiteJDBCDriverConnection.connect();
		if(conn==null)
			return;
		System.out.println("Connection established");
		try {
			String query="Create table if not exists User_Profile\n" +
					"(\n" +
					"Name varchar2(200),\n" +
					"display_image blob,\n" +
					"status varchar2(200),\n" +
					"gender varchar2(10),\n" +   
					"age INT,\n" + 
					"state varchar(20),\n" +
					"primary key (Name))";
			
			PreparedStatement pst=conn.prepareStatement(query);
			pst.execute();

			String query1=" create table if not exists relations\n" +
					"(\n" +
					"Name varchar2(200),\n" +
					"friend_name varchar2(200),\n" +
					"Relation varchar2(200),\n" +
					"foreign key (Name) references User_Profile(Name),\n" +
					"foreign key (friend_name) references User_Profile(Name),\n" +
					"primary key(Name,friend_name)\n" +
					")";

			PreparedStatement pst1=conn.prepareStatement(query1);
			pst1.execute();


		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
