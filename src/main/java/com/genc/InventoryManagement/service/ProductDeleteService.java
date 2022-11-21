package com.genc.InventoryManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genc.InventoryManagement.exception.ProductNotFoundException;
import com.genc.InventoryManagement.model.ProductEntity;
import com.genc.InventoryManagement.repository.ProductRepository;

@Service
public class ProductDeleteService {
	@Autowired
	ProductRepository prodRepo;
	
	public ProductEntity deleteProduct(String prodName) {
		ProductEntity foundProduct = prodRepo.findByProductName(prodName).orElseThrow(()->new ProductNotFoundException("Product not found for deleting"));
		prodRepo.delete(foundProduct);
		return foundProduct;
		
	}	

}
