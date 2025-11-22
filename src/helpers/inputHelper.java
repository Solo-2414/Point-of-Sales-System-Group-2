package helpers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class inputHelper {

	public static void attachPlaceholder(JTextField searchtxtf, String pHolder, Color phColor, Color textColor) {
		searchtxtf.setText(pHolder);
		searchtxtf.setForeground(phColor);
		searchtxtf.getCaret().setVisible(false);
		
		searchtxtf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
                if (searchtxtf.getText().equals(pHolder)) {
                	searchtxtf.setText("");
                	searchtxtf.setForeground(textColor);
                	searchtxtf.getCaret().setVisible(true);
                }
            }
		});
	}
	public static void hideLabelOnType(JTextField txtField, JLabel label) {
	    txtField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

	        private void update() {
	            if (txtField.getText().trim().isEmpty()) {
	                label.setVisible(true);
	            } else {
	                label.setVisible(false);
	            }
	        }

	        @Override
	        public void insertUpdate(javax.swing.event.DocumentEvent e) {
	            update();
	        }

	        @Override
	        public void removeUpdate(javax.swing.event.DocumentEvent e) {
	            update();
	        }

	        @Override
	        public void changedUpdate(javax.swing.event.DocumentEvent e) {
	            update();
	        }
	    });
	}



}
