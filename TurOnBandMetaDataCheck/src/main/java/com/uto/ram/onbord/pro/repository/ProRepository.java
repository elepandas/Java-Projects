package com.uto.ram.onbord.pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uto.ram.onbord.model.pro.ProTab;

@Repository
public interface ProRepository extends JpaRepository<ProTab, Long> {
	
	

}
