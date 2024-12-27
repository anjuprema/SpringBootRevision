package com.anju.demo.StaticAnalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anju.demo.Service.DataProviderService;

@RestController
@RequestMapping("/static")
public class StaticAnalysisController {
	
	@Autowired
	DataProviderService dataProvider;
	
	@GetMapping("/getData")
	public void getData() {
		dataProvider.getData();
	}
}
