package com.java.salesken.accomodationservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.java.salesken.accomodationservice.entity.Item;
import com.java.salesken.accomodationservice.entity.Location;
import com.java.salesken.accomodationservice.exception.InvalidItemException;
import com.java.salesken.accomodationservice.exception.ItemNotFoundException;
import com.java.salesken.accomodationservice.repository.ItemRepository;
import com.java.salesken.accomodationservice.service.ItemService;
import com.java.salesken.accomodationservice.utils.ValidationUtils;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemService.class)
@WithMockUser
public class ItemServiceTest {

	@Autowired
	private ItemService itemService;
	
	@MockBean 
	private ItemRepository itemRepository;

	@MockBean 
	private ValidationUtils validationUtils;
	
	@Test
	public void getAllItemTest(){
		when(itemRepository.findAll()).thenReturn(getItemList());
		
		assertEquals(3, itemService.getAllItems().size());
	}
	
	@Test
	public void getItemById() throws ItemNotFoundException{
		when(itemRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getItem()));
		assertEquals("radisson blu hotel", itemService.getItem(1L).getName());
	}
	
	
	@Test
	public void addItemTest() throws InvalidItemException{
		Item item = getItem();
		when(itemRepository.save(item)).thenReturn(item);
		assertEquals(item, itemService.addItem(item));
	}
	
	@Test
	public void deleteItemTest(){
		Item item = getItem();
		when(itemRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(item));
		itemRepository.delete(item);
		verify(itemRepository,times(1)).delete(item);
		
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
	
	private Item getItem(){
		Item item = new Item();
		item.setId(1l);
		item.setAvailability(10);
		item.setName("radisson blu hotel");
		item.setPrice(2000);
		item.setRating(5);
		item.setReputation(900);
		item.setCategory("Hotel");
		Location loc = new Location();
		loc.setZip_code(12345);
		item.setLocation(loc);
		return item;
	}
	
	
}
