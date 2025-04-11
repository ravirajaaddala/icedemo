
package com.icedemo.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.icedemo.www.entity.HomeProperty;

/**
 * 
 */
@Repository
public interface HomeProperyRepo extends JpaRepository<HomeProperty, Long>{
	@Query(value="select p.* from home_property p,location_details ld where p.location_id=ld.id and ld.zipcode = ?1",nativeQuery = true)
	public List<HomeProperty> findByZipcode(String zipcode);
}
