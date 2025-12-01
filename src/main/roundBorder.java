package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;

public class roundBorder implements Border{
	private int radius;
    private Color borderColor;
    private int thickness;

    public roundBorder(int radius, Color borderColor, int thickness) {
        this.radius = radius;
        this.borderColor = borderColor;
        this.thickness = thickness;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(thickness + 3, thickness + 3, thickness + 3, thickness + 3);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(thickness));

        g2.drawRoundRect(
            x + thickness/2,
            y + thickness/2,
            width - thickness,
            height - thickness,
            radius,
            radius
        );

        g2.dispose();
    }
}
