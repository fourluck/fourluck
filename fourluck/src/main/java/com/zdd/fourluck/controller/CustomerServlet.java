package com.zdd.fourluck.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zdd.fourluck.model.Customer;
import com.zdd.fourluck.service.CustomerService;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -958030321520184239L;

	private CustomerService customerService;
	
	@Override
	public void init() throws ServletException {
		customerService = new CustomerService();
	}
	/**
	 * 跳转 客户列表 界面
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Customer> customers = customerService.getCustomerList();
		req.setAttribute("customers", customers);
		req.getRequestDispatcher("/WEB-INF/page/customer.jsp").forward(req, resp);
	}

}
