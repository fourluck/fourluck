package com.zdd.framework.controller;

import java.util.List;

import com.zdd.framework.annotation.Action;
import com.zdd.framework.annotation.Controller;
import com.zdd.framework.annotation.Inject;
import com.zdd.framework.bean.Param;
import com.zdd.framework.bean.View;
import com.zdd.framework.model.Customer;
import com.zdd.framework.service.CustomerService;

@Controller
public class CustomerController {
	@Inject
	private CustomerService customerService;
	
	@Action("get:/customer")
	public View index(Param param){
		List<Customer> customers = customerService.getCustomerList();
		return new View("customer.jsp").addModel("customers", customers);
	}
	
	@Action("get:/customer_edit")
	public View edit(Param param){
		long id = param.getLong("id");
		Customer customer = customerService.getCustomer(id);
		return new View("customer_edit.jsp").addModel("customer", customer);
	}
}
