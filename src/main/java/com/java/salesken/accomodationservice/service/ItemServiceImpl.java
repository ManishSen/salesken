package com.java.salesken.accomodationservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.java.salesken.accomodationservice.controller.ItemController;
import com.java.salesken.accomodationservice.entity.Item;
import com.java.salesken.accomodationservice.entity.ReputationBadge;
import com.java.salesken.accomodationservice.exception.InvalidItemException;
import com.java.salesken.accomodationservice.exception.ItemNotFoundException;
import com.java.salesken.accomodationservice.repository.ItemRepository;
import com.java.salesken.accomodationservice.utils.StringUtility;
import com.java.salesken.accomodationservice.utils.ValidationUtils;

@Service
public class ItemServiceImpl implements ItemService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class); 
	
	@Autowired
	ItemRepository itemRepository;
	
	public List<Item> getAllItems() {
		LOGGER.info("inside getAll items :" );
		return itemRepository.findAll();
	}

	public Item getItem(Long id) throws ItemNotFoundException{
		LOGGER.info("inside get Item by Id :" );
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Item not found for this id:: "+ id));
		return item;
	}

	public Item addItem(Item item) throws InvalidItemException {
			LOGGER.info("inside if :" );
			List<String> errorList = ValidationUtils.isValidItem(item);
			if(errorList.size()==0){
				LOGGER.info("valid item, will save it in Db:" );
				int reputation = item.getReputation();
				if(reputation<=500){
					item.setReputationBadge(ReputationBadge.RED);
				}else if(reputation>500 && reputation<=799){
					item.setReputationBadge(ReputationBadge.YELLOW);
				}else {
					item.setReputationBadge(ReputationBadge.GREEN);
				}
				LOGGER.info("valid item, will save it in Db:" );
				itemRepository.save(item);
			}else{
				throw new InvalidItemException("Item is invalid and cannot be saved. Following errors found : " + errorList.toString());
			}
			
		LOGGER.info("Added Item  :" );
		return item;
	}

	public Map<String,Boolean> deleteItem(long id) throws ItemNotFoundException {
		LOGGER.info("Deleting Item::::::" + id);
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Item not found for this id:: "+ id));
		 itemRepository.delete(item);
		 Map<String,Boolean> response = new HashMap<>();
		 response.put("deleted", Boolean.TRUE);
		 
		 return response;
		 
	}

	public Item updateItem(Item updateitem) throws ItemNotFoundException, InvalidItemException {
		LOGGER.info("Updating Item:::::: " + updateitem.getId());
		Item item = itemRepository.findById(updateitem.getId())
				.orElseThrow(() -> new ItemNotFoundException("Item not found for this id:: "+updateitem.getId()));
		
		//Validate before update
		List<String> errorList = ValidationUtils.isValidItem(item);
		if(errorList.size()!=0){
			throw new InvalidItemException("Cannot update item as its has some invalid fields. Valid Item should have : " + errorList);
		}
		item.setAvailability(updateitem.getAvailability());
		item.setCategory(updateitem.getCategory());
		item.setImageUrl(updateitem.getImageUrl());
		item.setLocation(updateitem.getLocation());
		item.setName(updateitem.getName());
		item.setPrice(updateitem.getPrice());
		item.setRating(updateitem.getRating());
		item.setReputation(updateitem.getReputation());
		int reputation = updateitem.getReputation();
		if(reputation<=500){
			item.setReputationBadge(ReputationBadge.RED);
		}else if(reputation>500 && reputation<=799){
			item.setReputationBadge(ReputationBadge.YELLOW);
		}else {
			item.setReputationBadge(ReputationBadge.GREEN);
		}
		LOGGER.info("Valid Item will update it:::::: " + updateitem.getId());
		itemRepository.save(item);
		
		return item;
	}
	
	
	@Override
	public List<Item> searchItem(Map<String, String> allRequstParam) {
		LOGGER.info("search item by attributes:::: ");
		if(!CollectionUtils.isEmpty(allRequstParam)){
			for(Entry<String, String> entry : allRequstParam.entrySet()){
				String param = entry.getKey();
				String val = entry.getValue();
				LOGGER.info("search item by attributes:::: "+ param + " value :: " + val);
				if(StringUtility.RATING.equalsIgnoreCase(param) && !StringUtils.isEmpty(val)){
					return itemRepository.findByRating(Integer.parseInt(val));
				}
				if(StringUtility.CATEGORY.equalsIgnoreCase(param) && !StringUtils.isEmpty(val)){
					return itemRepository.findByCategory(val);
				}
				if(StringUtility.CITY.equalsIgnoreCase(param) && !StringUtils.isEmpty(val)){
					return itemRepository.getItemByCity(val);
				}
				if(StringUtility.REPUTATIONBADGE.equalsIgnoreCase(param) && !StringUtils.isEmpty(val)){
					return itemRepository.findByReputationBadge(ReputationBadge.valueOf(val.toUpperCase()));
				}
			}
		}
		return null;
	}

}
