package helpers;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class buttonHelper {

	  public static void apply(JButton btn, String iconPath,int x, int y) {
	        btn.setOpaque(false);
	        btn.setFocusPainted(false);
	        btn.setContentAreaFilled(false);
	        btn.setBorderPainted(false);

	        btn.setHorizontalAlignment(SwingConstants.LEFT);
	        btn.setForeground(Color.WHITE);
	        btn.setFont(new Font("Poppins Medium", Font.PLAIN, 13));

	        // Load icon
	        btn.setIcon(helpers.iconHelper.load(iconPath, x, y));
	    }
}
