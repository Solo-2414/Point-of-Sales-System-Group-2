package panels;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.productDao;
import helpers.buttonHelper;
import helpers.inputHelper;
import main.mainFrame;
import main.roundBorder;
import main.roundPanel;
import model.product;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class editProductPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField proID;
	private JTextField proName;
	private JTextField proPrice;
	private JTextField proDesc;
	private JTextField proReorder;
	private JTextField proStock;
	private int productId;

	/**
	 * Create the panel.
	 */
	public editProductPanel(int productId) {
		this.productId = productId;
		setBackground(Color.WHITE);
		setBorder(javax.swing.BorderFactory.createLineBorder(new Color(200,200,200), 2));
		setLayout(null);
		
		JLabel lbladdProduct = new JLabel("EDIT PRODUCT");
		lbladdProduct.setFont(new Font("Poppins SemiBold", Font.PLAIN, 24));
		lbladdProduct.setBounds(26, 44, 237, 25);
		add(lbladdProduct);
		
		JLabel lblProductID = new JLabel("PRODUCT ID");
		lblProductID.setOpaque(true);
		lblProductID.setBackground(Color.WHITE);
		lblProductID.setForeground(new Color(0, 0, 0));
		lblProductID.setFont(new Font("Inter 24pt Light", Font.PLAIN, 13));
		lblProductID.setBounds(41, 104, 76, 14);
		add(lblProductID);
        
		
		roundPanel productID = new roundPanel(20);
		productID.setBounds(26, 111, 275, 39);
		productID.setLayout(null);
		productID.setBackground(Color.WHITE);
		productID.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		add(productID);
		
		proID = new JTextField();
		proID.setFont(new Font("Poppins", Font.PLAIN, 13));
		proID.setBounds(10, 11, 255, 22);
		proID.setBorder(null);
		productID.add(proID);
		inputHelper.hideLabelOnType(proID, lblProductID);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Inter 24pt Light", Font.PLAIN, 13));
		lblName.setOpaque(true);
		lblName.setBackground(Color.WHITE);
		lblName.setBounds(373, 104, 37, 14);
		add(lblName);
		
		roundPanel name = new roundPanel(20);
		name.setLayout(null);
		name.setBackground(Color.WHITE);
		name.setBounds(358, 111, 275, 39);
		name.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		add(name);
		
		proName = new JTextField();
		proName.setFont(new Font("Poppins", Font.PLAIN, 13));
		proName.setBorder(null);
		proName.setBounds(10, 11, 255, 22);
		name.add(proName);
		inputHelper.hideLabelOnType(proName, lblName);
		
		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setFont(new Font("Inter 24pt Light", Font.PLAIN, 13));
		lblPrice.setOpaque(true);
		lblPrice.setBackground(Color.WHITE);
		lblPrice.setBounds(373, 207, 37, 14);
		add(lblPrice);
		
		roundPanel price = new roundPanel(20);
		price.setLayout(null);
		price.setBackground(Color.WHITE);
		price.setBounds(358, 214, 275, 39);
		price.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		add(price);
		
		proPrice = new JTextField();
		proPrice.setFont(new Font("Poppins", Font.PLAIN, 13));
		proPrice.setBorder(null);
		proPrice.setBounds(10, 11, 255, 22);
		price.add(proPrice);
		inputHelper.hideLabelOnType(proPrice, lblPrice);
		
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setFont(new Font("Inter 24pt Light", Font.PLAIN, 13));
		lblDescription.setOpaque(true);
		lblDescription.setBackground(Color.WHITE);
		lblDescription.setBounds(41, 207, 84, 14);
		add(lblDescription);
		
		roundPanel desc = new roundPanel(20);
		desc.setLayout(null);
		desc.setBackground(Color.WHITE);
		desc.setBounds(26, 214, 275, 39);
		desc.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		add(desc);
		
		proDesc = new JTextField();
		proDesc.setFont(new Font("Poppins", Font.PLAIN, 13));
		proDesc.setBorder(null);
		proDesc.setBounds(10, 11, 255, 22);
		desc.add(proDesc);
		inputHelper.hideLabelOnType(proDesc, lblDescription);
		
		JLabel lblReorderLvl = new JLabel("REORDER LEVEL");
		lblReorderLvl.setFont(new Font("Inter 24pt Light", Font.PLAIN, 13));
		lblReorderLvl.setOpaque(true);
		lblReorderLvl.setBackground(Color.WHITE);
		lblReorderLvl.setBounds(41, 311, 100, 14);
		add(lblReorderLvl);
		
		roundPanel reorder = new roundPanel(20);
		reorder.setLayout(null);
		reorder.setBackground(Color.WHITE);
		reorder.setBounds(26, 318, 275, 39);
		reorder.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		add(reorder);
		
		
		
		proReorder = new JTextField();
		proReorder.setFont(new Font("Poppins", Font.PLAIN, 13));
		proReorder.setBorder(null);
		proReorder.setBounds(10, 11, 255, 22);
		reorder.add(proReorder);
		inputHelper.hideLabelOnType(proReorder, lblReorderLvl);
		
		JLabel lblStockQuantity = new JLabel("STOCK QUANTITY");
		lblStockQuantity.setFont(new Font("Inter 24pt Light", Font.PLAIN, 13));
		lblStockQuantity.setOpaque(true);
		lblStockQuantity.setBackground(Color.WHITE);
		lblStockQuantity.setBounds(373, 311, 110, 14);
		add(lblStockQuantity);
		
		roundPanel stock = new roundPanel(20);
		stock.setLayout(null);
		stock.setBackground(Color.WHITE);
		stock.setBounds(358, 318, 275, 39);
		stock.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		add(stock);
		
		proStock = new JTextField();
		proStock.setFont(new Font("Poppins", Font.PLAIN, 13));
		proStock.setBorder(null);
		proStock.setBounds(10, 11, 255, 22);
		stock.add(proStock);
		inputHelper.hideLabelOnType(proStock, lblStockQuantity);
		
		JButton btnSave = new JButton("UPDATE");
		btnSave.setFont(new Font("Poppins", Font.PLAIN, 20));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
		            product p = new product();
		            p.setProdID(productId);
		            p.setName(proName.getText());
		            p.setDescription(proDesc.getText());
		            p.setPrice(Double.parseDouble(proPrice.getText()));
		            p.setReorderLevel(Integer.parseInt(proReorder.getText()));
		            p.setStockQuantity(Integer.parseInt(proStock.getText()));

		            productDao dao = new productDao();
		            

		            if (dao.editProduct(p)) {
		                JOptionPane.showMessageDialog(null, "Product updated!");
		                JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(editProductPanel.this);
		                mainFrame frame = (mainFrame) parent;
		                frame.popup.closePanel(parent);
		                dao.refreshProductTable(frame.getProductPanel().getTable());
		            } else {
		                JOptionPane.showMessageDialog(null, "Error updating product.");
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Invalid input. Please check fields.");
		        }
				
		            
			}
		});
		btnSave.setBounds(451, 447, 182, 45);
		add(btnSave);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(editProductPanel.this);
		        ((mainFrame) parent).popup.closePanel(parent);
			}
		});
		btnCancel.setFont(new Font("Poppins", Font.PLAIN, 20));
		btnCancel.setBounds(233, 447, 182, 45);
		add(btnCancel);
		
		loadProductData();      
        
	}
	private void loadProductData() {
	    productDao dao = new productDao();
	    product p = dao.getProductById(productId);

	    proID.setText(String.valueOf(p.getProdID()));
	    proName.setText(p.getName());
	    proDesc.setText(p.getDescription());
	    proPrice.setText(String.valueOf(p.getPrice()));
	    proReorder.setText(String.valueOf(p.getReorderLevel()));
	    proStock.setText(String.valueOf(p.getStockQuantity()));
	}
}
