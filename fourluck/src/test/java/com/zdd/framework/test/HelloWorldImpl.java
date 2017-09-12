package com.zdd.framework.test;

import com.zdd.framework.iTest.HelloWorld;

public class HelloWorldImpl implements HelloWorld {

	@Override
	public void sayHello() {
		System.out.println("Hello World!!!");
	}

}
