package UnitTest;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import Exception.NoAvailableException;
import Exception.NoParentException;
import Exception.NoSuchAgeException;
import Exception.NotToBeCoupledException;
import controller.AddNewUserController;
import controller.SearchUserController;
import helper.CreateTableDAO;
import model.Adult;
import model.Child;
import model.User;

public class SearchUser {

	AddNewUserController lAddNewUserController=new AddNewUserController();
	SearchUserController lSearchUserController=new SearchUserController();
	 @BeforeClass
	    public static void runOnceBeforeClass() throws NoParentException {
		 CreateTableDAO lCreateTableDAO=new CreateTableDAO();
		 lCreateTableDAO.createTable();
	    }
	 
	 @Test()
		public void searchUser() throws NoSuchAgeException, IOException, NoAvailableException, NotToBeCoupledException, NoParentException, InterruptedException {
			User adult=new Adult();
			adult.setName("Ben");
			adult.setAge(31);
			adult.setGender("M");
			adult.setState("VIC");
			lAddNewUserController.createAdult(adult);
			User adult1=new Adult();
			adult1.setName("Zoe");
			adult1.setAge(29);
			adult1.setGender("F");
			adult1.setState("QLD");
			lAddNewUserController.createAdult(adult);
		 	User child=new Child();
			child.setName("Mark");
			child.setAge(12);
			child.setGender("M");
			child.setState("VIC");
			((Child) child).setParent_id1("Ben");
			((Child) child).setParent_id2("Zoe");
			boolean createUser=lAddNewUserController.createChild(child);
			User searchchild=lSearchUserController.searchUser("Mark");
			lSearchUserController.searchUser("Mark");
			assertTrue(searchchild.getName().equalsIgnoreCase(child.getName()));
			
		

		}
	 
	 

}
