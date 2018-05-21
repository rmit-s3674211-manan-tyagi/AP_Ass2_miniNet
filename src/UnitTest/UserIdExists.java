package UnitTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Exception.NoParentException;
import Exception.NoSuchAgeException;
import controller.AddNewUserController;
import controller.DeleteUserController;
import controller.SearchUserController;
import helper.CreateTableDAO;
import model.Adult;
import model.User;

public class UserIdExists {

	AddNewUserController addNewUserController=new AddNewUserController();
	SearchUserController searchUserController=new SearchUserController();
	 @BeforeClass
	    public static void runOnceBeforeClass() throws NoParentException {
		 CreateTableDAO lCreateTableDAO=new CreateTableDAO();
		 lCreateTableDAO.createTable();
	    }
	 
	 @Test
		public void userIdPresent() throws NoSuchAgeException, IOException {
			User adult=new Adult();
			adult.setName("Achal Vaish");
			adult.setAge(20);
			adult.setGender("M");
			adult.setState("VIC");
			boolean createUser=addNewUserController.createAdult(adult);
			boolean userPresent=addNewUserController.checkUserId(adult.getName());
			assertEquals(userPresent,false);
			
		}

	 @AfterClass
	    public static void runOnceAfterClass() throws NoParentException, IOException {
		 DeleteUserController lDeleteUserController=new DeleteUserController();
		 lDeleteUserController.deleteUser("Achal Vaish");
	    }
}
