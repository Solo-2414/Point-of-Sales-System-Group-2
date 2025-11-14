package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel signPanel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame frame = new loginFrame();
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
	public loginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		signPanel = new JPanel();
		signPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(signPanel);
		signPanel.setLayout(new CardLayout(0, 0));
		
		JPanel signinPanel = new JPanel();
		signPanel.add(signinPanel, "name_5270677404800");
		signinPanel.setLayout(null);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new mainFrame().setVisible(true);
			}
		});
		btnSignIn.setBounds(169, 174, 89, 23);
		signinPanel.add(btnSignIn);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(169, 133, 86, 20);
		signinPanel.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(159, 108, 77, 14);
		signinPanel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(169, 77, 86, 20);
		signinPanel.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(159, 52, 59, 14);
		signinPanel.add(lblNewLabel);
		
		JPanel signupPanel = new JPanel();
		signPanel.add(signupPanel, "name_5197912345700");
		signupPanel.setLayout(null);
		
		JButton btnSignIn_1 = new JButton("Sign in");
		btnSignIn_1.setBounds(172, 166, 89, 23);
		signupPanel.add(btnSignIn_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(172, 125, 86, 20);
		signupPanel.add(textField_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setBounds(162, 100, 77, 14);
		signupPanel.add(lblNewLabel_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(172, 69, 86, 20);
		signupPanel.add(textField_3);
		
		JLabel lblNewLabel_2 = new JLabel("Username:");
		lblNewLabel_2.setBounds(162, 44, 59, 14);
		signupPanel.add(lblNewLabel_2);

	}

}
