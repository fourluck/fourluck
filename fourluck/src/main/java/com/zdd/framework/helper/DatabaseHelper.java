package com.zdd.framework.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdd.framework.util.CollectionUtil;
import com.zdd.framework.util.PropsUtil;


public class DatabaseHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	private static final QueryRunner QUERY_RUNNER;
	private static final ThreadLocal<Connection> CONNECTION_HOLDER;
	private static final BasicDataSource DATA_SOURCE;
	
	static {
		CONNECTION_HOLDER = new ThreadLocal<Connection>();
		
		QUERY_RUNNER = new QueryRunner();
		
		String driver = ConfigHelper.getJdbcDriver();
		String url = ConfigHelper.getJdbcUrl();
		String username = ConfigHelper.getJdbcUsername();
		String password = ConfigHelper.getJdbcPassword();
		
		DATA_SOURCE = new BasicDataSource();
		DATA_SOURCE.setDriverClassName(driver);
		DATA_SOURCE.setUrl(url);
		DATA_SOURCE.setUsername(username);
		DATA_SOURCE.setPassword(password);
	}
	
	public static void executeSqlFile(String filePath){
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			String sql;
			while((sql = reader.readLine()) != null){
				executeUpdate(sql);
			}
		} catch (Exception e) {
			LOGGER.error("execute sql file failure",e);
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询实体列表
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T>  queryEntityList(Class<T> entityClass,String sql,Object... params){
		List<T> entityList;
		try {
			Connection connection = getConnection();
			entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass),params);
		} catch (Exception e) {
			LOGGER.error("query entityList failure",e);
			throw new RuntimeException(e);
		}
		return entityList;
	}
	
	/**
	 * 查询单个实体
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T  queryEntity(Class<T> entityClass,String sql,Object... params){
		T entity;
		try {
			Connection connection = getConnection();
			entity = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass),params);
		} catch (Exception e) {
			LOGGER.error("query entity failure",e);
			throw new RuntimeException(e);
		}
		return entity;
	}
	
	/**
	 * 查询单行记录单个字段
	 * @param sql
	 * @param params
	 * @return
	 */
	public static String query(String sql,Object... params){
		String result = "";
		Map<String,Object> resultMap = executeQueryMap(sql,params);
		if(CollectionUtil.isNotEmpty(resultMap)){
			Object resultArray[] = resultMap.values().toArray();
			result = (String) resultArray[0];
		}
		return result;
	}
	
	/**
	 * 查询多行记录单个字段
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Set<String> querySet(String sql,Object... params){
		Set<String> result = new HashSet<String>();
		List<Map<String,Object>> resultList = executeQuery(sql,params);
		if(CollectionUtil.isNotEmpty(resultList)){
			for(Map<String,Object> resultMap : resultList){
				if(CollectionUtil.isNotEmpty(resultMap)){
					Object resultArray[] = resultMap.values().toArray();
					result.add((String) resultArray[0]);
				}
			}
		}
		return result;
	}
	/**
	 * 执行查询语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String,Object>> executeQuery(String sql,Object... params){
		List<Map<String,Object>> result;
		try {
			Connection connection = getConnection();
			result = QUERY_RUNNER.query(connection, sql, new MapListHandler(),params);
		} catch (Exception e) {
			LOGGER.error("execute query entity list failure",e);
			throw new RuntimeException(e);
		}
		return result;
	}
	/**
	 * 执行查询语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Map<String,Object> executeQueryMap(String sql,Object... params){
		Map<String,Object> result;
		try {
			Connection connection = getConnection();
			result = QUERY_RUNNER.query(connection, sql, new MapHandler(),params);
		} catch (Exception e) {
			LOGGER.error("execute query entity map failure",e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * 执行更新语句（insert、update、delete）
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql,Object... params){
		int rows = 0;
		try {
			Connection connection = getConnection();
			rows = QUERY_RUNNER.update(connection, sql, params);
		} catch (Exception e) {
			LOGGER.error("execute update entity failure",e);
			throw new RuntimeException(e);
		}
		return rows;
	}
	
	/**
	 * 新增实体
	 * @param entityClass
	 * @param fieldMap
	 * @return
	 */
	public static <T> boolean insertEntity(Class<T> entityClass,Map<String,Object> fieldMap){
		if(CollectionUtil.isEmpty(fieldMap)){
			LOGGER.error("can not insert entity: fieldMap is empty");
			return false;
		}
		String sql = "insert into "+getTableName(entityClass);
		StringBuilder columns = new StringBuilder("(");
		StringBuilder values = new StringBuilder("(");
		for(String filedName : fieldMap.keySet()){
			columns.append(filedName).append(", ");
			values.append("?, ");
		}
		columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
		values.replace(values.lastIndexOf(", "), values.length(), ")");
		sql += columns + " values " + values;
		
		Object[] params = fieldMap.values().toArray();
		return executeUpdate(sql, params) == 1;
	}
	
	public static <T> boolean updateEntity(Class<T> entityClass,long id,Map<String,Object> fieldMap){
		if(CollectionUtil.isEmpty(fieldMap)){
			LOGGER.error("can not update entity: fieldMap is empty");
			return false;
		}
		String sql = "update " + getTableName(entityClass) + " set ";
		StringBuilder columns = new StringBuilder();
		for(String filedName : fieldMap.keySet()){
			columns.append(filedName).append("=?, ");
		}
		sql += columns.substring(0,columns.lastIndexOf(", ")) + " where id = ?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(fieldMap.values());
		paramList.add(id);
		Object[] params = paramList.toArray();
		return executeUpdate(sql, params) == 1;
	}
	
	public static <T> boolean deleteEntity(Class<T> entityClass,long id){
		String sql = "delete from " + getTableName(entityClass) + " where id = ? ";
		return executeUpdate(sql, id) == 1;
	}
	
	public static DataSource getDataSource() {
		// TODO Auto-generated method stub
		return DATA_SOURCE;
	}
	
	private static String getTableName(Class<?> entityClass) {
		return entityClass.getSimpleName();
	}

	public static Connection getConnection() {
		Connection connection = CONNECTION_HOLDER.get();
		if(connection == null){
			try {
				connection = DATA_SOURCE.getConnection();
			} catch (Exception e) {
				LOGGER.error("get connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(connection);
			}
		}
		return connection;
	}
	
	/**
	 * 开始事务
	 */
	public static void beginTransaction() {
		Connection connection = getConnection();
		if(connection != null){
			try {
				connection.setAutoCommit(false);
			} catch (Exception e) {
				LOGGER.error("begin transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(connection);
			}
		}
	}
	
	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		Connection connection = getConnection();
		if(connection != null){
			try {
				connection.commit();
				connection.close();
			} catch (Exception e) {
				LOGGER.error("commit transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	public static void rollbackTransaction() {
		Connection connection = getConnection();
		if(connection != null){
			try {
				connection.rollback();;
				connection.close();
			} catch (Exception e) {
				LOGGER.error("rollback transaction failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
}
