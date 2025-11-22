package helpers;

import java.awt.Image;

import javax.swing.ImageIcon;

public class iconHelper {

	public static ImageIcon load(String path, int sizeX, int sizeY) {
        ImageIcon icon = new ImageIcon(iconHelper.class.getResource(path));
        Image scaled = icon.getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

}
