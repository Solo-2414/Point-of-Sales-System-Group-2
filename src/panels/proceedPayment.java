package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.posController;
import helpers.buttonHelper;
import main.mainFrame;
import java.awt.CardLayout;
import javax.swing.ImageIcon;

public class proceedPayment extends JPanel {

	private static final long serialVersionUID = 1L;
	private double totalAmount;
	private JTextField txtCash;
	private JTable table;
	private JLabel lblTotal;
	private JLabel lblChangeLabel;
	private JTextArea textArea;
	private salesTransactionPanel salesPanel;
	private posController controller = new posController();


	/**
	 * Create the panel.
	 */
	public proceedPayment(double totalAmount, JTable table, JLabel lblTotalLabel, JTextArea textArea, salesTransactionPanel salesPanel) {
		
	        this.totalAmount = totalAmount;
	        this.table = table;
	        this.textArea = textArea;
	        this.salesPanel = salesPanel;
	        
	        setBackground(Color.WHITE);
	        setBorder(BorderFactory.createLineBorder(new Color(200,200,200), 2));
	        
	        CardLayout cardLayout = new CardLayout();
	        setLayout(cardLayout);	        
	        
	        JPanel onlinePayment = new JPanel();
	        add(onlinePayment, "online");
	        onlinePayment.setLayout(null);
	        
	        JButton btnCloseOnline = new JButton("");
	        btnCloseOnline.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		close();
	        	}
	        });
	        btnCloseOnline.setBounds(0, 26, 61, 40);
	        buttonHelper.apply(btnCloseOnline, "/img/xmark-solid-full.png", 40, 40);
	        onlinePayment.add(btnCloseOnline);
	        
	        JPanel cardPayment = new JPanel();
	        add(cardPayment, "card");
	        cardPayment.setLayout(null);
	        
	        JButton btnCPay = new JButton("");
	        btnCPay.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		close();
	        	}
	        });
	        btnCPay.setBounds(0, 26, 61, 40);
	        buttonHelper.apply(btnCPay, "/img/xmark-solid-full.png", 40, 40);
	        cardPayment.add(btnCPay);
	        
	        JPanel cashPayment = new JPanel();
	        add(cashPayment, "cash");
	        cashPayment.setLayout(null);
	        
	        JLabel lblNewLabel_1 = new JLabel("CASH PAYMENT");
	        lblNewLabel_1.setFont(new Font("Poppins SemiBold", Font.PLAIN, 24));
	        lblNewLabel_1.setBounds(84, 40, 187, 26);
	        cashPayment.add(lblNewLabel_1);
	        
	        lblTotal = new JLabel("TOTAL: ₱" + String.format("%.2f", totalAmount));
	        lblTotal.setBounds(84, 101, 109, 14);
	        cashPayment.add(lblTotal);
	        
	        txtCash = new JTextField();
	        txtCash.setBounds(84, 150, 187, 20);
	        cashPayment.add(txtCash);
	        txtCash.setColumns(10);
	        
	        JLabel lblNewLabel_2 = new JLabel("CASH:");
	        lblNewLabel_2.setBounds(84, 125, 46, 14);
	        cashPayment.add(lblNewLabel_2);
	        
	        JButton btnPay = new JButton("CONFIRM PAYMENT");
	        btnPay.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		doPaymentAction();
	                
	                if (salesPanel != null) {
	                    salesPanel.refreshSelectedList();
	                }
	        	}
	        });
	        btnPay.setBounds(84, 199, 89, 23);
	        cashPayment.add(btnPay);
	        
	        lblChangeLabel = new JLabel("CHANGE");
	        lblChangeLabel.setBounds(84, 181, 46, 14);
	        cashPayment.add(lblChangeLabel);
	        
	        JButton btnCloseCash = new JButton("");
	        btnCloseCash.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		close();
	        	}
	        });
	        btnCloseCash.setBounds(0, 26, 61, 40);
	        buttonHelper.apply(btnCloseCash, "/img/xmark-solid-full.png", 40, 40);
	        cashPayment.add(btnCloseCash);
	        
	        JPanel paymentMethod = new JPanel();
	        add(paymentMethod, "method"); 
	        paymentMethod.setLayout(null);
	        
	        JLabel lblNewLabel = new JLabel("PAYMENT METHOD");
	        lblNewLabel.setFont(new Font("Poppins SemiBold", Font.PLAIN, 24));
	        lblNewLabel.setBounds(221, 49, 215, 23);
	        paymentMethod.add(lblNewLabel);
	        
	        JButton btnCash = new JButton("CASH");  
	        buttonHelper.apply(btnCash, "/img/money-bill-solid-full.png", 130, 130);
	        btnCash.setHorizontalTextPosition(SwingConstants.CENTER);
	        btnCash.setVerticalTextPosition(SwingConstants.BOTTOM);
	        btnCash.setBackground(new Color(58, 111, 67));
	        btnCash.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		cardLayout.show(proceedPayment.this, "cash");
	        	}
	        });
	        btnCash.setOpaque(true);
	        btnCash.setContentAreaFilled(true);
	        btnCash.setBorderPainted(false);
	        btnCash.setFont(new Font("Poppins", Font.PLAIN, 14));
	        btnCash.setBounds(55, 175, 165, 180);
	        paymentMethod.add(btnCash);
	        
	        JButton btnCard = new JButton("CARD");
	        buttonHelper.apply(btnCard, "/img/credit-card-solid-full.png", 130, 130);
	        btnCard.setHorizontalTextPosition(SwingConstants.CENTER);
	        btnCard.setVerticalTextPosition(SwingConstants.BOTTOM);
	        btnCard.setBackground(new Color(58, 111, 67));
	        btnCard.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		cardLayout.show(proceedPayment.this, "card");
	        	}
	        });
	        btnCard.setOpaque(true);
	        btnCard.setContentAreaFilled(true);
	        btnCard.setBorderPainted(false);
	        btnCard.setFont(new Font("Poppins", Font.PLAIN, 14));
	        btnCard.setBounds(433, 175, 165, 180);
	        paymentMethod.add(btnCard);
	        
	        JButton btnOnline = new JButton("ONLINE");
	        buttonHelper.apply(btnOnline, "/img/wallet-solid-full.png", 130, 130);
	        btnOnline.setHorizontalTextPosition(SwingConstants.CENTER);
	        btnOnline.setVerticalTextPosition(SwingConstants.BOTTOM);
	        btnOnline.setBackground(new Color(58, 111, 67));
	        btnOnline.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		cardLayout.show(proceedPayment.this, "online");
	        	}
	        });
	        btnOnline.setOpaque(true);
	        btnOnline.setContentAreaFilled(true);
	        btnOnline.setBorderPainted(false);
	        btnOnline.setFont(new Font("Poppins", Font.PLAIN, 14));
	        btnOnline.setBounds(246, 175, 165, 180);
	        paymentMethod.add(btnOnline);
	        
	        JButton btnClose = new JButton("");
	        btnClose.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		close();
	        	}
	        });
	        buttonHelper.apply(btnClose, "/img/xmark-solid-full.png", 40, 40);
	        btnClose.setBounds(0, 26, 61, 40);
	        paymentMethod.add(btnClose);
	        
	        JLabel lblTotalOf = new JLabel("₱" + String.format("%.2f", totalAmount));
	        lblTotalOf.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTotalOf.setFont(new Font("Segoe UI", Font.PLAIN, 24));
	        lblTotalOf.setBounds(246, 81, 165, 40);
	        paymentMethod.add(lblTotalOf);
	        
	        cardLayout.show(this, "method");
	        
	}
	 private void close() {
		 JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(salesPanel);
	     ((mainFrame) parent).popup.closePanel(parent);
	 }
	 private void doPaymentAction() {
		 String cashText = txtCash.getText().trim();

		    controller.processPayment(table, cashText, lblTotal, lblChangeLabel);

		    try {
		        double cash = Double.parseDouble(cashText);
		        double total = totalAmount;
		        clearSelectedProducts();
		        if (cash >= total) {
		            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		            if (parentFrame instanceof mainFrame) {
		                ((mainFrame) parentFrame).popup.closePanel(parentFrame);
		            }
		        }
		    } catch (Exception ex) {
		    }
	    }
	 private void clearSelectedProducts() {
		    for (int i = 0; i < table.getRowCount(); i++) {
		        table.setValueAt(false, i, 0); 
		        table.setValueAt(1, i, 5);     
		    }
		}
}
