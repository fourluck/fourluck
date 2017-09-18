package com.zdd.springmvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdd.springmvc.idao.IUserDao;
import com.zdd.springmvc.iservice.IUserService;
@Service("userService")
public class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;
	@Override
	public List<Map<String, Object>> queryUserList() {
		// TODO Auto-generated method stub
		return userDao.queryUserList();
	}
	@Override
	public void addUser() throws Exception{
		userDao.addUser();
		userDao.addUser();
	}

}
