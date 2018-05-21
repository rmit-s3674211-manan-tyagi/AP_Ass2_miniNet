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
import helper.CreateTableDAO;
import model.Adult;
import model.User;

public class AddUser {
	 
	AddNewUserController addNewUserController=new AddNewUserController();
	
	 @BeforeClass
	    public static void runOnceBeforeClass() throws NoParentException {
		 CreateTableDAO lCreateTableDAO=new CreateTableDAO();
		 lCreateTableDAO.createTable();
	    }
	 
	@Test(expected = NoSuchAgeException.class)
	public void testAge() throws NoSuchAgeException, IOException {
		User adult=new Adult();
		adult.setName("Bryan");
		adult.setAge(250);
		adult.setGender("M");
		adult.setState("ACT");
		boolean createUser=addNewUserController.createAdult(adult);
		
	}

	@Test
	public void createUser() throws NoSuchAgeException, IOException {
		User adult=new Adult();
		adult.setName("Randell White");
		adult.setAge(28);
		adult.setGender("M");
		adult.setState("VIC");
		boolean createUser=addNewUserController.createAdult(adult);
		assertEquals(createUser,true);
		
	}
	
	@Test
	public void createDuplicateUser() throws NoSuchAgeException, IOException {
		User adult=new Adult();
		adult.setName("Gilfoyle");
		adult.setAge(32);
		adult.setGender("M");
		adult.setState("QLD");
		boolean createUser=addNewUserController.createAdult(adult);
		User adult1=new Adult();
		adult1.setName("Gilfoyle");
		adult1.setAge(32);
		adult1.setGender("M");
		adult1.setState("QLD");
		createUser=addNewUserController.createAdult(adult);
		assertEquals(createUser,false);
		
	}
	 @AfterClass
	    public static void runOnceAfterClass() throws NoParentException, IOException {
		 DeleteUserController lDeleteUserController=new DeleteUserController();
		 lDeleteUserController.deleteUser("Randell White");
		 lDeleteUserController.deleteUser("Gilfoyle");
	    }
}
