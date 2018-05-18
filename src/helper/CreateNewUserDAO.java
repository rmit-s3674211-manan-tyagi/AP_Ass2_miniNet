package helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Util.SQLiteJDBCDriverConnection;
import model.Child;
import model.User;
import model.YoungChild;

public class CreateNewUserDAO {



	/**
	 * creates a new user row in the database
	 * @author  Achal Vaish
	 * @param adult contains user to be created
	 */
	public boolean createUser(User adult) throws IOException {
		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="insert into User_Profile values(?,?,?,?,?,?)";

			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, adult.getName().toLowerCase());
			System.out.println(adult.getDisplay_picture());
			if(adult.getDisplay_picture()!=null)
			{
				File f = new File(adult.getDisplay_picture());
				FileInputStream fis = new FileInputStream(f);
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				for (int len; (len = fis.read(buffer)) != -1;) 
					bos.write(buffer, 0, len);
				pst.setBytes(2, bos.toByteArray());
				fis.close();
			}

			// pst.setString(2, adult.getDisplay_picture());
			pst.setString(3, adult.getStatus());
			pst.setString(4, adult.getGender());
			pst.setInt(5, adult.getAge());
			pst.setString(6, adult.getState());

			int rs=pst.executeUpdate();
			if(rs==0)
				return false;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		if(adult instanceof Child||adult instanceof YoungChild)
		{
			

			try {
				String query = "insert into relations values(?,?,?)";
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setString(1, adult.getName().toLowerCase());
				if(adult instanceof Child)
				{
					pst.setString(2, ((Child) adult).getParent_id1().toLowerCase());

				}
				else
				{
					pst.setString(2, ((YoungChild) adult).getParent_id1().toLowerCase());

				}
				pst.setString(3, "parent");
				pst.executeUpdate();
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}


			try {
				String query = "insert into relations values(?,?,?)";
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setString(1, adult.getName().toLowerCase());
				if(adult instanceof Child)
				{
					pst.setString(2, ((Child) adult).getParent_id2().toLowerCase());
				}
				else
				{
					pst.setString(2, ((YoungChild) adult).getParent_id2().toLowerCase());
				}
				pst.setString(3, "parent");
				pst.executeUpdate();
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}


			if(adult instanceof Child)
			{
				if(checkParentAdded(((Child) adult).getParent_id1(),((Child) adult).getParent_id2())&&checkParentAdded(((Child) adult).getParent_id2(),((Child) adult).getParent_id1()))
				{
					try {
						String query = "insert into relations values(?,?,?)";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1, ((Child) adult).getParent_id1().toLowerCase());
						pst.setString(2, ((Child) adult).getParent_id2().toLowerCase());
						pst.setString(3, "couple");
						pst.executeUpdate();
					}
					catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}  
			}
			else
			{
				if(checkParentAdded(((YoungChild) adult).getParent_id1(),((YoungChild) adult).getParent_id2())&&checkParentAdded(((YoungChild) adult).getParent_id2(),((YoungChild) adult).getParent_id1()))
				{
					try {
						String query = "insert into relations values(?,?,?)";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1, ((YoungChild) adult).getParent_id1().toLowerCase());
						pst.setString(2, ((YoungChild) adult).getParent_id2().toLowerCase());
						pst.setString(3, "couple");
						pst.executeUpdate();
					}
					catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}

				}
			}

		}
		return true;
	}

	/**
	 * checks if parents for the given user already exist
	 * @author  Achal Vaish
	 * @param parent_id1 name of first parent is passed.
	 * @param parent_id2 name of second parent is passed.
	 * @return boolean returns false if parents are already added
	 */
	boolean checkParentAdded(String parent_id1, String parent_id2) {

		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select count(*) from relations where trim(lower(Name))=? and trim(lower(friend_name)) = ? and Relation=? ";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,parent_id1.toLowerCase());
			pst.setString(2,parent_id2.toLowerCase());
			pst.setString(3,"couple");
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)>0) {
					System.out.println("Parent already added");
					return false;
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
		System.out.println("Parent not added");
		return true; 

	}

	/**
	 * checks if the user name is unique.
	 * @param user_id contains name of the user to be verified
	 * @author  Achal Vaish
	 * @return boolean returns false if the user name already exists
	 */
	public boolean checkUserId(String user_id) {

		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select count(*) from User_Profile where trim(lower(name))= ?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,user_id.toLowerCase());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)>0) {
					System.out.println("Username does not exist!");

					return false;
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
		System.out.println("Username does not exist!");
		return true;
	}

	/**
	 * checks if the given users are mutually exclusive so as to qualify to connect with a child
	 * @param userParent1 name of first parent 
	 * @param userParent2 name of second parent
	 * @author  Manan Tyagi
	 * @return boolean returns false if users are not mutually exclusive
	 */
	public boolean checkParentFrDependent(String userParent1, String userParent2) {

		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select count(*) from relations where trim(lower(Name))=? and trim(lower(friend_name)) <> ? and Relation=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,userParent1.toLowerCase());
			pst.setString(2,userParent2.toLowerCase());  
			pst.setString(3,"couple");
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)>0) {
					System.out.println("Parent already exists as a couple");
					return false;
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
		return true;
	}

	/**
	 * finds tha age of the given user
	 * @param user1 name of the user
	 * @author  Manan Tyagi
	 * @return int returns the age of the user
	 */
	public int getAge(String user1) {
	
		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select age from User_Profile where lower(name)= ?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,user1.toLowerCase());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				System.out.println("age= " + rs.getInt(1));
				return rs.getInt(1);
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
		return 0;
	}


}
