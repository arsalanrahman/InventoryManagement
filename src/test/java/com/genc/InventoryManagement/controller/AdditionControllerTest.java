package com.genc.InventoryManagement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genc.InventoryManagement.model.ProductEntity;
import com.genc.InventoryManagement.model.ProductRequest;
import com.genc.InventoryManagement.model.ProductResponse;
import com.genc.InventoryManagement.model.ProductUpdateRequest;
import com.genc.InventoryManagement.service.ProductAdditionService;
import com.genc.InventoryManagement.service.ProductUpdateService;


@AutoConfigureMockMvc
@SpringBootTest
public class AdditionControllerTest {

	@Autowired private MockMvc mvc;
	
	@MockBean ProductAdditionService prodAddService;
	@MockBean ProductUpdateService prodUpdateService;
	
	
	//CREATE
	@Test
	public void createProductValidDetails() throws Exception 
	{
		
		ProductUpdateRequest request = new ProductUpdateRequest("dummy666", "short desc", "long desc", 1000.00); //POJO
		when(prodUpdateService.updateProduct(request)).thenReturn(new ProductResponse("Successfully added", 
				new ProductEntity(request.getProductName(), request.getShortDescription(),
						request.getDetailedDescription(), request.getDetailedDescription(), request.getPrice())));
		String jsonRequest = this.mapToJson(request);
		
		
		mvc.perform(post("/p-addition").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	//UPDATE
	@Test
	public void updateProductValidDetails() throws Exception 
	{
		
		ProductRequest request = new ProductRequest("dummy666", "short desc update", "long desc update", "ELECTRONICS", 1000.00); //POJO
		when(prodAddService.addProduct(request)).thenReturn(new ProductResponse("Successfully Updated", 
				new ProductEntity(request.getProductName(), request.getShortDescription(),
						request.getDetailedDescription(), request.getDetailedDescription(), request.getPrice())));
		String jsonRequest = this.mapToJson(request);
		
		
		mvc.perform(put("/p-update").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	
	
	//SEARCH
	@Test
	public void searchProductInvalidCategory() throws Exception 
	{
		mvc.perform(get("/p-search/{prodName}", "dummy1")).andExpect(status().isOk());
	}
	
	//DELETE
	@Test
	public void deleteProductInvalidCategory() throws Exception 
	{
		mvc.perform(delete("/p-delete/{prodName}", "dummy1")).andExpect(status().isOk());
	}
	
	
	public String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	

	 /* @Test public void createProductInvalidName() throws Exception {
	 * 
	 * ProductRequest request = new ProductRequest("dummy444", "short desc",
	 * "long desc", "ELECTRONICS", 1000.00); //POJO String jsonRequest =
	 * this.mapToJson(request); System.out.println(jsonRequest);
	 * mvc.perform(post("/p-addition").content(jsonRequest).contentType(MediaType.
	 * APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)).andExpect(status(
	 * ).is4xxClientError()); }
	 * 
	 * @Test public void createProductInvalidCategory() throws Exception {
	 * 
	 * ProductRequest request = new ProductRequest("dummy666", "short desc",
	 * "long desc", "INVALID", 1000.00); //POJO String jsonRequest =
	 * this.mapToJson(request); System.out.println(jsonRequest);
	 * mvc.perform(post("/p-addition").content(jsonRequest).contentType(MediaType.
	 * APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)).andExpect(status(
	 * ).is4xxClientError()); } */
	 
}
