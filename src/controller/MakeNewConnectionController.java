package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import Exception.NotToBeColleaguesException;
import Exception.NotToBeFriendsException;
import Exception.TooYoungException;
import helper.MakeNewConnectionDAO;
import model.User;

public class MakeNewConnectionController {

	/**
	 * creates a new connection between the given two users
	 * @param name name of first user to connect
	 * @param name2 name of second user to connect
	 * @param relation name of the relation to be formed between the two users
	 * @return boolean
	 */
	public boolean createNewConnection(String name, String name2, String relation) {
		
		MakeNewConnectionDAO makeNewConnectionDAO=new MakeNewConnectionDAO();

		try {
			return makeNewConnectionDAO.createNewConnection(name,name2,relation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		// TODO Auto-generated method stub
		return false;

	}

	/**
	 * checks if there is a direct connection between the given two users
	 * @param name name of first user involved in a connection
	 * @param name2 name of second user involved in a connection
	 * @return boolean returns true if there is a direct connection between the two users
	 */
	public boolean checkConnection(String name, String name2) {
		// TODO Auto-generated method stub
		
		MakeNewConnectionDAO makeNewConnectionDAO=new MakeNewConnectionDAO();

		if((makeNewConnectionDAO.checkConnection(name,name2))||makeNewConnectionDAO.checkConnection(name2,name))
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * checks if there is a relation between the two given users
	 * @param userName name of first user involved in a connection
	 * @param userName1 name of second user involved in a connection
	 * @return boolean returns true if there is a relation between the two users
	 */
	public String getConnection(String userName, String userName1) {
		// TODO Auto-generated method stub
		MakeNewConnectionDAO makeNewConnectionDAO=new MakeNewConnectionDAO();	
		return makeNewConnectionDAO.checkRelation(userName,userName1);
	}

	/**
	 * determines if a valid connection can be formed and forms the connection if it's a valid connection
	 * @param userName name of first user to connect
	 * @param userName1 name of second user to connect
	 * @param relation name of the relation to be formed between the two users
	 * @return boolean returns true if connection is formed
	 * @throws TooYoungException
	 * @throws NotToBeFriendsException
	 * @throws NotToBeColleaguesException
	 * @throws IOException
	 */
	public boolean createConnection(String userName,String userName1,String relation) throws TooYoungException, NotToBeFriendsException, NotToBeColleaguesException, IOException
	{

		SearchUserController searchUserController=new SearchUserController();
		User user;
		User user1;
		user=searchUserController.searchUser(userName.toLowerCase());
		user1=searchUserController.searchUser(userName1.toLowerCase());
		if(user!=null&&user1!=null)
		{
			System.out.println("User 1 and User 2 found!");
			MakeNewConnectionController makeNewConnectionController=new MakeNewConnectionController();


			if(relation.equals("friends"))
			{
				if(user.getAge()>16&&user1.getAge()<16||user1.getAge()>16&&user.getAge()<16)
				{
					JOptionPane.showMessageDialog(null,"Throwing NotToBeFriendsException:Can't Connect an adult and a child friend");
					throw new NotToBeFriendsException();


				}
				else if(user1.getAge()<3||(user.getAge()<3))
				{
					JOptionPane.showMessageDialog(null,"Throwing TooYoungException: Can't Connect to a Young Child");
					throw new TooYoungException();

				}
				else if(user.getAge()<16&&user1.getAge()<16)
				{

					if(Math.abs(user.getAge()-user1.getAge())>3)
					{
						JOptionPane.showMessageDialog(null,"Throwing NotToBeFriendsException: Can't Connect connect two children with an age gap larger than 3");
						throw new NotToBeFriendsException();
					}
					if(Math.abs(user.getAge()-user1.getAge())<=3)
					{
						ArrayList<String> parentsList=searchUserController.getParents(user.getName());
						ArrayList<String> parentsList1=searchUserController.getParents(user1.getName());
						Collections.sort(parentsList);
						Collections.sort(parentsList1);
						System.out.println(parentsList);
						System.out.println(parentsList1);

						if(parentsList.equals(parentsList1))
						{
							System.out.println("Throwing NotToBeFriendsException: Can't Connect 2 people from the same family!");
							System.out.println("Parent List 1"+parentsList);
							System.out.println("Parent List 2"+parentsList1);
							throw new NotToBeFriendsException();				
						}
						else
						{
							System.out.println("Making a connection");
							makeNewConnectionController.createNewConnection(user.getName(),user1.getName(),"friends");
							return true;
						}
					}
				}
				else
				{
					System.out.println("Making a connection");
					makeNewConnectionController.createNewConnection(user.getName(),user1.getName(),"friends");
					return true;
				}
			}
			else if(relation.equals("colleague"))
			{
				if(user.getAge()>16&&user1.getAge()>16)
				{
					System.out.println("Making a connection");
					makeNewConnectionController.createNewConnection(user.getName(),user1.getName(),"colleague");
					return true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Throwing NotToBeColleaguesException: Can't Connect  connect a child in a colleague relation");
					throw new NotToBeColleaguesException();
				}

			}
			else if(relation.equals("classmate"))
			{
				if(user.getAge()<3||user1.getAge()<3)
				{
					JOptionPane.showMessageDialog(null,"Throwing NotToBeColleaguesException: Can't Connect  connect a child in a colleague relation");
					throw new NotToBeColleaguesException();
				}
				else
				{
					System.out.println("Making a connection");
					makeNewConnectionController.createNewConnection(user.getName(),user1.getName(),"classmate");
					return true;
				}
			}
			else if(relation.equals("parent")||(relation.equals("couple")))
			{
				System.out.println("Making a connection");
				makeNewConnectionController.createNewConnection(user.getName(),user1.getName(),relation);
			}
		}
		return false;

	}


}
