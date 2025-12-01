package helpers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class actionRenderer implements TableCellRenderer{
	private String mode;
	
	public actionRenderer(String mode) {
		this.mode = mode;
	}
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		actionButton button = new actionButton(mode);
		button.setRow(row);
        button.setTable(table);
        return button;
    }
}
