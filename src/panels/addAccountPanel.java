package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.productDao;
import dao.userDao;
import helpers.inputHelper;
import main.mainFrame;
import main.roundBorder;
import main.roundPanel;
import model.product;
import model.user;

import javax.swing.JComboBox;

public class addAccountPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField Password;

	/**
	 * Create the panel.
	 */
	public addAccountPanel() {
		    setLayout(null);
	        setBackground(Color.WHITE);
	        setBorder(BorderFactory.createLineBorder(new Color(200,200,200), 2));

	        JLabel title = new JLabel("ADD ACCOUNT");
	        title.setFont(new Font("Poppins SemiBold", Font.PLAIN, 24));
	        title.setBounds(40, 40, 300, 40);
	        add(title);
	        
	        JLabel lblAccID = new JLabel("ACCOUNT ID");
			lblAccID.setOpaque(true);
			lblAccID.setBackground(Color.WHITE);
			lblAccID.setForeground(new Color(0, 0, 0));
			lblAccID.setFont(new Font("Inter 24pt Light", Font.PLAIN, 13));
			lblAccID.setBounds(41, 104, 78, 14);
			add(lblAccID);
	        
			
			roundPanel accountID = new roundPanel(20);
			accountID.setBounds(26, 111, 275, 39);
			accountID.setLayout(null);
			accountID.setBackground(new Color(238, 238, 238));
			accountID.setBorder(new roundBorder(20, new Color(107,147,114), 2));
			add(accountID);
			
			JTextField accID = new JTextField();
			accID.setFont(new Font("Poppins", Font.PLAIN, 13));
			accID.setEditable(false);
			accID.setText("The ID is generated automatically!");
			accID.setBounds(10, 11, 255, 22);
			accID.setBorder(null);
			accountID.add(accID);
			inputHelper.hideLabelOnType(accID, lblAccID);
			
			JLabel lblName = new JLabel("NAME");
			lblName.setFont(new Font("Inter 24pt Light", Font.PLAIN, 13));
			lblName.setOpaque(true);
			lblName.setBackground(Color.WHITE);
			lblName.setBounds(373, 104, 37, 14);
			add(lblName);
			
			roundPanel name = new roundPanel(20);
			name.setLayout(null);
			name.setBackground(Color.WHITE);
			name.setBounds(358, 111, 275, 39);
			name.setBorder(new roundBorder(20, new Color(107,147,114), 2));
			add(name);
			
			JTextField accName = new JTextField();
			accName.setFont(new Font("Poppins", Font.PLAIN, 13));
			accName.setBorder(null);
			accName.setBounds(10, 11, 255, 22);
			name.add(accName);
			inputHelper.hideLabelOnType(accName, lblName);
			
			JLabel lblName_1 = new JLabel("PASSWORD");
	        lblName_1.setOpaque(true);
	        lblName_1.setFont(new Font("Inter 24pt Light", Font.PLAIN, 13));
	        lblName_1.setBackground(Color.WHITE);
	        lblName_1.setBounds(41, 182, 71, 14);
	        add(lblName_1);
	        
	        roundPanel password = new roundPanel(20);
	        password.setLayout(null);
	        password.setBorder(new roundBorder(20, new Color(107,147,114), 2));
	        password.setBackground(Color.WHITE);
	        password.setBounds(26, 189, 275, 39);
	        add(password);
	        
	        Password = new JTextField();
	        Password.setFont(new Font("Poppins", Font.PLAIN, 13));
	        Password.setBorder(null);
	        Password.setBounds(10, 11, 255, 22);
	        password.add(Password);
	        
	        
	        JComboBox power = new JComboBox();
	        power.addItem("ADMIN");
	        power.addItem("USER");
	        power.setBounds(358, 195, 150, 35);
	        power.setFocusable(false);   
	        power.setLightWeightPopupEnabled(false);
	        add(power);
	        setComponentZOrder(power, 0);
	        
	        
	        
	        JButton btnYes = new JButton("SAVE ACCOUNT");
	        btnYes.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
//	        		
					try {
						user u = new user();
			            u.setUsername(accName.getText());  
			            u.setPassword(Password.getText());
			            u.setRole(power.getSelectedItem().toString());
			
			            userDao dao = new userDao();
			            
			            if (dao.addUser(u)) {
			                JOptionPane.showMessageDialog(null, "Account added!");
			                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(addAccountPanel.this);
			                mainFrame frame = (mainFrame) parent;
			                frame.popup.closePanel(parent);
					       
					        dao.refreshUserTable(frame.getstaffaccPanel().getTable());
			            } else {
			                JOptionPane.showMessageDialog(null, "Error adding product.");
			            }
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Invalid input. Please check fields.");
					}
	        	}
	        });
	        btnYes.setFont(new Font("Poppins", Font.PLAIN, 18));
	        btnYes.setBounds(457, 379, 176, 45);	        	       
	        add(btnYes);

	        JButton btnNo = new JButton("CANCEL");
	        btnNo.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
		            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(addAccountPanel.this);
			        ((mainFrame) parent).popup.closePanel(parent);
	        	}
	        });
	        btnNo.setFont(new Font("Poppins", Font.PLAIN, 18));
	        btnNo.setBounds(243, 379, 176, 45);
	        add(btnNo);
	        
	}
}
