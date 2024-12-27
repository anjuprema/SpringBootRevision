package com.anju.demo.Service;

import org.springframework.stereotype.Service;

@Service
public class DataProviderService {
	public void getData() {
		Object obj = getStringData();
		System.out.println(obj.toString());
	}
	
	private Object getStringData() {
		return null;
	}
}
