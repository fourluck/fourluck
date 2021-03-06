package com.zdd.framework.bean;

import java.io.InputStream;

/**
 * 上传文件参数封装类
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月12日 下午4:15:36
 */
public class FileParam {
	private String fieldName;//文件表单的字段名
	
	private String fileName;//上传文件的文件名
	
	private long fileSize;//上传文件的文件大小
	
	private String contentType;//上传文件的content-type,可判断文件类型
	
	private InputStream inputStream;//上传文件的字节输入流 

	public FileParam(String fieldName, String fileName, long fileSize, String contentType, InputStream inputStream) {
		this.fieldName = fieldName;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.inputStream = inputStream;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFileName() {
		return fileName;
	}


	public long getFileSize() {
		return fileSize;
	}

	public String getContentType() {
		return contentType;
	}


	public InputStream getInputStream() {
		return inputStream;
	}
}
