package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import Exception.NoAvailableException;
import Exception.NoSuchAgeException;
import Exception.NotToBeCoupledException;
import helper.CreateNewUserDAO;
import model.Child;
import model.User;
import model.YoungChild;

public class AddNewUserController {
	

	/**
	 * Creates a new user from the details obtained
	 * @author  Achal Vaish
	 * @param adult contains name of the user to be created
	 */
	public boolean createAdult(User adult) throws NoSuchAgeException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Creating the user with following details");
		System.out.println("Name: "+adult.getName());
		System.out.println("Age: "+adult.getAge());
		System.out.println("Image: "+adult.getDisplay_picture());
		System.out.println("Gender: "+adult.getGender());
		System.out.println("State: "+adult.getState());
		System.out.println("Status: "+adult.getStatus());
		
		if(adult.getAge()<0||adult.getAge()>150)
		{
			JOptionPane.showMessageDialog(null,"Throwing an NoSuchAgeException exception-Enter an age between 0-150! Age entered is "+adult.getAge());
			throw new NoSuchAgeException();

		}

		CreateNewUserDAO createNewUserDAO=new CreateNewUserDAO();
		
		if(createNewUserDAO.checkUserId(adult.getName()))
			return createNewUserDAO.createUser(adult);
		else
			return false;		
	}

	/**
	 * Checks if provided user name is unique
	 * @param user_id contains name of the user to be checked
	 * @author  Achal Vaish
	 * @return boolean Returns false if the user id exists already, otherwise returns true
	 */
	
	public boolean checkUserId(String name) {
		System.out.println("Name recieved: "+name);
		System.out.println("Calling CreateNewUserDAO for function checkUserId");
		CreateNewUserDAO createUserDAO=new CreateNewUserDAO();
		return createUserDAO.checkUserId(name);
	}


	/**
	 * creates a new Child user from the given details
	 * @author  Achal Vaish
	 * @param child contains the child user to be created
	 */
	public boolean createChild(User child) throws NoAvailableException, NoSuchAgeException, NotToBeCoupledException, IOException {
		
		if(child.getAge()<0||child.getAge()>150)
			throw new NoSuchAgeException();
		
		CreateNewUserDAO createUserDAO=new CreateNewUserDAO();
		
		if(createUserDAO.checkParentFrDependent(((Child) child).getParent_id1().toLowerCase(),((Child) child).getParent_id2().toLowerCase())&&createUserDAO.checkParentFrDependent(((Child) child).getParent_id2().toLowerCase(),((Child) child).getParent_id1().toLowerCase()))
		{
			System.out.println("The couple is mutually exclusive.Creating Child");
			try {
				return createUserDAO.createUser(child);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File Not Found");
			}
		}
		else if(createUserDAO.getAge(((Child) child).getParent_id1().toLowerCase())<16||createUserDAO.getAge(((Child) child).getParent_id2().toLowerCase())<16)
		{
			JOptionPane.showMessageDialog(null,"The couple is not mutually exclusive");
			throw new NotToBeCoupledException();
		}
		
		else
			throw new NoAvailableException();
		return false;
	}

	/**
	 * creates a new YoungChild user from the given details
	 * @author  Achal Vaish
	 * @param youngChild contains the Young child user to be created
	 */
	public boolean createYoungChild(User youngChild) throws NoAvailableException, NoSuchAgeException, IOException {
		
		if(youngChild.getAge()<0||youngChild.getAge()>150)
			throw new NoSuchAgeException();
		
		CreateNewUserDAO createuserDAO=new CreateNewUserDAO();
		
		if(createuserDAO.checkParentFrDependent(((YoungChild) youngChild).getParent_id1().toLowerCase(),((YoungChild) youngChild).getParent_id2().toLowerCase())&&createuserDAO.checkParentFrDependent(((YoungChild) youngChild).getParent_id2().toLowerCase(),((YoungChild) youngChild).getParent_id1().toLowerCase()))
		{
			System.out.println("The couple is mutually exclusive.Creating Child");
			try {
				return createuserDAO.createUser(youngChild);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"File Not Found");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null,"The couple is not mutually exclusive");
			throw new NoAvailableException();
		}
		return false;
	}
}
