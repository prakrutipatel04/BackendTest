package com.example.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

	@Autowired
	TestService testservice;
	
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public void startMonitoring(@RequestBody RequestObject reqObj) {
		testservice.StartMonitoring(reqObj.getDelay());
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public List<ResponseObject> stopMonitoring() {
		return testservice.StopMonitoring();
	}
}
