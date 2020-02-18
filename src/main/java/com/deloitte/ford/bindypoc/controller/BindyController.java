package com.deloitte.ford.bindypoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.ford.bindypoc.model.Order;
import com.deloitte.ford.bindypoc.service.BindyService;

@RestController
public class BindyController {

	@Autowired
	private BindyService bindyService;
	
	@GetMapping("/transform")
	@ResponseBody
	public Order getOrder() throws Exception {
		return bindyService.getOrder();
	}
}
