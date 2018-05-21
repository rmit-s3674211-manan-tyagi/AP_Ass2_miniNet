package UnitTest;

import java.io.IOException;

import org.junit.Test;

import Exception.NoParentException;
import controller.DeleteUserController;

public class Delete {

	@Test
	public void test() throws NoParentException, IOException {
		DeleteUserController lDeleteUserController=new DeleteUserController();
		lDeleteUserController.deleteUser("Mark Turner");
		lDeleteUserController.deleteUser("Zoe Foster");
		lDeleteUserController.deleteUser("Ben Turner");
		lDeleteUserController.deleteUser("Mark T");
		lDeleteUserController.deleteUser("Zoe F");
		lDeleteUserController.deleteUser("Ben T");
	}

}
