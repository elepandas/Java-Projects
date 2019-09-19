package com.uto.ram.onbord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uto.ram.onbord.model.pro.ProTab;
import com.uto.ram.onbord.model.qa.QaTab;
import com.uto.ram.onbord.pro.repository.ProRepository;
import com.uto.ram.onbord.qa.repository.QaRepository;


@SpringBootApplication
@RestController
public class TurOnBandMetaDataCheckApplication {
	
	@Autowired
	private ProRepository proRepository;
	
	@Autowired
	private QaRepository qaRepository;
	
	@GetMapping("/getAllTrbPro")
	public List<ProTab> getProTableOnBoardRecords(){
		return proRepository.findAll();
		
	}
	
	@GetMapping("/getAllTrbQa")
	public List<QaTab> getQaTableOnBoardRecords(){
		return qaRepository.findAll();
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TurOnBandMetaDataCheckApplication.class, args);
	}

}
