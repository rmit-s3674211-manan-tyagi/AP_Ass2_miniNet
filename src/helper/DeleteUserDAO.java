package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Util.SQLiteJDBCDriverConnection;

public class DeleteUserDAO {


	/**
	 * deletes the user from the database
	 * @param uName The Name of user is passed.
	 * @author  Achal Vaish
	 */
	public void deleteUser(String name) {
		
		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="delete from relations where lower(name)=? or lower(friend_name) = ? ";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,name.toLowerCase());
			pst.setString(2,name.toLowerCase());
			pst.executeUpdate();

			query="delete from User_Profile where lower(name)=? ";
			pst=conn.prepareStatement(query);
			pst.setString(1,name.toLowerCase());
			pst.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
