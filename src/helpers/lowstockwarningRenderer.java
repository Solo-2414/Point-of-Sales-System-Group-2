package helpers;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class lowstockwarningRenderer extends DefaultTableCellRenderer {

    private Icon warningIcon;
    private Icon lowWarningIcon;
    public lowstockwarningRenderer() {

    	warningIcon = iconHelper.load("/img/triangle-red-exclamation-solid-full.png", 20, 20);
    	lowWarningIcon = iconHelper.load("/img/triangle-yellow-exclamation-solid-full.png", 20, 20);
    	
        setHorizontalAlignment(LEFT);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        int stock = (int) value;
        int reorderLevel = (int) table.getValueAt(row, 4); // reorder level column
        
        setHorizontalAlignment(CENTER);
        
        if (stock >= reorderLevel/2 && stock <= reorderLevel) {
            setIcon(lowWarningIcon);
            setToolTipText("Stock is lower than the Reorder Level - Need to Restock");
            setIconTextGap(3);
        }else if(stock < reorderLevel/2){
        	setIcon(warningIcon);
            setToolTipText("Low Stock - Need to Restock");
            setIconTextGap(3);
        }else {
            setIcon(null);
            setToolTipText(null);
        }

        setText(String.valueOf(stock)); // keep stock number visible
        return this;
    }
}
