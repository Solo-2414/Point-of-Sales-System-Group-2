package panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import dao.productDao;
import helpers.actionEditor;
import helpers.actionRenderer;
import helpers.buttonHelper;
import helpers.lowstockwarningRenderer;
import helpers.session;
import main.mainFrame;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class productPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public productPanel() {
		setLayout(null);
		
		String role = session.currentUser.getRole();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 55, 1009, 557);
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
		
		JButton btnAddProduct = new JButton("ADD PRODUCT");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productPanel panel = (productPanel) btnAddProduct.getParent();
		        mainFrame parent = (mainFrame) panel.getTopLevelAncestor();
		        
				addProductPanel addPanel = new addProductPanel();
				parent.popup.showPanel(parent, addPanel, 654, 531);
			}
		});
		btnAddProduct.setFont(new Font("Poppins Medium", Font.PLAIN, 13));	
		btnAddProduct.setBounds(817, 6, 162, 42);
		buttonHelper.apply(btnAddProduct, "", 1, 1);
		btnAddProduct.setForeground(Color.BLACK);
		add(btnAddProduct);
		
		JButton btnProductList = new JButton("PRODUCT LIST");		
		btnProductList.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		btnProductList.setBounds(627, 6, 162, 42);
		buttonHelper.apply(btnProductList, "", 1, 1);
		btnProductList.setForeground(Color.BLACK);
		add(btnProductList);
		
		if(role.equals("user")) {
			btnAddProduct.setVisible(false);
			btnProductList.setBounds(806, 0, 162, 42);
		}
		productDao dao = new productDao();
		dao.loadProducts(table); 
		
		table.getColumnModel().getColumn(5).setCellRenderer(new lowstockwarningRenderer());
		table.getColumnModel().getColumn(6).setCellRenderer(new actionRenderer("product"));
		table.getColumnModel().getColumn(6).setCellEditor(new actionEditor("product"));
		table.setRowHeight(35);
		table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value,
		            boolean isSelected, boolean hasFocus, int row, int column) {

		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        setBackground(new Color(151, 179, 155));  // green header
		        setForeground(Color.BLACK);
		        setHorizontalAlignment(CENTER);
		        setFont(new Font("Poppins Medium", Font.BOLD, 14));
		        return this;
		    }
		});
	}
	public JTable getTable() {
	    return table;
	}
	public void refreshProducts() {
	    productDao dao = new productDao();
	    dao.loadProducts(table);
	}
}
