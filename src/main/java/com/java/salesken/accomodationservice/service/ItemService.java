package com.java.salesken.accomodationservice.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.java.salesken.accomodationservice.entity.Item;
import com.java.salesken.accomodationservice.exception.InvalidItemException;
import com.java.salesken.accomodationservice.exception.ItemNotFoundException;

public interface ItemService {

	List<Item> getAllItems();

	Item getItem(Long id) throws ItemNotFoundException;

	Item addItem(Item item) throws InvalidItemException;

	Map<String, Boolean> deleteItem(long id) throws ItemNotFoundException;

	Item updateItem(Item updateitem) throws ItemNotFoundException, InvalidItemException;

	List<Item> searchItem(Map<String, String> allRequstParam);

}
