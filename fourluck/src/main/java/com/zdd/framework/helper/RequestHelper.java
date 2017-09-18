package com.zdd.framework.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;

import com.zdd.framework.bean.FormParam;
import com.zdd.framework.bean.Param;
import com.zdd.framework.util.CodecUtil;
import com.zdd.framework.util.StreamUtil;
import com.zdd.framework.util.StringUtil;

/**
 * 请求助手类
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月13日 下午1:22:54
 */
public final class RequestHelper {
	
	public static Param createParam(HttpServletRequest request)throws IOException{
		List<FormParam> formParamList = new ArrayList<FormParam>();
		formParamList.addAll(parseParamterNames(request));
		formParamList.addAll(parseInputstream(request));
		return new Param(formParamList);
	}

	private static List<FormParam> parseParamterNames(HttpServletRequest request) {
		List<FormParam> formParamList = new ArrayList<FormParam>();
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()){
			String fieldName = paramNames.nextElement();
			String[] fieldValues = request.getParameterValues(fieldName);
			if(ArrayUtils.isNotEmpty(fieldValues)){
				Object fieldValue;
				if(fieldValues.length == 1){
					fieldValue = fieldValues[0];
				}else{
					StringBuilder sb = new StringBuilder("");
					for(int i = 0;i<fieldValues.length;i++){
						sb.append(fieldValues[i]);
						if(i != fieldValues.length - 1){
							sb.append(StringUtil.SEPARATOR);
						}
					}
					fieldValue = sb.toString();
				}
				formParamList.add(new FormParam(fieldName, fieldValue));
			}
		}
		return formParamList;
	}
	
	public static List<FormParam> parseInputstream(HttpServletRequest request)throws IOException{
		List<FormParam> formParamList = new ArrayList<FormParam>();
		String body = CodecUtil.encodeURL(StreamUtil.getString(request.getInputStream()));
		if(StringUtil.isNoEmpty(body)){
			String[] kvs = body.split("&");
			if(ArrayUtils.isNotEmpty(kvs)){
				for(String kv : kvs){
					String[] array = kv.split("=");
					if(ArrayUtils.isNotEmpty(array)){
						String fieldName = array[0];
						String fieldValue = array[1];
						formParamList.add(new FormParam(fieldName, fieldValue));
					}
				}
			}
		}
		return formParamList;
	}
}
