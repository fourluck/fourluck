package com.zdd.springmvc.iservice;

import java.util.List;
import java.util.Map;

public interface IUserService {

	public List<Map<String, Object>> queryUserList();

	public void addUser()throws Exception;

}
