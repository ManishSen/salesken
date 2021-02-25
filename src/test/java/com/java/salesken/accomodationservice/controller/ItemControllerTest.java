package com.java.salesken.accomodationservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.skyscreamer.jsonassert.JSONAssert;

import com.java.salesken.accomodationservice.controller.ItemController;
import com.java.salesken.accomodationservice.entity.Item;
import com.java.salesken.accomodationservice.service.ItemService;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class)
@WithMockUser
public class ItemControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemService itemService;
	
	Item item = new Item();
	
	@Test
	public void getItemByIdTest() throws Exception{
		item.setId(1l);
		item.setAvailability(10);
		item.setName("radisson blu hotel");
		item.setPrice(2000);
		item.setRating(5);
		item.setReputation(900);
		Mockito.when(itemService.getItem(Mockito.anyLong())).thenReturn(item);
		
		RequestBuilder requestbuilder = MockMvcRequestBuilders.get("/api/v1/item/1").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestbuilder).andReturn();
		
		System.out.println(result.getResponse());
		
		String expectedJson = "{\"id\":1,\"name\":\"radisson blu hotel\",\"price\":2000}";
        //assertEquals(HttpStatus., response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void getAllItemTest() throws Exception{
		List<Item> itemList = getItemList();
		//Mockito.when(itemService.getAllItems()).thenReturn(itemList);
		given(itemService.getAllItems()).willReturn(itemList);
		RequestBuilder requestbuilder = MockMvcRequestBuilders.get("/api/v1/items").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestbuilder).andReturn();
		
		System.out.println(result.getResponse());
		
		String expectedJson = "{\"id\":1,\"name\":\"radisson blu hotel\",\"price\":2000}";
        //assertEquals(HttpStatus., response.getStatusCode());
        //JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
        
        
        //assertEquals(3, result.getResponse().getContentLength()); // This assert passes
        //assertEquals(itemList, responseBody);
	}

	private List<Item> getItemList() {
		Item item = new Item();
		item.setId(1l);
		item.setAvailability(10);
		item.setName("radisson blu hotel");
		item.setPrice(2000);
		item.setRating(5);
		item.setReputation(900);
		
		Item item1 = new Item();
		item.setId(2l);
		item.setAvailability(15);
		item.setName("Westin Hotel");
		item.setPrice(5000);
		item.setRating(5);
		item.setReputation(990);
		
		Item item2 = new Item();
		item.setId(3l);
		item.setAvailability(100);
		item.setName("Taj hotel");
		item.setPrice(2000);
		item.setRating(4);
		item.setReputation(800);
		
		List <Item> list = new ArrayList<>();
		list.add(item);
		list.add(item1);
		list.add(item2);
		
		return list;
	}

}
