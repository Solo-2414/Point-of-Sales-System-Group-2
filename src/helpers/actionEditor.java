package helpers;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class actionEditor extends AbstractCellEditor implements TableCellEditor{

	private actionButton panel;
	
	public actionEditor(String mode) {
        panel = new actionButton(mode);
    }
	
  
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		panel.setRow(row);
		panel.setTable(table);
		return panel;
	}
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
