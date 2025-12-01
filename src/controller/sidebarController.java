package controller;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class sidebarController {

	  private CardLayout layout;
	  private JPanel mainPanel;

	  public sidebarController(CardLayout layout, JPanel mainPanel) {
	      this.layout = layout;
	      this.mainPanel = mainPanel;
	  }

	  public void showDashboard() {
	      layout.show(mainPanel, "dashboard");
	      
	  }

	  public void showProducts() {
	      layout.show(mainPanel, "product");
	  }

	  public void showReports() {
	      layout.show(mainPanel, "report");
	  }
	  public void showSales() {
	      layout.show(mainPanel, "sales");
	  }

	  public void showStaffAccounts() {
	      layout.show(mainPanel, "admin");
	  }

}
