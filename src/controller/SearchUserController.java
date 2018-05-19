package controller;

import java.io.IOException;
import java.util.ArrayList;
import helper.SearchUserDAO;
import model.User;

public class SearchUserController {


	/**
	 * searches for the given user
	 * @param name contains name of the user to be searched
	 * @author  Achal Vaish
	 * @return User returns the details of user if found
	 */
	public User searchUser(String name) throws IOException {
		System.out.println("name"+name);
		SearchUserDAO lSearchUserDAO=new SearchUserDAO();
		return lSearchUserDAO.searchUser(name);
	}

	/**
	 * finds parents of the given user
	 * @param name contians name of user whose details are to be retrieved
	 * @author  Manan Tyagi
	 * @return ArrayList<String> This returns the the list of parents or children.
	 */
	public ArrayList<String> getParents(String name) {
		System.out.println("name"+name);
		SearchUserDAO lSearchUserDAO=new SearchUserDAO();
		return lSearchUserDAO.getParents(name);
	}


	/**
	 * finds children of a particular user.
	 * @param name contians name of user whose details are to be retrieved
	 * @author  Manan Tyagi
	 * @return ArrayList<String> This returns the the list of parents or children.
	 */
	public ArrayList<String> getChildren(String name) {
		System.out.println("name"+name);
		// TODO Auto-generated method stub
		SearchUserDAO lSearchUserDAO=new SearchUserDAO();
		return lSearchUserDAO.getParents(name);
	}
}
