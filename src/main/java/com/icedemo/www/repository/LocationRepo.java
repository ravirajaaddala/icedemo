
package com.icedemo.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.icedemo.www.entity.LocationDetails;

/**
 * 
 */
@Repository
public interface LocationRepo extends JpaRepository<LocationDetails, Long>{

	@Query("SELECT l.zipcode FROM LocationDetails l")
    List<String> getAllZipcodes();
	
	LocationDetails findByZipcode(String zipcode);

}
