package controller;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.userDao;
import helpers.session;
import main.mainFrame;
import model.user;

public class loginController {
	private userDao dao = new userDao();
	
	public void loginF(JFrame loginFrame, JTextField usernameF, JPasswordField passwordF ) {
		String user = usernameF.getText().trim();
		String pass = String.valueOf(passwordF.getPassword());
		
		if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(loginFrame, "Please enter username and password!");
            return;
        }
		
		user loggedUser = dao.validateLogin(user, pass);
		
		if (loggedUser == null) {
		    JOptionPane.showMessageDialog(null, "Invalid username or password!");
		    return;
		} 

		// save session
		session.setUser(loggedUser);
		mainFrame mf = new mainFrame();
		loginFrame.dispose();
		if(!session.currentUser.getRole().equals("admin")) {
//			CardLayout layout = (CardLayout) mf.mainPanel.getLayout();
//		    layout.show(mf.mainPanel, "sales"); // show transaction panel
		}
		
		mf.setVisible(true);
		
		
	}

   
}
    
