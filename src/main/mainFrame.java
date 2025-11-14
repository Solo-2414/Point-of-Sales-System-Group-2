package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private CardLayout alpha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		alpha = new CardLayout();
		mainPanel.setLayout(alpha);
		
		setContentPane(mainPanel);
		
	
		
		dashboardPanel dashboardPanel_ = new dashboardPanel();
		mainPanel.add(dashboardPanel_, "dashboard");
		dashboardPanel_.setLayout(null);
		
		JButton btndashboard = new JButton("Dashboard");
		btndashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alpha.show(mainPanel, "dashboard");
			}
		});
		btndashboard.setBounds(38, 162, 89, 23);
		dashboardPanel_.add(btndashboard);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setBounds(137, 162, 89, 23);
		dashboardPanel_.add(btnInventory);
		
		JButton btnSales = new JButton("Sales");
		btnSales.setBounds(236, 162, 89, 23);
		dashboardPanel_.add(btnSales);
		mainPanel.add(new inventoryPanel(), "inventory");
		mainPanel.add(new salesPanel(), "sales");

	}

}
