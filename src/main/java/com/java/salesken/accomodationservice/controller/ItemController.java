package com.java.salesken.accomodationservice.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.salesken.accomodationservice.entity.Item;
import com.java.salesken.accomodationservice.exception.InvalidItemException;
import com.java.salesken.accomodationservice.exception.ItemNotFoundException;
import com.java.salesken.accomodationservice.service.ItemService;

@RestController
@RequestMapping("/api/v1/")
public class ItemController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	
	
	@GetMapping("items")
	public List<Item> getAllItems(){
		return itemService.getAllItems();
	}
	
	@GetMapping("item/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable(value="id") Long id) throws ItemNotFoundException {
			LOGGER.info("Searching with Item id :" + id);
			Item item =itemService.getItem(id);
			return ResponseEntity.ok().body(item);
	}
	
	@PostMapping("item")
	public Item addItem(@RequestBody Item item) throws InvalidItemException{
		LOGGER.info("Adding Item  :" );
		return itemService.addItem(item);
	}
	
	@PutMapping("item")
	public ResponseEntity<Item> updateItem(@RequestBody Item newItem) throws ItemNotFoundException, InvalidItemException{
		LOGGER.info("Searching with Item id :" + newItem.toString());
		Item item =itemService.updateItem(newItem);
		return ResponseEntity.ok().body(item);
	}
	
	@DeleteMapping("/item/{id}")
	public Map<String,Boolean> deleteItem(@PathVariable("id") long id) throws ItemNotFoundException{
		  return itemService.deleteItem(id);
	}
	
	@GetMapping(value="/item")
	public List<Item> searchItem(@RequestParam Map<String,String> allRequstParam) throws Exception {
		try {
			LOGGER.info("Searching with Item attributes :" + allRequstParam.toString());
			return itemService.searchItem(allRequstParam);
		}
		 catch(Exception e)
			{
				LOGGER.error("Searching with Item attributes :" + allRequstParam.toString());
				LOGGER.error("Exception " + e.getMessage());
				throw new Exception("We are sorry, It Seems some issue at our end.Please try after some time.");
			}
	}

	
	

}
