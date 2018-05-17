package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import Exception.NoParentException;
import Exception.NoSuchAgeException;
import Exception.NotToBeColleaguesException;
import Exception.NotToBeFriendsException;
import Exception.TooYoungException;
import controller.AddNewUserController;
import controller.CheckChildController;
import controller.MakeNewConnectionController;
import model.Adult;
import model.User;

public class FileReading {


	/**
	 * reads people.txt file and writes the values obtained to the database
	 * @author  Achal Vaish
	 * @throws NoSuchAgeException 
	 */
	public  boolean readPeopleFile() throws NoSuchAgeException
	{
		System.out.println("In FileReading class- readPeople() function");

		try {
			BufferedReader in = new BufferedReader(new FileReader("people.txt"));

			String line = null;
			while ((line = in.readLine()) != null) {
				String[] values = line.split(",");
				User adult=new Adult();
				int count=1;
				for (String str : values) {

					str=str.trim();

					if(count==1)
					{
						if(str!=null)
							adult.setName(str);
						else
						{
							JOptionPane.showMessageDialog(null,"Record can not be added.Missing Name.");
							continue;
						}
					}

					else if(count==2)
					{
						str=str.substring(1, str.length()-1);
						if(str.trim().length()>0)
							adult.setDisplay_picture(str);

					}
					else if(count==3)
					{
						str=str.substring(1, str.length()-1);
						if(str.trim().length()>0)
							adult.setStatus(str);

					}
					else if(count==4)
					{
						if(!str.equals("\"\""))
						{
							if(str.charAt(0) == 34 && str.charAt(str.length()-1) == 34)
								str=str.substring(1, str.length()-1);
							adult.setGender(str);
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Record can not be added.Missing information.");
							continue;
						}

					}
					else if(count==5)
					{

						if(!str.equals("\"\""))
						{

							if(str.charAt(0) == 34 && str.charAt(str.length()-1) == 34)
								str=str.substring(1, str.length()-1);
							adult.setAge(Integer.parseInt(str));
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Record can not be added.Missing age.");

							continue;
						}

					}
					else if(count==6)
					{
						if(!str.equals("\"\""))
						{
							if(str.charAt(0) == 34 && str.charAt(str.length()-1) == 34)
								str=str.substring(1, str.length()-1);
							adult.setState(str);
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Record can not be added. Missing state.");

							continue;
						}
					}
					count++;
				}

				AddNewUserController lAddNewUserContoller=new AddNewUserController();
				lAddNewUserContoller.createAdult(adult);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
			return false;
		} catch (NoSuchAgeException e) {
			throw new NoSuchAgeException();

		}
		return true;
	}

	/**
	 * reads relations.txt file and writes the values obtained to the database
	 * @author  Achal Vaish
	 */
	public  boolean readRelations() throws TooYoungException, NotToBeFriendsException, NotToBeColleaguesException
	{
		try {
			BufferedReader in = new BufferedReader(new FileReader("relations.txt"));
			System.out.println("relations.txt found");
			String line = null;
			while ((line = in.readLine()) != null) {
				String[] values = line.split(",");
				int count=1;
				String name = null;
				String name1 = null;
				String relation = null;
				for (String str : values) {
					str=str.trim();
					if(count==1)
					{
						name=str;
					}
					else if(count==2)
					{
						name1=str;
					}
					else if(count==3)
					{
						relation=str;
					}
					count++;
				}

				MakeNewConnectionController makeNewConnectionController=new MakeNewConnectionController();
				makeNewConnectionController.createConnection(name, name1, relation);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		} 
		return true;
	}

	/**
	 * checks and deletes the parent information for non-Adult users that do not correspond to the relations specified in the relations file
	 * @author  Achal Vaish
	 * @throws NoParentException 
	 */
	public void checkYoung() throws NoParentException
	{
		CheckChildController checkChildController=new CheckChildController();
		checkChildController.checkYoung();
	}
}
