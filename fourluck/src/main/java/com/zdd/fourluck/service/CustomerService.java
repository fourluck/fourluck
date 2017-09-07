package com.zdd.fourluck.service;

import java.util.List;
import java.util.Map;

import com.zdd.fourluck.helper.DatabaseHelper;
import com.zdd.fourluck.model.Customer;

public class CustomerService {
	/**
	 * 获取客户列表
	 * @param key
	 * @return
	 */
	public List<Customer> getCustomerList(){
		String sql = "select * from customer";
		return DatabaseHelper.queryEntityList(Customer.class, sql);
	}
	
	/**
	 * 获取客户列表
	 * @param key
	 * @return
	 */
	public Customer getCustomer(long id){
		String sql = "select * from customer where id = ? ";
		return DatabaseHelper.queryEntity(Customer.class, sql,new Object[]{id});
	}
	
	/**
	 * 新增客户
	 * @param customerMap
	 * @return
	 */
	public boolean createCustomer(Map<String,Object> customerMap){
		return DatabaseHelper.insertEntity(Customer.class, customerMap);
	}
	
	/**
	 * 更新客户信息
	 * @param id
	 * @param customerMap
	 * @return
	 */
	public boolean updateCustomer(long id,Map<String,Object> customerMap){
		return DatabaseHelper.updateEntity(Customer.class, id, customerMap);
	}
	
	/**
	 * 删除客户信息
	 * @param id
	 * @return
	 */
	public boolean deleteCustomer(long id){
		return DatabaseHelper.deleteEntity(Customer.class, id);
	}
}
