package com.genc.InventoryManagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
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
	void testMockMvcNotNull() {
		assertThat(mvc).isNotNull();
	}
	
	@Test
	public void createEmployeeAPI() throws Exception 
	{
		//when(prodAddService.checkCategory("ELECTRONICS")).thenReturn(true);
		ProductRequest request = new ProductRequest("dummy1", "short desc", "long desc", "ELECTRONICS", 1000.00); //POJO
		String jsonRequest = this.mapToJson(request);
		System.out.println(jsonRequest);
		mvc.perform(post("/p-addition").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		
		
	}
	
	/*
	 * {
	 * 	prodName: dummy1,
	 * 
	 * }
	 */
	public String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
