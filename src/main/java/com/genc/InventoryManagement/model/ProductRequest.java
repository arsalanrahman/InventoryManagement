package com.genc.InventoryManagement.model;

public class ProductRequest {
	private String productName;
	private String shortDescription;
	private String detailedDescription;
	private String category;
	private Double price;
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getDetailedDescription() {
		return detailedDescription;
	}
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public ProductRequest(String productName, String shortDescription, String detailedDescription, String category,
			Double price) {
		super();
		this.productName = productName;
		this.shortDescription = shortDescription;
		this.detailedDescription = detailedDescription;
		this.category = category;
		this.price = price;
	}
	public ProductRequest() {
		
		// TODO Auto-generated constructor stub
	}

}

