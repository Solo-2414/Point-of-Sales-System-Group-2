package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import java.awt.CardLayout;
import javax.swing.border.LineBorder;

import controller.sidebarController;
import helpers.buttonHelper;
import helpers.iconHelper;
import helpers.inputHelper;
import helpers.session;
import helpers.uiHelper;
import panels.addProductPanel;
import panels.dashboardPanel;
import panels.productPanel;
import panels.reportPanel;
import panels.salesTransactionPanel;
import panels.staffaccPanel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class mainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtSearchProductId;
	private CardLayout cLayout;
	public helpers.popupManager popup = new helpers.popupManager();
	public productPanel productPanelInstance;
	public staffaccPanel staffPanelInstance;
	public dashboardPanel dashboardPanelInstance;
	public JPanel mainPanel ;



	/**
	 * Launch the application.
	 */	
	/**
	 * Create the frame.
	 */
	public mainFrame() {
		
		String role = session.currentUser.getRole();
		String username = session.currentUser.getUsername();
		if (!session.isLoggedIn()) {
		    dispose();
		    new loginFrame().setVisible(true);
		    return;
		}
		getContentPane().setLayout(null);
		setResizable(false);
		
		
		JPopupMenu ellipsisMenu = new JPopupMenu();
		ellipsisMenu.setBounds(0, 0, 107, 80);
		ellipsisMenu.setBackground(new Color(255, 255, 255));
		ellipsisMenu.setFont(new Font("Poppins", Font.PLAIN, 13));
		addPopup(getContentPane(), ellipsisMenu);
		ellipsisMenu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		JMenuItem settings = new JMenuItem("Settings");
		settings.setFont(new Font("Poppins", Font.PLAIN, 13));
		ellipsisMenu.add(settings);
		
		JMenuItem account = new JMenuItem("Account");
		account.setFont(new Font("Poppins", Font.PLAIN, 13));
		ellipsisMenu.add(account);
		
		JMenuItem logout = new JMenuItem("Logout");
		logout.setFont(new Font("Poppins", Font.PLAIN, 13));
		ellipsisMenu.add(logout);
		
		
		logout.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {

		        System.out.println("Logout clicked!");

		        session.logout(); 
		        dispose(); // close mainFrame
		        new loginFrame().setVisible(true);
		    }
		});
		
		roundPanel sidebarPanel = new roundPanel(30);
		sidebarPanel.setBounds(0, 69, 245, 612);
		sidebarPanel.setRoundType(roundPanel.RoundType.TOP_RIGHT);
		sidebarPanel.setBackground(new Color(58, 111, 67));
		getContentPane().add(sidebarPanel);
		sidebarPanel.setLayout(null);
		
		mainPanel = new roundPanel(30);      
		mainPanel.setBounds(255, 69, 1009, 612);
		((roundPanel) mainPanel).setRoundType(roundPanel.RoundType.FULL);
		mainPanel.setBackground(Color.WHITE);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new CardLayout(0, 0));
		cLayout = (CardLayout) mainPanel.getLayout();
		sidebarController nav = new sidebarController(cLayout, mainPanel);
		
		dashboardPanelInstance = new dashboardPanel();
		dashboardPanelInstance.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		mainPanel.add(dashboardPanelInstance, "dashboard");

		productPanel proPanel = new productPanel();
		proPanel.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		productPanelInstance = proPanel;
		mainPanel.add(proPanel, "product");
		
		reportPanel repPanel = new reportPanel();
		repPanel.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		mainPanel.add(repPanel, "report");
		
		salesTransactionPanel salesPanel = new salesTransactionPanel();
		salesPanel.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		mainPanel.add(salesPanel, "sales");
		
		staffaccPanel adminPanel = new staffaccPanel();
		adminPanel.setBorder(new roundBorder(20, new Color(107,147,114), 2));
		staffPanelInstance = adminPanel;
		mainPanel.add(adminPanel, "admin");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null); 
		
//This is the header ------------------------------------------------------------------------------------
		JLabel Logo = new JLabel("");
		Logo.setBounds(10, -14, 278, 98);
		Logo.setIcon(iconHelper.load("/img/logo.png", 210, 90));
		getContentPane().add(Logo);
		
		roundtxtfPanel searchPanel = new roundtxtfPanel(40);
		searchPanel.setBounds(253, 11, 827, 40);
		getContentPane().add(searchPanel);

		txtSearchProductId = searchPanel.getTextField();
		String pHolder = "Search Product ID";
		Color pColor = Color.GRAY;
		Color tColor = Color.BLACK;
		inputHelper.attachPlaceholder(txtSearchProductId, pHolder, pColor, tColor);
		
		MouseAdapter globalClick = new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {

		        // If clicked outside text field
		        if (!txtSearchProductId.getBounds().contains(e.getPoint())) {

		            // If empty = restore placeholder
		            if (txtSearchProductId.getText().isEmpty()) {
		                txtSearchProductId.setText(pHolder);
		                txtSearchProductId.setForeground(pColor);
		                txtSearchProductId.getCaret().setVisible(false); 
		            }
		        }
		    }
		};

		uiHelper.addGlobalMouseListener(this.getContentPane(), globalClick);
		
		txtSearchProductId.setHorizontalAlignment(SwingConstants.LEFT);
		txtSearchProductId.setFont(new Font("Poppins", Font.PLAIN, 13));
		txtSearchProductId.setBounds(253, 11, 827, 34);
		txtSearchProductId.setColumns(10);
		
		JToggleButton btnEllipsis = new JToggleButton("");
		btnEllipsis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnEllipsis.isSelected()) {
					ellipsisMenu.show(btnEllipsis, 0, btnEllipsis.getHeight());
										
				}else {
					ellipsisMenu.setVisible(false);
				}				
			}
		});
		btnEllipsis.setBounds(1176, 11, 78, 40);
		btnEllipsis.setOpaque(false);
		btnEllipsis.setFocusPainted(false);
		btnEllipsis.setContentAreaFilled(false);
		btnEllipsis.setBorderPainted(false);
		btnEllipsis.setHorizontalAlignment(SwingConstants.LEFT);
		btnEllipsis.setForeground(Color.WHITE);
		btnEllipsis.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		btnEllipsis.setIcon(iconHelper.load("/img/ellipsis-solid-full.png", 40, 40));
		getContentPane().add(btnEllipsis);
//-------------------------------------------------------------------------------------------------------------------		

//This is the sidebar------------------------------------------------------------------------------------------------
				
		JLabel lblNewLabel = new JLabel("WELCOME "+ username.toUpperCase());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
		lblNewLabel.setBounds(0, 175, 245, 22);
		sidebarPanel.add(lblNewLabel);
		
		JLabel UserPic = new JLabel("");
		UserPic.setIcon(iconHelper.load("/img/user-head.png", 140, 140));
		UserPic.setBounds(48, 36, 140, 126);
		sidebarPanel.add(UserPic);
		
		JButton btnDashboard = new JButton("DASHBOARD");
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				nav.showDashboard();
				dashboardPanelInstance.refreshDashboard();
			}
		});
		btnDashboard.setBounds(10, 221, 213, 35);
		sidebarPanel.add(btnDashboard);
		buttonHelper.apply(btnDashboard, "/img/house-solid-full.png", 40, 40);
		
		JButton btnProductRec = new JButton("PRODUCT RECORDS");
		btnProductRec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nav.showProducts();
			}
		});
		btnProductRec.setBounds(10, 272, 213, 35);
		sidebarPanel.add(btnProductRec);
		buttonHelper.apply(btnProductRec, "/img/cart-shopping-solid-full.png", 40, 40);
		
		JButton btnReports = new JButton("REPORTS");
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nav.showReports();
			}
		});
		btnReports.setBounds(10, 323, 213, 35);
		sidebarPanel.add(btnReports);
		buttonHelper.apply(btnReports, "/img/envelope-solid-full.png", 40, 40);
		
		JButton btnTransaction = new JButton("TRANSACTION");
		btnTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nav.showSales();
			}
		});
		btnTransaction.setBounds(10, 374, 213, 35);
		sidebarPanel.add(btnTransaction);
		buttonHelper.apply(btnTransaction, "/img/receipt-solid-full.png", 40, 40);
		
		JButton btnStaffAcc = new JButton("STAFF ACCOUNTS");
		btnStaffAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nav.showStaffAccounts();
			}
		});
		btnStaffAcc.setBounds(10, 425, 213, 35);
		sidebarPanel.add(btnStaffAcc);
		buttonHelper.apply(btnStaffAcc, "/img/users-solid-full.png", 40, 40);
		
		JButton btnNotif = new JButton("");
		btnNotif.setBounds(1105, 0, 78, 58);
		getContentPane().add(btnNotif);
		btnNotif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNotif.setOpaque(false);
		btnNotif.setFocusPainted(false);
		btnNotif.setContentAreaFilled(false);
		btnNotif.setBorderPainted(false);
		btnNotif.setHorizontalAlignment(SwingConstants.LEFT);
		btnNotif.setForeground(Color.WHITE);
		btnNotif.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		btnNotif.setIcon(iconHelper.load("/img/bell-solid-full.png", 40, 40));
		
		
		
//-------------------------------------------------------------------------------------------------------------------------------------

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	public productPanel getProductPanel() {
		return productPanelInstance;
	}
	public staffaccPanel getstaffaccPanel() {
		// TODO Auto-generated method stub
		return staffPanelInstance;
	}
}

