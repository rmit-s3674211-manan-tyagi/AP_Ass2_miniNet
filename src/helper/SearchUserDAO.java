package helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Util.SQLiteJDBCDriverConnection;
import model.Adult;
import model.Child;
import model.User;
import model.YoungChild;

public class SearchUserDAO {


	/**
	 * searches for the given user in the database
	 * @param name name of the user to be searched
	 * @author  Achal Vaish
	 * @return User returns the details of user if found
	 */
	public User searchUser(String name) throws IOException {

		Connection conn= SQLiteJDBCDriverConnection.connect();
		User user=null;
		try {
			String query="select * from User_Profile where trim(lower(name))= ?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,   name.toLowerCase());
			ResultSet rs=pst.executeQuery();

			while(rs.next())
			{
				System.out.println("User found");

				if( rs.getInt(5)>16)
				{
					System.out.println("User is an adult");
					user=new Adult();
					user.setName(rs.getString(1));
					if(rs.getBinaryStream(2)!=null)
					{
						System.out.println("Image found");
						File file = new File(user.getName()+".jpg");
						FileOutputStream fos = new FileOutputStream(file);

						java.io.InputStream input = rs.getBinaryStream(2);
						byte[] buffer = new byte[1024];
						while (input.read(buffer) > 0) {
							fos.write(buffer);
						}
						user.setDisplay_picture(file.getPath());
						fos.close();
					}
					System.out.println(user.getDisplay_picture());
					user.setStatus(rs.getString(3));

					user.setGender(rs.getString(4));
					user.setAge(rs.getInt(5));
					user.setState(rs.getString(6));

				}
				else if(rs.getInt(5)>2)
				{
					System.out.println("User is a Child");
					user=new Child();
					user.setName(rs.getString(1));
					if(rs.getBinaryStream(2)!=null)
					{
						System.out.println("Image found");
						File file = new File(user.getName()+".jpg");
						FileOutputStream fos = new FileOutputStream(file);

						java.io.InputStream input = rs.getBinaryStream(2);
						byte[] buffer = new byte[1024];
						while (input.read(buffer) > 0) {
							fos.write(buffer);
						}
						user.setDisplay_picture(file.getPath());
						fos.close();
					}
					user.setStatus(rs.getString(3));
					user.setGender(rs.getString(4));
					user.setAge(rs.getInt(5));
					user.setState(rs.getString(6));
				}
				else
				{
					System.out.println("User is a Young Child");
					user=new YoungChild();
					user.setName(rs.getString(1));
					if(rs.getBinaryStream(2)!=null)
					{
						System.out.println("Image found");
						File file = new File(user.getName()+".jpg");
						FileOutputStream fos = new FileOutputStream(file);

						java.io.InputStream input = rs.getBinaryStream(2);
						byte[] buffer = new byte[1024];
						while (input.read(buffer) > 0) {
							fos.write(buffer);
						}
						user.setDisplay_picture(file.getPath());
						fos.close();
					}
					user.setStatus(rs.getString(3));
					user.setGender(rs.getString(4));
					user.setAge(rs.getInt(5));
					user.setState(rs.getString(6));
				}
			}


		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
		}
		return user;
	}

	/**
	 * searches non-Adult Records which do not have parent information .
	 * @author  Achal Vaish
	 * @return ArrayList<String> returns the list of the non-Adult which do not have parent information.
	 */
	public ArrayList<String> searchUser()
	{
		Connection conn= SQLiteJDBCDriverConnection.connect();
		ArrayList<String> deleteList=new ArrayList<>();
		try {
			String query="select * from User_Profile";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();

			while(rs.next())
			{
				if( rs.getInt(5)<16)
				{
					ArrayList<String> parentsList=getParents(rs.getString(1));

					if(parentsList.size()!=2)
					{
						JOptionPane.showMessageDialog(null, "There are not 2 parents for this record. Hence putting in delete List for "+ rs.getString(1));
						deleteList.add(rs.getString(1));

					}
				}

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		} 
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
		}
		return deleteList;


	}

	/**
	 * finds parents or children of the given user.
	 * @param name name of user whose relations are to be determined
	 * @author  Manan Tyagi
	 * @return ArrayList<String> This returns the the list of parents or children.
	 */
	public ArrayList<String> getParents(String name) {

		Connection conn= SQLiteJDBCDriverConnection.connect();
		ArrayList<String> parentList =new ArrayList<>();
		try {
			String query="select Name from relations d where lower(d.friend_name)=? and Relation=?" +
					" UNION\n" +
					"select friend_name from relations  where lower(Name)=? and Relation=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,name.toLowerCase());
			pst.setString(2,"parent");
			pst.setString(3,name.toLowerCase());
			pst.setString(4,"parent");
			ResultSet rs=pst.executeQuery();
			while (rs.next())
			{
				System.out.println("Adding values to the list "+rs.getString(1));
				parentList.add(rs.getString(1));
			}


		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
		}
		return parentList;
	}

}
