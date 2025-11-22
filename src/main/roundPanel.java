package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class roundPanel extends JPanel {

    public enum RoundType {
        FULL,
        RIGHT,
        TOP_RIGHT
    }

    private int cornerRadius = 20;
    private RoundType roundType = RoundType.FULL;

    public roundPanel() {
        setOpaque(false); 
    }

    public roundPanel(int radius) {
        this.cornerRadius = radius;
        setOpaque(false);
    }

    public void setRoundType(RoundType type) {
        this.roundType = type;
        repaint();
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int r = cornerRadius;
        

        g2.setColor(getBackground());
        

        switch (roundType) {
            case FULL:
                g2.fillRoundRect(0, 0, w, h, r, r);
                break;

            case RIGHT:
                g2.fillRoundRect(0, 0, w, h, r * 2, r * 2);
                g2.fillRect(0, 0, r, h);
                break;

            case TOP_RIGHT:    
                Path2D path = new Path2D.Float();
                path.moveTo(0, 0);          
                path.lineTo(w - r, 0);     
                path.quadTo(w, 0, w, r);    
                path.lineTo(w, h);         
                path.lineTo(0, h);       
                path.closePath();          
                g2.fill(path);
                break;
        }

        g2.dispose();
    }
}
