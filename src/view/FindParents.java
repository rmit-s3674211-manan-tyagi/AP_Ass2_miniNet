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

public class FindParents extends JFrame {
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindParents frame = new FindParents();
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
	public FindParents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Find Parents");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFindParents = new JLabel("Display Parents");
		lblFindParents.setBounds(156, 31, 95, 16);
		contentPane.add(lblFindParents);

		JLabel lblChildName = new JLabel("Child Name:");
		lblChildName.setBounds(6, 75, 77, 16);
		contentPane.add(lblChildName);

		textField = new JTextField();
		textField.setBounds(121, 70, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Parent 1");
		lblNewLabel.setBounds(6, 162, 77, 16);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		JLabel label = new JLabel("");
		label.setBounds(129, 162, 122, 16);
		contentPane.add(label);
		label.setVisible(false);

		JLabel lblParent = new JLabel("Parent 2");
		lblParent.setBounds(6, 190, 77, 16);
		contentPane.add(lblParent);
		lblParent.setVisible(false);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(129, 190, 122, 16);
		contentPane.add(label_2);
		label_2.setVisible(false);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("In FindParents- search button clicked");
				if(textField.getText().isEmpty())
				{
					System.out.println("Please enter Name to be searched");
					JOptionPane.showMessageDialog(null, "Please enter Name to be searched!");
					return;
				}
				String userName=textField.getText();
				AddNewUserController addNewUserController=new AddNewUserController();
				if(!addNewUserController.checkUserId(userName))
				{

					SearchUserController lSearchUserController=new SearchUserController();
					User user;
					try {
						user=lSearchUserController.searchUser(userName.toLowerCase());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "File not found");	
						return;
					}
					if(user!=null)
					{
						if(user.getAge()<16)
						{

							lblNewLabel.setVisible(true);
							label.setVisible(true);
							lblParent.setVisible(true);
							label_2.setVisible(true);
							ArrayList<String> parentsList=lSearchUserController.getParents(user.getName());
							label.setText(parentsList.get(0));
							label_2.setText(parentsList.get(1));
						}
						else
						{
							System.out.println("User is an adult");
							JOptionPane.showMessageDialog(null, "User is an adult!");
							return;
						}
					}
				}
				else
				{
					lblNewLabel.setVisible(false);
					label.setVisible(false);
					lblParent.setVisible(false);
					label_2.setVisible(false);
					JOptionPane.showMessageDialog(null, "Username not available!");
					return;
				}
			}
		});
		btnSearch.setBounds(115, 108, 117, 29);
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
