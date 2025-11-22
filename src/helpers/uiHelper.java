package helpers;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;

public class uiHelper {

    public static void addGlobalMouseListener(Component comp, MouseAdapter listener) {
        comp.addMouseListener(listener);

        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                addGlobalMouseListener(child, listener);
            }
        }
    }
}
