package panels;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import controller.posController;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import dao.productDao;
import helpers.quantityEditor;
import main.mainFrame;

public class salesTransactionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel lblTotal;
    public String Change;
    public JTextArea textArea;
    private boolean isUpdatingTable = false;
    private productDao dao = new productDao();

	/**
	 * Create the panel.
	 */
	public salesTransactionPanel() {
		setLayout(null);
		setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 78, 701, 534);
		add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(45);
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFont(new Font("Poppins", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 14));
		table.getTableHeader().setPreferredSize(new Dimension(0, 40));
		table.setSelectionBackground(new Color(230, 230, 230));
		table.setSelectionForeground(Color.BLACK);
		table.setShowHorizontalLines(true);
		table.setGridColor(new Color(230, 230, 230));
		table.setShowVerticalLines(false);
		scrollPane.setViewportView(table);
		
		table.setModel(new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int col) {
		        // Allow only checkbox (0) and Qty column (5) to be editable
		        return col == 0 || col == 5;
		    }
		});
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.rowAtPoint(e.getPoint());
		        int col = table.columnAtPoint(e.getPoint());

		        if (row < 0 || col < 0) return;

		        Object stockObj = table.getValueAt(row, 4);
                int stock = (stockObj instanceof Number) ? ((Number) stockObj).intValue() : 0;
                Object selObj = table.getValueAt(row, 0);
                boolean checked = selObj instanceof Boolean ? (Boolean) selObj : false;

		        if (col == 0 && stock == 0) {
		            JOptionPane.showMessageDialog(
		                table,
		                "This product has no stock available.",
		                "No Stock",
		                JOptionPane.ERROR_MESSAGE
		            );
		        }

		        if (col == 5 && !checked) {
		            JOptionPane.showMessageDialog(
		                table,
		                "Please check the product first before setting quantity.",
		                "Not Selected",
		                JOptionPane.WARNING_MESSAGE
		            );
		        }
		    }
		});
		
		//======================
        // POS RIGHT PANEL
        //======================
		
		JPanel posPanel = new JPanel(null);
        posPanel.setBackground(new Color(58, 111, 67));
        posPanel.setBounds(701, 0, 309, 612);
        add(posPanel);
        
        lblTotal = new JLabel("TOTAL: ₱0.00");
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setFont(new Font("Segoe UI Semibold", Font.BOLD, 22));
        lblTotal.setBounds(20, 60, 260, 40);
        posPanel.add(lblTotal);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(20, 191, 270, 410);
        posPanel.add(scrollPane_1);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPane_1.setViewportView(textArea);


        JButton btnNewButton = new JButton("PROCEED PAYMENT ");
        btnNewButton.setFont(new Font("Poppins", Font.PLAIN, 24));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mainFrame parent = (mainFrame) salesTransactionPanel.this.getTopLevelAncestor();
                double total = posController.getTotal(table);

                proceedPayment paymentPanel = new proceedPayment(total, table, lblTotal, textArea, salesTransactionPanel.this);

                parent.popup.showPanel(parent, paymentPanel, 654, 531);
        	}
        });
        btnNewButton.setBounds(20, 138, 260, 23);
        posPanel.add(btnNewButton);
        
        
        
        //======================
        // LOAD PRODUCT DATA
        //======================
        dao.loadProductsForPOS(table);
        
        installRenderersEditorsAndListener(textArea);
  
        JLabel lblTitle = new JLabel("TRACK AND GROWTH POS", SwingConstants.LEFT);
        lblTitle.setBounds(10, 18, 404, 40);
        add(lblTitle);
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setFont(new Font("Poppins Medium", Font.BOLD, 24));

        if (table.getColumnModel().getColumnCount() > 5) {
            table.getColumnModel().getColumn(5).setCellEditor(new quantityEditor());
        }
        
        updateSelectedList(textArea);
        updateTotalLabel();     

	}
	
	private void installRenderersEditorsAndListener(JTextArea textArea) {

        if (table.getColumnModel().getColumnCount() <= 5) return;

        table.getColumnModel().getColumn(5).setCellEditor(new quantityEditor());

        DefaultTableCellRenderer priceRenderer = new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                if (value instanceof Number) {
                    setText("\u20B1 " + String.format("%.2f", ((Number) value).doubleValue()));
                } else {
                    super.setValue(value);
                }
            }
        };
        priceRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(3).setCellRenderer(priceRenderer);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // ID
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); // Stock
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer); // Qty

        TableModel model = table.getModel();
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                if (isUpdatingTable) {
                    return;
                }

                isUpdatingTable = true;

                try {
                    updateSelectedList(textArea);
                    updateTotalLabel();
                } finally {
                    isUpdatingTable = false;
                }
            }
        });
        table.addPropertyChangeListener("tableCellEditor", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                if (!table.isEditing()) {
                    if (table.getCellEditor() != null) {
                        try { table.getCellEditor().stopCellEditing(); } catch (Exception ignored) {}
                    }
                    updateSelectedList(textArea);
                    updateTotalLabel();
                }
            }
        });


       
    }
	private void updateTotalLabel() {
        posController.updateTotalLabel(table, lblTotal);
    }    
	public void updateSelectedList(JTextArea textArea) {
        TableModel model = table.getModel();
        StringBuilder list = new StringBuilder();

        list.append(String.format("%-18s %5s %12s\n", "Item", "Qty", "Line Total"));
        list.append("----------------------------------------\n");

        double grandTotal = 0.0;
        boolean anyChecked = false;
        
        for (int i = 0; i < model.getRowCount(); i++) {
            Object selObj = model.getValueAt(i, 0);
            boolean checked = selObj instanceof Boolean ? (Boolean) selObj : false;
            if (!checked) continue;
            
            anyChecked = true;

            Object nameObj = model.getValueAt(i, 2);
            String name = nameObj != null ? nameObj.toString() : "Item";

            double price = 0.0;
            Object priceObj = model.getValueAt(i, 3);
            if (priceObj instanceof Number) {
                price = ((Number) priceObj).doubleValue();
            } else {
                try {
                    String s = priceObj != null ? priceObj.toString().replaceAll("[^0-9.\\-]", "") : "0";
                    price = Double.parseDouble(s);
                } catch (Exception ex) { price = 0.0; }
            }

            int qty = 1;
            Object qtyObj = model.getValueAt(i, 5);
            if (qtyObj instanceof Number) {
                qty = ((Number) qtyObj).intValue();
            } else {
                try { qty = Integer.parseInt(qtyObj != null ? qtyObj.toString() : "1"); } catch (Exception ex) { qty = 1; }
            }

            double line = price * qty;
            grandTotal += line;

            String truncatedName = name.length() > 18 ? name.substring(0, 15) + "..." : name;
            list.append(String.format("%-20s %5d %12s\n", truncatedName, qty, "\u20B1 " + String.format("%.2f", line)));
        }
        
        if (!anyChecked) {
            list.append(String.format("%-20s %5s %12s\n", "No products selected", "", ""));
        }

        list.append("----------------------------------------\n");
        list.append(String.format("%-18s %5s %12s\n", "", "", "\u20B1 " + String.format("%.2f", grandTotal)));

        textArea.setText(list.toString());
    }
	public void refreshSelectedList() {
	    updateSelectedList(textArea);
	}
}
