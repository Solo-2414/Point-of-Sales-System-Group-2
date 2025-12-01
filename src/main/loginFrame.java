package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.loginController;
import dao.userDao;
import helpers.buttonHelper;
import helpers.inputHelper;
import helpers.session;
import model.user;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class loginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel signPanel;
	private JTextField username;
	private JPasswordField password;

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
		setBounds(100, 100, 1000, 550);
		setLocationRelativeTo(null); 
		setResizable(false);
		signPanel = new JPanel();
		signPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(signPanel);
		signPanel.setLayout(new CardLayout(0, 0));
		
		JPanel signinPanel = new JPanel();
		signPanel.add(signinPanel, "SignIn");
		signinPanel.setLayout(null);
						
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(281, 78, 100, 28);
		lblLogin.setForeground(new Color(58, 111, 67));
		lblLogin.setFont(new Font("Poppins", Font.BOLD, 24));
		signinPanel.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(289, 149, 70, 14);
		lblUsername.setForeground(Color.GRAY);
		lblUsername.setFont(new Font("Poppins", Font.PLAIN, 13));
		signinPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(289, 212, 70, 14);
		lblPassword.setForeground(new Color(128, 128, 128));
		lblPassword.setFont(new Font("Poppins", Font.PLAIN, 13));
		signinPanel.add(lblPassword);
		
		roundPanel usernamePanel = new roundPanel(20);
		usernamePanel.setBounds(281, 147, 395, 39);
		usernamePanel.setLayout(null);
		usernamePanel.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		usernamePanel.setBackground(Color.WHITE); 
		signinPanel.add(usernamePanel);
		
		username = new JTextField();
		username.setBounds(10, 14, 375, 19);
		username.setBorder(null);;
		usernamePanel.add(username);
		inputHelper.hideLabelOnType(username, lblUsername);
		
		
		roundPanel passwordRoundPanel = new roundPanel(20);
		passwordRoundPanel.setBounds(281, 210, 395, 39);
		passwordRoundPanel.setLayout(null);
		passwordRoundPanel.setBackground(Color.WHITE);
		passwordRoundPanel.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		signinPanel.add(passwordRoundPanel);
			
		password = new JPasswordField();
		password.setBounds(10, 17, 344, 19);
		password.setBorder(null);
		password.setEchoChar('•');
		passwordRoundPanel.add(password);
		inputHelper.hideLabelOnType(password, lblPassword);

		
		JButton btnshow = new JButton("");
		btnshow.setBounds(345, -1, 40, 39);
		passwordRoundPanel.add(btnshow);
		buttonHelper.apply(btnshow, "/img/eye-slash-regular-full.png", 20, 20);
		passwordRoundPanel.setComponentZOrder(btnshow, 0);
		btnshow.addActionListener(new ActionListener() {
			boolean isShowing = false;
			
			public void actionPerformed(ActionEvent e) {
				if (isShowing) {
					password.setEchoChar('•');
					buttonHelper.apply(btnshow, "/img/eye-slash-regular-full.png", 20, 20);
				}else {
					password.setEchoChar((char)0);
					buttonHelper.apply(btnshow, "/img/eye-regular-full.png", 20, 20);
				}
				
				isShowing = !isShowing;
			}
		});		
		
		roundPanel loginButtonPanel = new roundPanel(20);
		loginButtonPanel.setBounds(281, 309, 398, 39);
		loginButtonPanel.setBackground(new Color(99, 173, 112));
		loginButtonPanel.setLayout(null);
		signinPanel.add(loginButtonPanel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBounds(0, 0, 398, 39);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setFont(new Font("Poppins", Font.BOLD, 20));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginController ctrl = new loginController();
				ctrl.loginF(loginFrame.this, username, password);
			}
		});
		loginButtonPanel.add(btnLogin);
		
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.setFont(new Font("Poppins", Font.PLAIN, 13));
		btnForgotPassword.setBounds(532, 253, 158, 23);
		buttonHelper.apply(btnForgotPassword, "", 1, 1);
		btnForgotPassword.setForeground(Color.BLACK);
		signinPanel.add(btnForgotPassword);
		
		
		
		
		


	}
}
