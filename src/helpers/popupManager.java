package helpers;

import javax.swing.JFrame;
import javax.swing.JPanel;

import panels.addProductPanel;
import panels.editProductPanel;

public class popupManager {
	
	private JPanel overlay;
	
	public void showPanel(JFrame parent, JPanel panel, int w, int h) {
		
		overlay = new blurBackground();
	    overlay.setLayout(null);

	    overlay.addMouseListener(new java.awt.event.MouseAdapter() {}); 
	    overlay.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {});

	    parent.setGlassPane(overlay);
	    overlay.setVisible(true);
        
        panel.setBounds((parent.getWidth() - w) / 2, (parent.getHeight() - h) / 2, w, h);
        
        overlay.add(panel);

	}	
	
	public void closePanel(JFrame parent) {
		overlay.setVisible(false);
        parent.repaint();
	}
}
