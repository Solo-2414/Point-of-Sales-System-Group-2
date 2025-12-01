package helpers;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

public class quantityEditor extends AbstractCellEditor implements TableCellEditor {
	private JSpinner spinner;

    public quantityEditor() {
    	spinner = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1));

        ((JSpinner.DefaultEditor) spinner.getEditor())
                .getTextField().setHorizontalAlignment(SwingConstants.CENTER);

        ((JSpinner.DefaultEditor) spinner.getEditor())
                .getTextField().setEditable(false);
    }

    @Override
    public Object getCellEditorValue() {
    	return spinner.getValue();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	

        int stock = parseIntSafe(table.getValueAt(row, 4), 1);

        SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();
        model.setMinimum(1);
        model.setMaximum(stock);

        if (value instanceof Number n) {
            spinner.setValue(Math.min(n.intValue(), stock));
        } else {
            spinner.setValue(1);
        }

        return spinner;
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        if (e instanceof MouseEvent me) {
            JTable table = (JTable) e.getSource();
            int row = table.rowAtPoint(me.getPoint());
            int col = table.columnAtPoint(me.getPoint());

            if (col == 5) {
                boolean checked = Boolean.TRUE.equals(table.getValueAt(row, 0));
                int stock = parseIntSafe(table.getValueAt(row, 4), 0);

                if (!checked) {
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(
                            table, "Please check the item first.",
                            "Not Allowed", JOptionPane.WARNING_MESSAGE
                        )
                    );
                    return false;
                }

                if (stock <= 0) {
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(
                            table, "No stock available.",
                            "Out of stock", JOptionPane.ERROR_MESSAGE
                        )
                    );
                    return false;
                }
            }
        }

        return true;
    }
    @Override
    public boolean shouldSelectCell(EventObject e) {
        return true;
    }

    private int parseIntSafe(Object obj, int fallback) {
    	 try {
             return Integer.parseInt(obj.toString());
         } catch (Exception e) {
             return fallback;
         }
    }
}
