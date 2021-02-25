package com.java.salesken.accomodationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.salesken.accomodationservice.entity.Item;
import com.java.salesken.accomodationservice.entity.ReputationBadge;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
	
	public List<Item> findByRating(int rating);
	public List<Item> findByCategory(String category);
	public List<Item> findByReputationBadge(ReputationBadge reputationBadge);
	public List<Item> findByAvailability(int availability);
	
	@Query(value="select * from saleskendb.item i join saleskendb.location l" + 
			" on i.location_id = l.id  and l.city=:city", nativeQuery=true)
	public List<Item> getItemByCity(@Param("city") String city);

}
