package com.genc.InventoryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.genc.InventoryManagement.model.ProductEntity;
import com.genc.InventoryManagement.service.ProductDeleteService;

@RestController
@CrossOrigin("http://localhost:4200")
public class DeleteController {
	@Autowired
	ProductDeleteService prodService;
	
	@DeleteMapping("/p-delete/{prodName}")
	public ResponseEntity<ProductEntity> deleteController(@PathVariable String prodName) {
		
		return new ResponseEntity<ProductEntity>(prodService.deleteProduct(prodName),HttpStatus.OK);

		
		
	}
}
