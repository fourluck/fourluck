package com.zdd.springmvc.idao;

import java.util.List;
import java.util.Map;

public interface IUserDao {

	public List<Map<String, Object>> queryUserList();

	public void addUser();

}
