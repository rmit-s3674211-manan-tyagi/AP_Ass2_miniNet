package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AddNewUserController;
import controller.SearchUserController;
import model.User;

public class FindChildren extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindChildren frame = new FindChildren();
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
	public FindChildren() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Find Children");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFindChildren = new JLabel("Display Children");
		lblFindChildren.setBounds(144, 39, 95, 16);
		contentPane.add(lblFindChildren);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 72, 61, 16);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(79, 67, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblListOfChildren = new JLabel("List of children");
		lblListOfChildren.setBounds(6, 144, 117, 16);
		contentPane.add(lblListOfChildren);
		lblListOfChildren.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(132, 144, 284, 16);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(textField.getText().isEmpty())
				{
					System.out.println("Name not entered");	
					JOptionPane.showMessageDialog(null, "Please enter a valid name.");
					return;
				}
				String userName=textField.getText();
				AddNewUserController addNewUserController=new AddNewUserController();
				if(!addNewUserController.checkUserId(userName))
				{
					SearchUserController searchUserController=new SearchUserController();
					User user;
					try {
						user=searchUserController.searchUser(userName.toLowerCase());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "File not found");	
						return;
					}
					if(user!=null)
					{
						if(user.getAge()>16)
						{
							lblListOfChildren.setVisible(true);
							lblNewLabel.setVisible(true);
							String list;
							ArrayList<String> childList=searchUserController.getChildren(user.getName());
							//System.out.println(childList);
							lblNewLabel.setText(childList.toString());
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "User is not Adult");
							return;
						}
					}
				}
				else
				{
					lblListOfChildren.setVisible(false);
					lblNewLabel.setVisible(false);
					JOptionPane.showMessageDialog(null, "Username does not exist.");
					return;
				}
			}
		});
		btnSearch.setBounds(122, 105, 117, 29);
		contentPane.add(btnSearch);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MiniNet lMiniNet=new MiniNet();
				lMiniNet.setVisible(true);
			}
		});
		btnMenu.setBounds(327, 6, 117, 29);
		contentPane.add(btnMenu);
		
		
	}

}
