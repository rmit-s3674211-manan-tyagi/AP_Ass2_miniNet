package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Exception.NoParentException;
import helper.DeleteUserDAO;
import model.User;

public class DeleteUserController {

	/**
	 * deletes user from the list of users on the network
	 * @param name contains name of user to be deleted
	 * @author  Achal Vaish
	 */
	public void deleteUser(String name) throws NoParentException, IOException {
		// TODO Auto-generated method stub
				
		SearchUserController searchUserController=new SearchUserController();
		User user=searchUserController.searchUser(name);
		
		if(user==null)
			return ;
		
		if(user.getAge()>16)
		{
			ArrayList<String> childList=searchUserController.getChildren(name);
			
			if(childList.size()>0)
			{
				JOptionPane.showMessageDialog(null,"NoParentException-Can't Delete parent having dependent");
				throw new NoParentException();
			}
		}
		
		DeleteUserDAO deleteUserDAO=new DeleteUserDAO();
		deleteUserDAO.deleteUser(name);
	}

}
