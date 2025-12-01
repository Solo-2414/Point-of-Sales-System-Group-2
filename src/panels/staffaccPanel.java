package panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import dao.userDao;
import helpers.actionEditor;
import helpers.actionRenderer;
import helpers.buttonHelper;
import main.mainFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class staffaccPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public staffaccPanel() {
		setLayout(null);
		
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
		
		JButton btnAddAcc = new JButton("ADD ACCOUNT");
		btnAddAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffaccPanel panel = (staffaccPanel) btnAddAcc.getParent();
		        mainFrame parent = (mainFrame) panel.getTopLevelAncestor();
		        
				addAccountPanel addPanel = new addAccountPanel();
				parent.popup.showPanel(parent, addPanel, 654, 531);
			}
		});
		btnAddAcc.setFont(new Font("Poppins Medium", Font.PLAIN, 13));	
		btnAddAcc.setBounds(817, 6, 162, 42);
		buttonHelper.apply(btnAddAcc, "", 1, 1);
		btnAddAcc.setForeground(Color.BLACK);
		add(btnAddAcc);
		
		userDao dao = new userDao();
		dao.loadUsers(table);
		table.getColumnModel().getColumn(3).setCellRenderer(new actionRenderer("staff"));
		table.getColumnModel().getColumn(3).setCellEditor(new actionEditor("staff"));
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
}
