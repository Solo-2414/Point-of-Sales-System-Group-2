package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import dao.productDao;
import helpers.session;
import main.mainFrame;

public class posController {

    private productDao dao = new productDao();

    public void processPayment(JTable table, String cashText, 
                               javax.swing.JLabel lblTotal,
                               javax.swing.JLabel lblChange) {

        try {
            double total = 0.0;
            TableModel model = table.getModel();
            List<int[]> itemsToSave = new ArrayList<>();

            for (int r = 0; r < model.getRowCount(); r++) {
                Object selObj = model.getValueAt(r, 0);
                boolean selected = selObj instanceof Boolean && (Boolean) selObj;
                if (selected) {
                    int prodId = ((Number) model.getValueAt(r, 1)).intValue();
                    double price = ((Number) model.getValueAt(r, 3)).doubleValue();
                    int qty = ((Number) model.getValueAt(r, 5)).intValue();
                    total += price * qty;
                    itemsToSave.add(new int[]{prodId, qty});
                }
            }

            if (itemsToSave.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No items selected.");
                return;
            }

            double cash = Double.parseDouble(cashText.trim());

            int transactionId = dao.createTransaction("CASH", total, session.getUser().getId());
            if (transactionId <= 0) {
                JOptionPane.showMessageDialog(null, "Error creating transaction.");
                return;
            }

            boolean allOk = true;

            for (int[] it : itemsToSave) {
                int prodId = it[0];
                int qty = it[1];
                double price = 0.0;

                for (int r = 0; r < model.getRowCount(); r++) {
                    int id = ((Number) model.getValueAt(r, 1)).intValue();
                    if (id == prodId) {
                        price = ((Number) model.getValueAt(r, 3)).doubleValue();
                        break;
                    }
                }

                boolean ok1 = dao.insertTransactionItem(transactionId, prodId, qty, price);
                boolean ok2 = dao.updateProductStockByDelta(prodId, -qty);

                if (!ok1 || !ok2) allOk = false;
            }

            if (!allOk) {
                JOptionPane.showMessageDialog(null, "Some items failed to save.");
            } else {
                double change = cash - total;
                lblChange.setText("CHANGE: ₱" + String.format("%.2f", change));

                JOptionPane.showMessageDialog(null,
                        "Payment successful!\nChange: ₱" + String.format("%.2f", change));                             

                dao.loadProductsForPOS(table);
                
                java.awt.Window w = javax.swing.SwingUtilities.getWindowAncestor(table);
                if (w instanceof mainFrame parentFrame) {
                    if (parentFrame.productPanelInstance != null) {
                        parentFrame.productPanelInstance.refreshProducts();
                    }
                }

                lblTotal.setText("TOTAL: ₱0.00");
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Invalid cash amount.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Payment error.");
        }
    }
    public static double calculateTotal(JTable table) {

        double total = 0;

        for (int i = 0; i < table.getRowCount(); i++) {

            Boolean selected = (Boolean) table.getValueAt(i, 0);
            if (selected != null && selected) {

                double price = ((Number) table.getValueAt(i, 3)).doubleValue();
                int qty = ((Number) table.getValueAt(i, 5)).intValue();

                total += price * qty;
            }
        }

        return total;
    }

    public static void updateTotalLabel(JTable table, JLabel lblTotal) {
        double total = calculateTotal(table);
        lblTotal.setText("TOTAL: ₱" + String.format("%.2f", total));
    }
    public static double getTotal(JTable table) {
        return calculateTotal(table);
    }
}
