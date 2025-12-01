package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.sql.Statement;

import database.dbConnection;
import helpers.actionEditor;
import helpers.actionRenderer;
import helpers.lowstockwarningRenderer;
import model.product;

public class productDao {
	
	public boolean addProduct(product p) {
		try {
			Connection conn = dbConnection.getConnection();
			String sql = "INSERT INTO products (name, description, price, reorder_level, stock) VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getDescription());
			ps.setDouble(3, p.getPrice());
			ps.setInt(4, p.getReorderLevel());
			ps.setInt(5, p.getStockQuantity());
			
			return ps.executeUpdate() > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	public boolean editProduct(product p) {
		try {
			Connection conn = dbConnection.getConnection();
			String sql = "UPDATE products SET name = ?, description = ?, price = ?, reorder_level = ?, stock = ? WHERE id = ?";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, p.getName());
	        pst.setString(2, p.getDescription());
	        pst.setDouble(3, p.getPrice());
	        pst.setInt(4, p.getReorderLevel());
	        pst.setInt(5, p.getStockQuantity());
	        pst.setInt(6, p.getProdID());
			
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean deleteProduct(int id) {
		try {
	        Connection conn = dbConnection.getConnection();
	        String sql = "DELETE FROM products WHERE id = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public void loadProducts(JTable table) {
	    try {
	        Connection conn = dbConnection.getConnection();
	        String sql = "SELECT * FROM products";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        DefaultTableModel model = new DefaultTableModel(
	            new Object[]{"ID","Name","Description","Price","Reorder Level","Stock","Actions"}, 
	            0
	        ) {
	        	@Override
		        public boolean isCellEditable(int row, int column) {
		            return column == 6; // all cells non-editable
		        }
	        };
        
	        while (rs.next()) {
	            model.addRow(new Object[]{
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("description"),
	                "\u20B1" + String.format("%.2f", rs.getDouble("price")),
	                rs.getInt("reorder_level"),
	                rs.getInt("stock"),
	                ""
	            });
	        }

	        table.setModel(model);
	        
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

	        int[] centerColumns = {0, 3, 4, 5};

	        for (int col : centerColumns) {
	            table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
	        }
	        table.getColumnModel().getColumn(5).setCellRenderer(new lowstockwarningRenderer());
	        table.getColumnModel().getColumn(6).setCellRenderer(new actionRenderer("product"));
			table.getColumnModel().getColumn(6).setCellEditor(new actionEditor("product"));
			table.setRowHeight(35);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public void refreshProductTable(JTable table) {
	    loadProducts(table);
	    
	}

	public product getProductById(int productId) {
		 product p = null;
		    try {
		        Connection conn = dbConnection.getConnection();
		        String sql = "SELECT * FROM products WHERE id = ?";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setInt(1, productId);

		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            p = new product();
		            p.setProdID(rs.getInt("id"));        
		            p.setName(rs.getString("name"));
		            p.setDescription(rs.getString("description"));
		            p.setPrice(rs.getDouble("price"));
		            p.setReorderLevel(rs.getInt("reorder_level"));
		            p.setStockQuantity(rs.getInt("stock"));
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return p;
	}
	public void loadProductsForPOS(JTable table) {
		 try {
			 	Connection conn = dbConnection.getConnection();
			 	 String sql = "SELECT id, name, price, stock FROM products";
			        PreparedStatement ps = conn.prepareStatement(sql);
			        ResultSet rs = ps.executeQuery();

			        DefaultTableModel model = new DefaultTableModel(
			            new Object[]{"Select","ID","Name","Price","Stock","Qty"}, 0
			        ) {
			            @Override
			            public boolean isCellEditable(int row, int col) {
			                if (col == 0) { 
			                    Object stockObj = getValueAt(row, 4);
			                    int stock = (stockObj instanceof Number) ? ((Number) stockObj).intValue() : 0;
			                    return stock > 0;
			                }
			                return col == 5;
			            }

			            @Override
			            public Class<?> getColumnClass(int column) {
			                switch (column) {
			                    case 0: return Boolean.class;
			                    case 1: return Integer.class;
			                    case 3: return Double.class;
			                    case 4: return Integer.class;
			                    case 5: return Integer.class;
			                    default: return String.class;
			                }
			            }
			        };

			        while (rs.next()) {
			            int stock = rs.getInt("stock");

			            model.addRow(new Object[] {
			                false,
			                rs.getInt("id"),
			                rs.getString("name"),
			                rs.getDouble("price"),
			                stock,
			                stock > 0 ? 1 : 0
			            });
			        }

			        table.setModel(model);
			        table.setRowHeight(40);

			        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
			        center.setHorizontalAlignment(SwingConstants.CENTER);
			        int[] centerCols = {1, 3, 4, 5};
			        for (int col : centerCols) {
			            table.getColumnModel().getColumn(col).setCellRenderer(center);
			        }

			        table.getColumnModel().getColumn(5).setCellEditor(new helpers.quantityEditor());


		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}

	public int createTransaction(String paymentMethod, double totalAmount, int staffId) {
	    int generatedId = -1;
	    
	    try {
	    	Connection conn = dbConnection.getConnection();	        
	    	String sql = "INSERT INTO transactions (customer_id, transaction_date, payment_method, total_amount, staff_id) VALUES (NULL, NOW(), ?, ?, ?)";
	    	PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
	    	
	        ps.setString(1, paymentMethod);
	        ps.setDouble(2, totalAmount);
	        ps.setInt(3, staffId);
	        int rows = ps.executeUpdate();
	        if (rows > 0) {
	            ResultSet rs = ps.getGeneratedKeys();
	            if (rs.next()) {
	                generatedId = rs.getInt(1);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return generatedId;
	}
	public boolean insertTransactionItem(int transactionId, int productId, int quantity, double price) {
	    String sql = "INSERT INTO transaction_items (transaction_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
	    try (Connection conn = dbConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, transactionId);
	        ps.setInt(2, productId);
	        ps.setInt(3, quantity);
	        ps.setDouble(4, price);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean updateProductStockByDelta(int productId, int delta) {
	    String sql = "UPDATE products SET stock = GREATEST(stock + ?, 0) WHERE id = ?";
	    try (Connection conn = dbConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, delta);
	        ps.setInt(2, productId);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public int getTotalProducts() {
	    int total = 0;
	    try (Connection conn = dbConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) AS total FROM products");
	         ResultSet rs = ps.executeQuery()) {

	        if (rs.next()) {
	            total = rs.getInt("total");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
	}
	public double getTotalSales() {
	    double total = 0;
	    try (Connection conn = dbConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement("SELECT SUM(total_amount) AS total FROM transactions");
	         ResultSet rs = ps.executeQuery()) {

	        if (rs.next()) {
	            total = rs.getDouble("total");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
	}
	public int getLowStockCount() {
	    int count = 0;
	    try {
	        Connection conn = dbConnection.getConnection();
	        String sql = "SELECT COUNT(*) AS cnt FROM products WHERE stock <= reorder_level";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            count = rs.getInt("cnt");
	        }

	        rs.close();
	        pst.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return count;
	}
}

