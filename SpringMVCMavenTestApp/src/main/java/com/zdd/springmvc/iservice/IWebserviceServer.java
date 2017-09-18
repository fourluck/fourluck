package com.zdd.springmvc.iservice;

import javax.jws.WebParam;
import javax.jws.WebService;
@WebService
public interface IWebserviceServer {
	int add(@WebParam(name = "firstA")int a, @WebParam(name = "firstB")int b);
    int minus(@WebParam(name = "secondA")int a, @WebParam(name = "secondB")int b);
}
