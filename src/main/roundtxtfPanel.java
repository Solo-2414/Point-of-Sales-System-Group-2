package main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JTextField;

public class roundtxtfPanel extends roundPanel {
	private int cornerRadius = 20;
    private JTextField textField;

    public roundtxtfPanel(int radius) {
        super(radius);
        setRoundType(RoundType.FULL);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 20, 5, 10));
        textField.setFont(new java.awt.Font("Poppins", java.awt.Font.PLAIN, 14));

        add(textField, BorderLayout.CENTER);
    }

    public JTextField getTextField() {
        return textField;
    }
    
    public void roundPanels() {
    	
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int r = cornerRadius;

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width - 1, height - 1, r, r);

        g2.setColor(new Color(58, 111, 67));  
        g2.setStroke(new BasicStroke(2));      
        g2.drawRoundRect(1, 1, width - 3, height - 3, r, r);

        g2.dispose();
    }
}
