package com.tcl.car.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcl.car.model.PrivatePreOwnerCar;


@Service
@Transactional
public interface PrivatePreOwnerCarRepository extends JpaRepository<PrivatePreOwnerCar, Integer>
{

	@Query("select p from PrivatePreOwnerCar p")
	List<PrivatePreOwnerCar> findAll();

	@Query("select count(*) from PrivatePreOwnerCar m where m.url=?1")
	int hasThisRecord(String url);
	
//	@Query("select p from PrivatePreOwnerCar p where (select DATEDIFF(p.searchdate,?1)<?2)")
//	List<PrivatePreOwnerCar> findLatest(Date date,int days);
	
	
	//@Query("select p from PrivatePreOwnerCar p where p.")
}
