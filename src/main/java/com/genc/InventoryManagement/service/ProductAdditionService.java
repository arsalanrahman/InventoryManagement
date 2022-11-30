package com.genc.InventoryManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genc.InventoryManagement.exception.CategoryMismatchException;
import com.genc.InventoryManagement.exception.DuplicateProductFoundException;
import com.genc.InventoryManagement.model.ProductEntity;
import com.genc.InventoryManagement.model.ProductRequest;
import com.genc.InventoryManagement.model.ProductResponse;
import com.genc.InventoryManagement.repository.ProductRepository;

@Service
public class ProductAdditionService {
	@Autowired
	ProductRepository prodRepo;
	
	
	public ProductResponse addProduct(ProductRequest prodReq) {
		ProductEntity savedProduct = new ProductEntity();
		if(checkCategory(prodReq.getCategory()))
			savedProduct.setCategory(prodReq.getCategory());
		else throw new CategoryMismatchException("Category Incorrect");
		if(!checkDuplicateProduct(prodReq.getProductName(), prodReq.getCategory()))
			savedProduct.setProductName(prodReq.getProductName());
		else throw new DuplicateProductFoundException("Duplicate product");
		savedProduct.setShortDescription(prodReq.getShortDescription());
		savedProduct.setDetailedDescription(prodReq.getDetailedDescription());
		savedProduct.setPrice(prodReq.getPrice());
		prodRepo.save(savedProduct);
		return new ProductResponse("Product successfully added", savedProduct);
		
		
	}
	
	public boolean checkCategory(String userCategory) {
		if(userCategory != null) {
		return (userCategory.equals("ELECTRONICS") || userCategory.equals("APPAREL") || 
				userCategory.equals("GROCERY") || userCategory.equals("TRAVEL"));
		}
		
		else throw new CategoryMismatchException(" Category null");
	}
	
	private boolean checkDuplicateProduct(String productName, String productCategory) {
		ProductEntity foundProduct;
		if(prodRepo.findByProductName(productName).isPresent()) {
			foundProduct = prodRepo.findByProductName(productName).get();
			return (foundProduct.getProductName().equals(productName) && foundProduct.getCategory().equals(productCategory));
		}
		else return false;
	}
	
    
}
