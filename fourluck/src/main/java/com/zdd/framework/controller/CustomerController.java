package com.zdd.framework.controller;

import java.util.List;
import java.util.Map;

import com.zdd.framework.annotation.Action;
import com.zdd.framework.annotation.Controller;
import com.zdd.framework.annotation.Inject;
import com.zdd.framework.bean.Data;
import com.zdd.framework.bean.FileParam;
import com.zdd.framework.bean.Param;
import com.zdd.framework.bean.View;
import com.zdd.framework.model.Customer;
import com.zdd.framework.service.CustomerService;

@Controller
public class CustomerController {
	@Inject
	private CustomerService customerService;
	
	@Action("get:/customer")
	public View index(){
		List<Customer> customers = customerService.getCustomerList();
		return new View("customer.jsp").addModel("customers", customers);
	}
	
	@Action("get:/customer_create")
	public View create(){
		return new View("customer_create.jsp");
	}
	
	@Action("post:/customer_create")
	public Data createDo(Param param){
		Map<String,Object> customerMap = param.getFieldMap();
		FileParam fileParam = param.getFile("photo");
		boolean result = customerService.createCustomer(customerMap,fileParam);
		return new Data(result);
	}
	
	@Action("get:/customer_edit")
	public View edit(Param param){
		long id = param.getLong("id");
		Customer customer = customerService.getCustomer(id);
		return new View("customer_edit.jsp").addModel("customer", customer);
	}
	
	@Action("post:/customer_edit")
	public Data editDo(Param param){
		long id = param.getLong("id");
		Map<String,Object> customerMap = param.getFieldMap();
		boolean result = customerService.updateCustomer(id, customerMap);
		return new Data(result);
	}
}
