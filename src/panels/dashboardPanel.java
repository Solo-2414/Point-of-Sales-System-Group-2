package panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import dao.productDao;
import helpers.session;
import main.roundBorder;
import main.roundPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class dashboardPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel lblSalesCount;
    private JLabel lblProdCount;
    private JLabel lblLowStock;

    public dashboardPanel() {
    	int lowStock = 0;
        int totalProducts = 0;
        double totalSales = 0.0;
        String username = "User";

        if (!java.beans.Beans.isDesignTime()) {
            productDao dao = new productDao();
            lowStock = dao.getLowStockCount();
            totalProducts = dao.getTotalProducts();
            totalSales = dao.getTotalSales();

            if (session.currentUser != null) {
                username = session.currentUser.getUsername();
            }
        }

        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1010, 1097));

        // --------------------------------------------------------
        //  SCROLLPANE
        // --------------------------------------------------------
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 1010, 682);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        add(scrollPane);

        // --------------------------------------------------------
        //  CONTENT PANEL (EVERYTHING GOES HERE)
        // --------------------------------------------------------
        JPanel content = new JPanel();
        content.setLayout(null);
        content.setPreferredSize(new Dimension(1010, 1100)); // extendable height
        scrollPane.setViewportView(content);

        // --------------------------------------------------------
        //  HEADER PANEL
        // --------------------------------------------------------
        roundPanel header = new roundPanel(20);
        header.setBackground(new Color(58, 111, 67));
        header.setBounds(0, 0, 1010, 140);
        header.setLayout(null);
        content.add(header);

        JLabel lblWelcome = new JLabel("Hi! Welcome Back, " + username + "!");
        lblWelcome.setFont(new Font("Poppins", Font.BOLD, 22));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBounds(24, 28, 400, 40);
        header.add(lblWelcome);

        JLabel lblSub = new JLabel("Here's what's happening with your inventory today");
        lblSub.setFont(new Font("Poppins", Font.PLAIN, 14));
        lblSub.setForeground(Color.WHITE);
        lblSub.setBounds(24, 68, 450, 40);
        header.add(lblSub);

        // --------------------------------------------------------
        //  LEFT CARD (SALES)
        // --------------------------------------------------------
        roundPanel cardSales = new roundPanel(20);
        cardSales.setBackground(Color.WHITE);
        cardSales.setBorder(new roundBorder(20, new Color(107,147,114), 2));
        cardSales.setLayout(null);
        cardSales.setBounds(34, 178, 278, 120);
        content.add(cardSales);

        lblSalesCount = new JLabel("₱" + String.format("%,.2f", totalSales));
        lblSalesCount.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblSalesCount.setBounds(20, 20, 150, 40);
        cardSales.add(lblSalesCount);

        JLabel lblSalesTxt = new JLabel("Total Sales");
        lblSalesTxt.setFont(new Font("Poppins", Font.PLAIN, 14));
        lblSalesTxt.setBounds(20, 60, 150, 30);
        cardSales.add(lblSalesTxt);

        roundPanel percentageSales = new roundPanel(20);
        percentageSales.setBackground(new Color(58,111,67));
        percentageSales.setBounds(180, 11, 70, 21);
        percentageSales.setLayout(null);
        percentageSales.setBorder(new roundBorder(20, new Color(107,147,114), 2));
        cardSales.add(percentageSales);

        JLabel lblSalesPercent = new JLabel("+8%");
        lblSalesPercent.setFont(new Font("Poppins", Font.BOLD, 11));
        lblSalesPercent.setForeground(Color.WHITE);
        lblSalesPercent.setBounds(10, 0, 40, 25);
        percentageSales.add(lblSalesPercent);

        JLabel iconUp2 = new JLabel("↑");
        iconUp2.setFont(new Font("Poppins", Font.BOLD, 14));
        iconUp2.setForeground(Color.WHITE);
        iconUp2.setBounds(40, -1, 20, 25);
        percentageSales.add(iconUp2);

        // --------------------------------------------------------
        //  MIDDLE CARD (PRODUCTS)
        // --------------------------------------------------------
        roundPanel cardProducts = new roundPanel(20);
        cardProducts.setBackground(Color.WHITE);
        cardProducts.setBorder(new roundBorder(20, new Color(107,147,114), 2));
        cardProducts.setBounds(355, 178, 278, 120);
        cardProducts.setLayout(null);
        content.add(cardProducts);

        lblProdCount = new JLabel(String.valueOf(totalProducts));
        lblProdCount.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblProdCount.setBounds(20, 20, 150, 40);
        cardProducts.add(lblProdCount);

        JLabel lblProdTxt = new JLabel("Total Products");
        lblProdTxt.setFont(new Font("Poppins", Font.PLAIN, 14));
        lblProdTxt.setBounds(20, 60, 150, 30);
        cardProducts.add(lblProdTxt);

        roundPanel percentageProd = new roundPanel(20);
        percentageProd.setBackground(new Color(58,111,67));
        percentageProd.setBounds(180, 11, 70, 20);
        percentageProd.setLayout(null);
        percentageProd.setBorder(new LineBorder(new Color(58,111,67), 2));
        cardProducts.add(percentageProd);

        JLabel lblProdPercent = new JLabel("+12%");
        lblProdPercent.setFont(new Font("Poppins", Font.BOLD, 11));
        lblProdPercent.setForeground(Color.WHITE);
        lblProdPercent.setBounds(10, 0, 40, 25);
        percentageProd.add(lblProdPercent);

        JLabel iconUp1 = new JLabel("↑");
        iconUp1.setFont(new Font("Poppins", Font.BOLD, 14));
        iconUp1.setForeground(Color.WHITE);
        iconUp1.setBounds(40, -1, 20, 25);
        percentageProd.add(iconUp1);

        // --------------------------------------------------------
        //  RIGHT EMPTY CARD
        // --------------------------------------------------------
        roundPanel cardEmpty2 = new roundPanel(20);
        cardEmpty2.setBackground(Color.WHITE);
        cardEmpty2.setBorder(new roundBorder(20, new Color(107,147,114), 2));
        cardEmpty2.setLayout(null);
        cardEmpty2.setBounds(683, 178, 278, 120);
        content.add(cardEmpty2);
        
        JLabel lblLowStockLevels = new JLabel("Low Stock Levels");
        lblLowStockLevels.setFont(new Font("Poppins", Font.PLAIN, 14));
        lblLowStockLevels.setBounds(22, 62, 150, 30);
        cardEmpty2.add(lblLowStockLevels);
        
        lblLowStock = new JLabel(String.valueOf(lowStock));
        lblLowStock.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblLowStock.setBounds(22, 22, 150, 40);
        cardEmpty2.add(lblLowStock);

        // --------------------------------------------------------
        //  CHART PANEL
        // --------------------------------------------------------
        roundPanel chartPanel = new roundPanel(20);
        chartPanel.setBackground(new Color(225, 240, 225));
        chartPanel.setBorder(new LineBorder(new Color(200,200,200), 2));
        chartPanel.setBounds(34, 349, 927, 332);
        chartPanel.setLayout(null);
        content.add(chartPanel);

        JLabel lblChartTxt = new JLabel("Sales Overview (Chart Placeholder)");
        lblChartTxt.setFont(new Font("Poppins", Font.PLAIN, 14));
        lblChartTxt.setBounds(20, 10, 300, 30);
        chartPanel.add(lblChartTxt);

        JPanel bar1 = new JPanel();
        bar1.setBackground(new Color(58,111,67));
        bar1.setBounds(73, 120, 40, 24);
        chartPanel.add(bar1);

        JPanel bar2 = new JPanel();
        bar2.setBackground(new Color(98,140,88));
        bar2.setBounds(140, 80, 40, 64);
        chartPanel.add(bar2);

        JPanel bar3 = new JPanel();
        bar3.setBackground(new Color(58,111,67));
        bar3.setBounds(220, 106, 40, 38);
        chartPanel.add(bar3);

        JPanel bar4 = new JPanel();
        bar4.setBackground(new Color(98,140,88));
        bar4.setBounds(300, 60, 40, 84);
        chartPanel.add(bar4);

        JPanel bar5 = new JPanel();
        bar5.setBackground(new Color(58,111,67));
        bar5.setBounds(380, 100, 40, 44);
        chartPanel.add(bar5);
    }
    public void refreshDashboard() {
        productDao pDao = new productDao();

        int lowStock = pDao.getLowStockCount();
        int totalProducts = pDao.getTotalProducts();
        double totalSales = pDao.getTotalSales();

        lblSalesCount.setText("₱" + String.format("%,.2f", totalSales));
        lblProdCount.setText(String.valueOf(totalProducts));
        lblLowStock.setText(String.valueOf(lowStock));
    }
}
