package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AddNewUserController;
import controller.MakeNewConnectionController;

public class CheckConnection extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckConnection frame = new CheckConnection();
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
	public CheckConnection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Check Connection");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCheckConnection = new JLabel("Check Connection");
		lblCheckConnection.setBounds(137, 30, 120, 16);
		contentPane.add(lblCheckConnection);
		
		JLabel lblName = new JLabel("Name 1:");
		lblName.setBounds(6, 75, 61, 16);
		contentPane.add(lblName);
		
		JLabel lblName_1 = new JLabel("Name 2:");
		lblName_1.setBounds(6, 110, 61, 16);
		contentPane.add(lblName_1);
		
		textField = new JTextField();
		textField.setBounds(79, 70, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(79, 105, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Verify Relation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty())
				{
					System.out.println("Person 1 empty");
					JOptionPane.showMessageDialog(null, "Person 1 cannot be empty");
					return;
				}
				if(textField_1.getText().isEmpty())
				{
					System.out.println("Person 2 empty");
					JOptionPane.showMessageDialog(null, "Person 2 cannot be empty!");
					return;
				}
				MakeNewConnectionController makeNewConnectionController=new MakeNewConnectionController();
				String userName=textField.getText();
				String userName1=textField_1.getText();
				AddNewUserController addNewUserController=new AddNewUserController();
				if(!addNewUserController.checkUserId(userName))
				{
					if(!addNewUserController.checkUserId(userName1))
					{
						if(!makeNewConnectionController.checkConnection(userName,userName1))
						{
							System.out.println("Not Connected");

							JOptionPane.showMessageDialog(null, "Not Connected!");
							return;
						}
						else
						{

							String relation=makeNewConnectionController.getConnection(userName,userName1);
							System.out.println("Connected as "+relation);
							JOptionPane.showMessageDialog(null, "Connected as "+relation );

						}
					}
					else
					{
						System.out.println("Username for User 2 not Correct!");
						JOptionPane.showMessageDialog(null, "Username for User 2 not Correct!");	
						return;

					}
				}
				else
				{
					System.out.println("Username for User 1 not Correct!");
					JOptionPane.showMessageDialog(null, "Username for User 1 not Correct!");	
					return;
				}
				
				
			}
		});
		btnNewButton.setBounds(137, 143, 146, 29);
		contentPane.add(btnNewButton);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MiniNet lMiniNet=new MiniNet();
				lMiniNet.setVisible(true);
			}
		});
		btnMenu.setBounds(311, 17, 117, 29);
		contentPane.add(btnMenu);
	}

}
