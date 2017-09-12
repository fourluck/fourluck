package com.zdd.framework.test;

import com.zdd.framework.iTest.HelloWorld;

public class AppTest {

	public static void main(String[] args) {
		DymicProxy dymicProxy = new DymicProxy(new HelloWorldImpl());
		HelloWorld helloWorldProxy = dymicProxy.getProxy();
		helloWorldProxy.sayHello();
		
		CGLibProxy cgLibProxy = CGLibProxy.getInstance();
		cgLibProxy.getProxy(HelloWorldImpl.class).sayHello();
	}

}
