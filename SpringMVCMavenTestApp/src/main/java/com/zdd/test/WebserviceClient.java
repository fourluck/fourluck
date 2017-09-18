package com.zdd.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.zdd.springmvc.iservice.IWebserviceServer;

public class WebserviceClient {

	public static void main(String[] args) {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setServiceClass(IWebserviceServer.class);
		factoryBean.setAddress("http://127.0.0.1:8081/manyTest/server/web-publish?wsdl");
		IWebserviceServer webserviceServer = (IWebserviceServer) factoryBean.create();
		webserviceServer.add(2, 5);
	}
}
