package helpers;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class blurBackground extends JPanel{
	
	public blurBackground() {
		setOpaque(false);
		setBackground(new Color(0, 0, 0, 120));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
		
	}
}
