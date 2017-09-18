package com.zdd.springmvc.service;

import javax.jws.WebService;

import com.zdd.springmvc.iservice.IWebserviceServer;
@WebService(endpointInterface = "com.zdd.springmvc.iservice.IWebserviceServer")
public class WebserviceServer implements IWebserviceServer {

	@Override
	public int add(int a, int b) {
		System.out.println(a+"+"+b+"="+(a+b));
        return a+b;
	}

	@Override
	public int minus(int a, int b) {
		System.out.println(a+"-"+b+"="+(a-b));
        return a-b;
	}

}
