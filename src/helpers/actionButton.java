package helpers;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import main.mainFrame;
import panels.addProductPanel;
import panels.deleteAccountPanel;
import panels.deleteProductPanel;
import panels.editAccountPanel;
import panels.editProductPanel;
import panels.productPanel;
import panels.staffaccPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class actionButton extends JPanel {

	private static final long serialVersionUID = 1L;
		public JButton btnEdit;
		public JButton btnDelete;
		private int row;
		private JTable table;
		private String mode;
	/**
	 * Create the panel.
	 */
	public actionButton(String mode) {
		this.mode = mode;
		setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
		btnEdit = new JButton("");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performEdit();
			}
		});
        buttonHelper.apply(btnEdit, "/img/pencil-solid-full.png", 20, 20); 
        btnEdit.setFocusable(false);
        btnEdit.setBorder(null);
        btnEdit.setToolTipText("Edit");
        btnEdit.setContentAreaFilled(false);
        
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 4, 0, 4); // spacing left-right
        add(btnEdit, gbc);
        
        btnDelete = new JButton("");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		performDelete();
        	}
        });
        buttonHelper.apply(btnDelete, "/img/trash-solid-full.png", 20, 20); 
        btnDelete.setFocusable(false);
        btnDelete.setBorder(null);
        btnDelete.setToolTipText("Delete");
        btnDelete.setContentAreaFilled(false);
        
        gbc.gridx = 1;
        add(btnDelete, gbc);
	}
	 private void performEdit() {
	        mainFrame parent = (mainFrame) SwingUtilities.getWindowAncestor(actionButton.this);

	        if (mode.equals("product")) {
	      	  JTable table = ((productPanel) SwingUtilities.getAncestorOfClass(productPanel.class, actionButton.this)).getTable();
	          int row = table.getSelectedRow();
	          
	          int productId = Integer.parseInt(table.getValueAt(row, 0).toString());
	      
	          editProductPanel editPanel = new editProductPanel(productId);
	          parent.popup.showPanel(parent, editPanel, 654, 531);
	        } else if (mode.equals("staff")) {
	        	  JTable table = ((staffaccPanel) SwingUtilities.getAncestorOfClass(staffaccPanel.class, actionButton.this)).getTable();
		          int row = table.getSelectedRow();
		          
		          int userId = Integer.parseInt(table.getValueAt(row, 0).toString());
		      
		          editAccountPanel editPanel = new editAccountPanel(userId);
		          parent.popup.showPanel(parent, editPanel, 654, 531);
	        }
	 }
	 private void performDelete() {
	        mainFrame parent = (mainFrame) SwingUtilities.getWindowAncestor(actionButton.this);

	        if (mode.equals("product")) {
	    		JTable table = ((productPanel) SwingUtilities.getAncestorOfClass(productPanel.class, actionButton.this)).getTable();
	    
	    	    int row = table.getSelectedRow();
	    	    int productId = Integer.parseInt(table.getValueAt(row, 0).toString());	      
	    	    
	    	    deleteProductPanel deletePanel = new deleteProductPanel(productId);
	    	    parent.popup.showPanel(parent, deletePanel, 392, 265);
	        } else if (mode.equals("staff")) {
	            JTable table = ((staffaccPanel) SwingUtilities.getAncestorOfClass(staffaccPanel.class, actionButton.this)).getTable();
	    
	    	    int row = table.getSelectedRow();
	    	    int userId = Integer.parseInt(table.getValueAt(row, 0).toString());	      
	    	    
	    	    deleteAccountPanel deletePanel = new deleteAccountPanel(userId);
	    	    parent.popup.showPanel(parent, deletePanel, 392, 265);
	        }
	}
	public void setRow(int row) {
	    this.row = row;
	}
	public void setTable(JTable table) {
	    this.table = table;
	}
	

	
	
	


}
