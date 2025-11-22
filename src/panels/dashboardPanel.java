package panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class dashboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public dashboardPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Test");
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 155, 57);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Test");
		lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(961, 568, 155, 57);
		add(lblNewLabel_1);

	}

}
