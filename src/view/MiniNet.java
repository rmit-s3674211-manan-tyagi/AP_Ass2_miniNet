package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Exception.NotToBeColleaguesException;
import Exception.NotToBeFriendsException;
import Exception.TooYoungException;
import helper.CreateTableDAO;
import helper.FileReading;

public class MiniNet extends JFrame {

	private JPanel contentPane;
	 

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File tmpDir = new File("Mini.db");
					boolean checkDB=tmpDir.exists();
					File tmpFile = new File("People.txt");
					boolean checktmpFile=tmpFile.exists();
					if(checktmpFile==true&&checkDB==true)
					{
						tmpDir.delete();
						checkDB=false;
					}
					if(checkDB==false)
					{
					System.out.println("DataBase Does not exist");
					CreateTableDAO lCreateTableDAO=new CreateTableDAO();
					lCreateTableDAO.createTable();

					FileReading lFileReading=new FileReading();
					boolean readFile=lFileReading.readPeopleFile();
					if(readFile==false)
					{
						JOptionPane.showMessageDialog(null, "People.txt not available!");	
						System.out.println("People.txt not available");
						
					}
					boolean readFileRelations=false;
					try
					{

					 readFileRelations=lFileReading.readRelations();
					}
					catch(TooYoungException | NotToBeFriendsException | NotToBeColleaguesException e)
					{
						System.out.println("Exception caught in readRelations()"+e.getMessage());
						JOptionPane.showMessageDialog(null, e.getMessage());	
						
					}
					lFileReading.checkYoung();
					}
					else
					{
						System.out.println("Database exists.");

					}
					MiniNet frame = new MiniNet();
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
	public MiniNet() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 349);
		setTitle("MiniNet: The mini social network");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MiniNet");
		lblNewLabel.setBounds(199, 32, 80, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnAddNewUser = new JButton("Add New User");
		btnAddNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add User Button Clicked");
				dispose();
				AddNewUser lAddNewUser=new AddNewUser();
				lAddNewUser.setVisible(true);
			}
		});
		btnAddNewUser.setBounds(29, 78, 117, 29);
		contentPane.add(btnAddNewUser);
		
		JButton btnViewProfile = new JButton("View Profile");
		btnViewProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("View Profile button clicked");
				dispose();
				ViewUser lViewUser=new ViewUser();
				lViewUser.setVisible(true);
			}
		});
		btnViewProfile.setBounds(29, 146, 117, 29);
		contentPane.add(btnViewProfile);
		
		JButton btnFindParents = new JButton("Display Parents");
		btnFindParents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FindParents lFindParents=new FindParents();
				lFindParents.setVisible(true);
				
			}
		});
		btnFindParents.setBounds(253, 78, 127, 32);
		contentPane.add(btnFindParents);
		
		JButton btnNewButton = new JButton("Display Children");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FindChildren lFindChildren=new FindChildren();
				lFindChildren.setVisible(true);
			}
		});
		btnNewButton.setBounds(253, 146, 127, 29);
		contentPane.add(btnNewButton);
		
		JButton btnConnectPeople = new JButton("Connect People");
		btnConnectPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Connect People button clicked");
				dispose();
				AddFriend lAddFriend=new AddFriend();
				lAddFriend.setVisible(true);
			}
		});
		btnConnectPeople.setBounds(29, 210, 132, 29);
		contentPane.add(btnConnectPeople);
		
		JButton btnCheckConnection = new JButton("Check Connection");
		btnCheckConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CheckConnection lCheckConnection=new CheckConnection();
				lCheckConnection.setVisible(true);
			}
		});
		btnCheckConnection.setBounds(243, 210, 137, 29);
		contentPane.add(btnCheckConnection);
		
		JButton btnNewButton_1 = new JButton("Delete User");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DeleteUser lDeleteUser=new DeleteUser();
				lDeleteUser.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(149, 270, 117, 29);
		contentPane.add(btnNewButton_1);
	}
}
