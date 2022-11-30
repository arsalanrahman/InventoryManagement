package com.genc.InventoryManagement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genc.InventoryManagement.model.ProductRequest;
import com.genc.InventoryManagement.service.ProductAdditionService;


@AutoConfigureMockMvc
@SpringBootTest
public class AdditionControllerTest {

	@Autowired private MockMvc mvc;
	
	@Mock ProductAdditionService prodAddService; 
	

	@Test
	public void createProductValidDetails() throws Exception 
	{
		//when(prodAddService.checkCategory("ELECTRONICS")).thenReturn(true);
		ProductRequest request = new ProductRequest("dummy666", "short desc", "long desc", "ELECTRONICS", 1000.00); //POJO
		String jsonRequest = this.mapToJson(request);
		System.out.println(jsonRequest);
		mvc.perform(post("/p-addition").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void createProductInvalidName() throws Exception 
	{
		
		ProductRequest request = new ProductRequest("dummy444", "short desc", "long desc", "ELECTRONICS", 1000.00); //POJO
		String jsonRequest = this.mapToJson(request);
		System.out.println(jsonRequest);
		mvc.perform(post("/p-addition").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void createProductInvalidCategory() throws Exception 
	{
		
		ProductRequest request = new ProductRequest("dummy666", "short desc", "long desc", "INVALID", 1000.00); //POJO
		String jsonRequest = this.mapToJson(request);
		System.out.println(jsonRequest);
		mvc.perform(post("/p-addition").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
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
		mvc.perform(delete("/p-delete/{prodName}", "dummy555")).andExpect(status().isOk());
	}
	
	
	public String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
