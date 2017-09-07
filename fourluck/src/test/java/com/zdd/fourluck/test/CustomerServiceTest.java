package com.zdd.fourluck.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.zdd.fourluck.helper.DatabaseHelper;
import com.zdd.fourluck.model.Customer;
import com.zdd.fourluck.service.CustomerService;

import junit.framework.Assert;

public class CustomerServiceTest {
	private CustomerService customerService;
	
	public CustomerServiceTest(){
		this.customerService = new CustomerService();
	}
	
	@Before
	public void init(){
		DatabaseHelper.executeSqlFile("sql/customer_init.sql");
	}
	
	@Test
	public void createCustomerTest()throws Exception{
		Map<String,Object> customerMap = new HashMap<String,Object>();
		customerMap.put("name", "customer100");
		customerMap.put("contact", "John");
		customerMap.put("telephone", "15623232156");
		boolean result = customerService.createCustomer(customerMap);
		Assert.assertTrue(result);
	}
	
	@Test
	public void deleteCustomerTest()throws Exception{
		Assert.assertTrue(customerService.deleteCustomer(1));
	}
	
	@Test
	public void getCustomerTest()throws Exception{
		List<Customer>  customers = customerService.getCustomerList();
		Assert.assertNotNull(customers);
	}
	
	@Test
	public void updateCustomerTest()throws Exception{
		long id = 1;
		Map<String,Object> customerMap = new HashMap<String,Object>();
		customerMap.put("name", "customer100");
		customerMap.put("contact", "John");
		customerMap.put("telephone", "15623232156");
		boolean result = customerService.updateCustomer(id, customerMap);
		Assert.assertTrue(result);
	}
}
