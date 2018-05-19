package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Exception.NoParentException;
import controller.AddNewUserController;
import controller.DeleteUserController;
import controller.SearchUserController;
import model.User;

public class DeleteUser extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser();
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
	public DeleteUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Delete User");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDeleteUser = new JLabel("Delete User");
		lblDeleteUser.setBounds(123, 37, 111, 16);
		contentPane.add(lblDeleteUser);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 82, 61, 16);
		contentPane.add(lblName);

		textField = new JTextField();
		textField.setBounds(79, 77, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName=textField.getText();
				AddNewUserController lAddNewUserController=new AddNewUserController();
				if(!lAddNewUserController.checkUserId(userName))
				{
					SearchUserController lSearchUserController=new SearchUserController();
					User user;
					DeleteUserController lDeleteUserController=new DeleteUserController();
					try {
						user=lSearchUserController.searchUser(userName.toLowerCase());
					} catch (IOException e2) {
						System.out.println("File not found");
						JOptionPane.showMessageDialog(null, "File not found");	
						return;
					}
					if(user!=null)
					{

						try {
							lDeleteUserController.deleteUser(user.getName());
						} catch (NoParentException e1) {
							System.out.println("NoParentException- "+e1.getMessage());
							JOptionPane.showMessageDialog(null, e1.getMessage());
							return;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("User Succesfully Deleted");
						JOptionPane.showMessageDialog(null, "User Succesfully Deleted");	

					}
				}
				else
				{
					System.out.println("User doesn't exists");
					JOptionPane.showMessageDialog(null, "User doesn't exist");

				}
			}
		});
		btnDeleteUser.setBounds(123, 134, 117, 29);
		contentPane.add(btnDeleteUser);
		
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
