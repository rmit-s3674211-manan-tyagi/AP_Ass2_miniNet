package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Util.SQLiteJDBCDriverConnection;

public class MakeNewConnectionDAO {


	/**
	 * connects two users
	 * @param name name of first user
	 * @param name2 name of second user
	 * @param relation relation name that exists between the given users
	 * @return boolean returns true if the given users are connected successfully
	 * @author  Achal Vaish
	 */

	public boolean createNewConnection(String name, String name2, String relation) throws SQLException {

		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query = "insert into relations values(?,?,?)";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, name2);
			pst.setString(3, relation);
			int rs=pst.executeUpdate();
			if(rs>0)
			{
				System.out.println("Added Succesfully");
				return true;
			}
		}


		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return false; 
	}

	/**
	 * checks if the given two users are friends.
	 * @param name name of first user
	 * @param name2 name of second user
	 * @author  Manan Tyagi
	 * @return boolean returns true if they are friends otherwise false.
	 */
	public boolean checkConnection(String name, String name2) {

		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select count(*) from relations where trim(lower(Name))=? and trim(lower(friend_name))= ?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,name.toLowerCase());
			pst.setString(2,name2.toLowerCase());  

			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)>0) {
					System.out.println("Relation Exists");
					return true;
				}
			}


		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return false;

	}

	/**
	 * obtains the relation between the two given users
	 * @param userName The Name of user1 is passed.
	 * @param userName1 The Name of user2 is passed.
	 * @author  Achal Vaish
	 * @return String returns the relation name
	 */
	public String checkRelation(String userName, String userName1) {
		
		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select relation from relations where trim(lower(Name))=? and trim(lower(friend_name))= ? "
					+ "UNION"
					+ " select relation from relations where trim(lower(Name))=? and trim(lower(friend_name))= ?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,userName.toLowerCase());
			pst.setString(2,userName1.toLowerCase());  
			pst.setString(3,userName1.toLowerCase());  
			pst.setString(4,userName.toLowerCase());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				System.out.println("Relation found =" + rs.getString(1));
				return rs.getString(1);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return null;
	}

}
