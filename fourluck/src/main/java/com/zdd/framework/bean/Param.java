package com.zdd.framework.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdd.framework.util.CastUtil;
import com.zdd.framework.util.CollectionUtil;
import com.zdd.framework.util.StringUtil;

/**
 * 请求参数对象
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月8日 下午1:24:59
 */
public class Param {
	private List<FormParam> formParamList;
	
	private List<FileParam> fileParamList;
	
	public Param(List<FormParam> formParamList) {
		this.formParamList = formParamList;
	}
	
	public Param(List<FormParam> formParamList,List<FileParam> fileParamList) {
		this.formParamList = formParamList;
		this.fileParamList = fileParamList;
	}
	
	/**
	 * 获取请求参数映射
	 * @return
	 */
	public Map<String,Object> getFieldMap(){
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		if(CollectionUtil.isNotEmpty(formParamList)){
			for(FormParam formParam : formParamList){
				String fieldName = formParam.getFieldName();
				Object fieldValue = formParam.getFieldValue();
				if(fieldMap.containsKey(fieldName)){
					fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
				}
				fieldMap.put(fieldName, fieldValue);
			}
		}
		return fieldMap;
	}

	/**
	 * 获取上传文件映射
	 * @return
	 */
	public Map<String,List<FileParam>> getFileMap() {
		Map<String,List<FileParam>> fileMap = new HashMap<String,List<FileParam>>();
		if(CollectionUtil.isNotEmpty(fileParamList)){
			for(FileParam fileParam : fileParamList){
				String fieldName = fileParam.getFieldName();
				List<FileParam> fileParamList;
				if(fileMap.containsKey(fieldName)){
					fileParamList = fileMap.get(fieldName);
				}else{
					fileParamList = new ArrayList<FileParam>();
				}
				fileParamList.add(fileParam);
				fileMap.put(fieldName, fileParamList);
			}
		}
		return fileMap;
	}
	
	/**
	 * 获取某字段下所有上传文件
	 * @param fieldName
	 * @return
	 */
	public List<FileParam> getFileList(String fieldName){
		return getFileMap().get(fieldName);
	}
	
	/**
	 * 获取某字段下唯一上传文件
	 * @param fieldName
	 * @return
	 */
	public FileParam getFile(String fieldName){
		List<FileParam> fileParamList = getFileList(fieldName);
		if(CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1){
			return fileParamList.get(0);
		}
		return null;
	}
	
	/**
	 * 验证请求参数是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return CollectionUtil.isEmpty(formParamList) && CollectionUtil.isEmpty(fileParamList);
	}
	
	/**
	 * 根据请求参数名获取String 参数值
	 * @param name
	 * @return
	 */
	public String getString(String name){
		return CastUtil.castString(getFieldMap().get(name));
	}
	
	/**
	 * 根据请求参数名获取long 参数值
	 * @param name
	 * @return
	 */
	public long getLong(String name){
		return CastUtil.castLong(getFieldMap().get(name));
	}
	
	/**
	 * 根据请求参数名获取int 参数值
	 * @param name
	 * @return
	 */
	public int getInt(String name){
		return CastUtil.castInt(getFieldMap().get(name));
	}
	
	/**
	 * 根据请求参数名获取boolean 参数值
	 * @param name
	 * @return
	 */
	public boolean getBoolean(String name){
		return CastUtil.castBoolean(getFieldMap().get(name));
	}
}
