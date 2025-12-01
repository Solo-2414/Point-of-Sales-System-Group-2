package model;

public class product {
	private int prodId;
    private String name;
    private String desc;
    private double price;
    private int reorderLevel;
    private int stockQuantity;
    
    public int getProdID() {
    	return prodId;
    }
    public void setProdID(int id) {
    	this.prodId = id;
    }
    public String getName() { 
    	return name; 
    }
    public void setName(String n) { 
    	this.name = n; 
    }
    public String getDescription() { 
    	return desc; 
    }
    public void setDescription(String d) { 
    	this.desc = d; 
    }
    public double getPrice() { 
    	return price; 
    }
    public void setPrice(double p) { 
    	this.price = p; 
    }
    public int getReorderLevel() { 
    	return reorderLevel; 
    }
    public void setReorderLevel(int rl) { 
    	this.reorderLevel = rl; 
    }
    public int getStockQuantity() {
    	return stockQuantity; 
    }
    public void setStockQuantity(int s) { 
    	this.stockQuantity = s; 
    }
}
