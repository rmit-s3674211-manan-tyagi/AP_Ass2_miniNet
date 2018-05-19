package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Exception.NoAvailableException;
import Exception.NoSuchAgeException;
import Exception.NotToBeCoupledException;
import controller.AddNewUserController;
import model.Adult;
import model.Child;
import model.YoungChild;


public class AddNewUser extends JFrame {

	private static int count;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	static AddNewUser frame ;
	private JTextField textField_4;
	private JTextField textField_5;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddNewUser();
					count=0;
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddNewUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 450);
		setTitle("Add new user");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddNewUser = new JLabel("Add New User");
		lblAddNewUser.setBounds(177, 25, 123, 16);
		contentPane.add(lblAddNewUser);

		textField = new JTextField();
		textField.setBounds(177, 73, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblEnterName = new JLabel("Name");
		lblEnterName.setBounds(20, 78, 84, 16);
		contentPane.add(lblEnterName);

		JLabel lblEnterAge = new JLabel("Age");
		lblEnterAge.setBounds(20, 116, 84, 16);
		contentPane.add(lblEnterAge);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(177, 111, 130, 26);
		contentPane.add(textField_1);

		JLabel lblEnterStatus = new JLabel("Status");
		lblEnterStatus.setBounds(20, 149, 84, 16);
		contentPane.add(lblEnterStatus);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(177, 144, 130, 26);
		contentPane.add(textField_2);

		JLabel lblEnterImage = new JLabel("Image");
		lblEnterImage.setBounds(20, 182, 84, 16);
		contentPane.add(lblEnterImage);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(177, 177, 130, 26);
		contentPane.add(textField_3);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(20, 222, 61, 16);
		contentPane.add(lblGender);

		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setSelected(true);
		rdbtnMale.setActionCommand( rdbtnMale.getText() );
		rdbtnMale.setBounds(138, 217, 84, 23);
		contentPane.add(rdbtnMale);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(239, 217, 141, 23);
		rdbtnFemale.setActionCommand( rdbtnFemale.getText() );

		contentPane.add(rdbtnFemale);

		ButtonGroup btn_group = new ButtonGroup();
		btn_group.add(rdbtnFemale);
		btn_group.add(rdbtnMale);
		JLabel lblNewLabel = new JLabel("State");
		lblNewLabel.setBounds(20, 266, 61, 16);
		contentPane.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ACT", "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(165, 262, 84, 27);
		contentPane.add(comboBox);


		JLabel lblParent = new JLabel("Parent 1");
		lblParent.setBounds(20, 299, 84, 16);
		lblParent.setVisible(false);

		contentPane.add(lblParent);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(177, 294, 130, 26);
		textField_4.setVisible(false);
		contentPane.add(textField_4);

		JLabel lblParent_1 = new JLabel("Parent 2");
		lblParent_1.setBounds(20, 335, 84, 16);
		lblParent_1.setVisible(false);
		contentPane.add(lblParent_1);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(177, 330, 130, 26);
		textField_5.setVisible(false);

		contentPane.add(textField_5);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add User Button Clicked");

				String name=null;
				name=textField.getText();
				if(textField.getText().isEmpty())
				{
					System.out.println("Name Empty");
					JOptionPane.showMessageDialog(null, "Name can't be Empty!");
					return;
				}
				if(textField_1.getText().isEmpty())
				{
					System.out.println("Age Empty");
					JOptionPane.showMessageDialog(null, "Age can't be Empty!");
					return;
				}
				int age;
				try
				{
					age=Integer.parseInt(textField_1.getText());
				}
				catch(Exception e1)
				{
					System.out.println("Characters in Age");
					JOptionPane.showMessageDialog(null, "Please Enter only Numbers in age");
					return;
				}
				String status=textField_2.getText();
				String image=textField_3.getText();
				String gender=null;

				gender=btn_group.getSelection().getActionCommand();

				String state=comboBox.getSelectedItem().toString();
				//System.out.println(state);
				AddNewUserController addNewUserController=new AddNewUserController();
				if(!addNewUserController.checkUserId(name))
				{
					System.out.println("Username already exists!Username is"+name );
					JOptionPane.showMessageDialog(null, "Username Already Exists!");
					return;
				}
				if(age>16||age<0)
				{

					Adult adult=new Adult(name,age,state,gender);
					if(!textField_2.getText().isEmpty())
						adult.setStatus(status);
					if(!textField_3.getText().isEmpty())
						adult.setDisplay_picture(image);
					System.out.println("Calling AddNewUserController for function createAdult" );
					boolean addUser;
					try {
						addUser = addNewUserController.createAdult(adult);
					} catch (NoSuchAgeException e1) {
						System.out.println("NoSuchAgeException-Age not correct. Age entered is"+age );
						JOptionPane.showMessageDialog(null, e1.getMessage());		
						return ;
					} catch (FileNotFoundException e1) {
						System.out.println("FileNotFoundException-Image not Found" );
						JOptionPane.showMessageDialog(null, "Image not Found!");		
						return ;
					} catch (IOException e1) {
						System.out.println("IOException-File not Found" );
						JOptionPane.showMessageDialog(null, "File Not found");		
						return;
					}

					if(addUser==true)
					{
						JOptionPane.showMessageDialog(null, "User Succesfully Added");
						System.out.println("User has been succesfully added" );

						dispose();
						MiniNet lMiniNet=new MiniNet();
						lMiniNet.setVisible(true);
					}

				}
				else	 if(age<16)
				{
					if(!textField_4.isVisible())
					{
						JOptionPane.showMessageDialog(null, "User is not an Adult. Please Enter Details of Parent!");
						System.out.println("User is not an adult!" );
					}
					if(textField_4.isVisible())
					{
						count++;
					}
					lblParent.setVisible(true);
					textField_4.setVisible(true);
					lblParent_1.setVisible(true);
					textField_5.setVisible(true);
					String parent1=textField_4.getText();
					String parent2=textField_5.getText();

					if((textField_4.getText().isEmpty()||textField_5.getText().isEmpty())&&count!=0)
					{
						JOptionPane.showMessageDialog(null, "Parent Details Cannot be Blank. Please Enter Details of Parent!");
						return;
					}

					if((age<16&&age>3))
					{
						if(addNewUserController.checkUserId(parent1)&&count!=0)
						{
							JOptionPane.showMessageDialog(null, "Parent1 does not exist. Please Enter Details of Parent!");
							System.out.println("Parent 1 does not exist" );

							return;
						}
						if(addNewUserController.checkUserId(parent2)&&count!=0)
						{
							JOptionPane.showMessageDialog(null, "Parent2 does not exist. Please Enter Details of Parent!");
							System.out.println("Parent 2 does not exist" );

							return;
						}
						if(count==0)
							return;
						count++;
						Child lChild=new Child(name,age,state,gender,parent1,parent2);
						if(!textField_2.getText().isEmpty())
							lChild.setStatus(status);
						if(!textField_3.getText().isEmpty())
							lChild.setDisplay_picture(image);
						boolean addUser;
						try {
							addUser = addNewUserController.createChild(lChild);
						} catch (NoAvailableException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());	
							System.out.println("NoAvailableException" +e1.getMessage());
							return ;
						} catch (NoSuchAgeException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());		
							System.out.println("NoSuchAgeException" +e1.getMessage());
							return ;
						} catch (NotToBeCoupledException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());		
							System.out.println("NotToBeCoupledException" +e1.getMessage());
							return;
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "File Not found");	
							System.out.println("File Not found");
							return;
						}
						if(addUser==true)
						{
							JOptionPane.showMessageDialog(null, "User Succesfully Added");
							System.out.println("User Succesfully Added");
							dispose();
							MiniNet lMiniNet=new MiniNet();
							lMiniNet.setVisible(true);
						}

					}
					else
					{
						if(addNewUserController.checkUserId(parent1)&&count!=0)
						{
							JOptionPane.showMessageDialog(null, "Parent 1 does not exist. Please Enter Details of Parent.");
							System.out.println("Parent1 does not exist");

							return;
						}
						if(addNewUserController.checkUserId(parent2)&&count!=0)
						{
							JOptionPane.showMessageDialog(null, "Parent 2 does not exist. Please Enter Details of Parent.");
							System.out.println("Parent2 does not exist");
							return;
						}
						if(count==0)
							return;
						count++;
						YoungChild lYoungChild=new YoungChild(name,age,state,gender,parent1,parent2);
						if(!textField_2.getText().isEmpty())
							lYoungChild.setStatus(status);
						if(!textField_3.getText().isEmpty())
							lYoungChild.setDisplay_picture(image);
						boolean addUser = false;
						try {
							addUser = addNewUserController.createYoungChild(lYoungChild);
						} catch (NoAvailableException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());		
							System.out.println("NoAvailableException" +e1.getMessage());

							return ;
						} catch (NoSuchAgeException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());	
							System.out.println("NoSuchAgeException" +e1.getMessage());
							return ;
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "File Not found");
							System.out.println("IOException-File Not found");
							return;
						}
						if(addUser==true)
						{
							JOptionPane.showMessageDialog(null, "User Succesfully Added");
							System.out.println("User Succesfully Added");
							dispose();
							MiniNet lMiniNet=new MiniNet();
							lMiniNet.setVisible(true);
						}

					}

				}
			}
		});
		btnSubmit.setBounds(190, 353, 117, 29);
		contentPane.add(btnSubmit);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MiniNet lMiniNet=new MiniNet();
				lMiniNet.setVisible(true);
			}
		});
		btnMenu.setBounds(384, 6, 117, 29);
		contentPane.add(btnMenu);


	}
}
