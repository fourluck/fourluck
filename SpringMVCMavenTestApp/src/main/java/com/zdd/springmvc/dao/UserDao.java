package com.zdd.springmvc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zdd.springmvc.idao.IUserDao;
@Repository("userDap")
public class UserDao implements IUserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> queryUserList() {
		String sql = "SELECT * FROM TS_USER";
		return this.jdbcTemplate.queryForList(sql);
	}

	@Override
	public void addUser() {
		String sql = "INSERT INTO TS_USER VALUES('10001','张顺鹏','zsp')";
		this.jdbcTemplate.update(sql);
	}

}
