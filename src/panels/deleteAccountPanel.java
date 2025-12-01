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
import javax.swing.SwingUtilities;

import dao.productDao;
import dao.userDao;
import main.mainFrame;

public class deleteAccountPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int userId;

	/**
	 * Create the panel.
	 */
	public deleteAccountPanel(int id) {
		this.userId = id;

        setLayout(null);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(200,200,200), 2));

        JLabel title = new JLabel("DELETE ACCOUNT?");
        title.setFont(new Font("Poppins SemiBold", Font.PLAIN, 24));
        title.setBounds(40, 40, 300, 40);
        add(title);

        JLabel msg = new JLabel("Are you sure you want to delete this account?");
        msg.setFont(new Font("Poppins", Font.PLAIN, 14));
        msg.setBounds(40, 100, 350, 30);
        add(msg);

        JButton btnYes = new JButton("YES");
        btnYes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		userDao dao = new userDao();
	            dao.deleteUser(userId);

	            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(deleteAccountPanel.this);
	            mainFrame frame = (mainFrame) parent;
	            frame.popup.closePanel(parent);

	            dao.refreshUserTable(frame.getstaffaccPanel().getTable());

	            JOptionPane.showMessageDialog(null, "Account deleted successfully!");
        	}
        });
        btnYes.setFont(new Font("Poppins", Font.PLAIN, 18));
        btnYes.setBounds(60, 170, 120, 45);	        	       
        add(btnYes);

        JButton btnNo = new JButton("NO");
        btnNo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
	            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(deleteAccountPanel.this);
	            mainFrame frame = (mainFrame) parent;
	            frame.popup.closePanel(parent);
        	}
        });
        btnNo.setFont(new Font("Poppins", Font.PLAIN, 18));
        btnNo.setBounds(210, 170, 120, 45);
        add(btnNo);
	}

}
