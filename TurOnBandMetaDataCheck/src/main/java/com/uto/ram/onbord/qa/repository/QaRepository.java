package com.uto.ram.onbord.qa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uto.ram.onbord.model.qa.QaTab;

@Repository
public interface QaRepository extends JpaRepository<QaTab, Long>{

}
