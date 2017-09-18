package com.zdd.framework.service;

import java.util.List;
import java.util.Map;

import com.zdd.framework.annotation.Service;
import com.zdd.framework.annotation.Transaction;
import com.zdd.framework.bean.FileParam;
import com.zdd.framework.helper.DatabaseHelper;
import com.zdd.framework.helper.UploadHelper;
import com.zdd.framework.model.Customer;

@Service
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
	@Transaction
	public boolean createCustomer(Map<String,Object> customerMap){
		return DatabaseHelper.insertEntity(Customer.class, customerMap);
	}
	
	/**
	 * 更新客户信息
	 * @param id
	 * @param customerMap
	 * @return
	 */
	@Transaction
	public boolean updateCustomer(long id,Map<String,Object> customerMap){
		return DatabaseHelper.updateEntity(Customer.class, id, customerMap);
	}
	
	/**
	 * 删除客户信息
	 * @param id
	 * @return
	 */
	@Transaction
	public boolean deleteCustomer(long id){
		return DatabaseHelper.deleteEntity(Customer.class, id);
	}

	/**
	 * 新增客户
	 * @param customerMap
	 * @return
	 */
	@Transaction
	public boolean createCustomer(Map<String, Object> customerMap, FileParam fileParam) {
		boolean result = DatabaseHelper.insertEntity(Customer.class, customerMap);
		if(result){
			UploadHelper.uploadFile("/tmp/upload/", fileParam);
		}
		return result;
	}
}
