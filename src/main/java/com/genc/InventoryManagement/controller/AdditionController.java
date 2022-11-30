package com.genc.InventoryManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.genc.InventoryManagement.model.ProductRequest;
import com.genc.InventoryManagement.model.ProductResponse;
import com.genc.InventoryManagement.service.ProductAdditionService;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdditionController {
	
	
	@Autowired
	ProductAdditionService prodService;
	@PostMapping(value="/p-addition")
	public ResponseEntity<ProductResponse> addController(@RequestBody ProductRequest prodReq) {
		System.out.println(prodReq.getProductName());
		return new ResponseEntity<ProductResponse>(prodService.addProduct(prodReq), HttpStatus.OK);
		
	}

}
