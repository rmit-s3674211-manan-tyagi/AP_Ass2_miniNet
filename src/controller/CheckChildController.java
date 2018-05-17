package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Exception.NoParentException;
import helper.SearchUserDAO;

public class CheckChildController {


	/**
	 * checks and deletes the parent information for non-Adult users that do not correspond to the relations specified in the relations file
	 * @author  Achal Vaish
	 * @throws NoParentException 
	 */
	public void checkYoung() throws NoParentException {
		
		// TODO Auto-generated method stub
		SearchUserDAO searchUserDAO=new SearchUserDAO();
		ArrayList<String> deleteList=new ArrayList<>();
		deleteList=searchUserDAO.searchUser();
		
		if(deleteList!=null)
		{			
			for(String s:deleteList)
			{
				DeleteUserController deleteUserController=new DeleteUserController();
				try {
					deleteUserController.deleteUser(s);
				} catch (NoParentException e) {
					// TODO Auto-generated catch block
					throw new NoParentException();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Error reading from File");
				}
			}
		}
	}

}
