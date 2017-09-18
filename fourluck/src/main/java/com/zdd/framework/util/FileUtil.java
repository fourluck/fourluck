package com.zdd.framework.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件工具类
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月13日 上午11:17:33
 */
public final class FileUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 获取真实文件名（自动去除文件路径）
	 */
	public static String getRealFileName(String filename){
		return FilenameUtils.getName(filename);
	}
	
	/**
	 * 创建文件
	 * @param filePath
	 * @return
	 */
	public static File createFile(String filePath){
		File file;
		try {
			file = new File(filePath);
			File parentDir = file.getParentFile();
			if(!parentDir.exists()){
				FileUtils.forceMkdir(parentDir);
			}
		} catch (Exception e) {
			LOGGER.error("create file failure",e);
			throw new RuntimeException();
		}
		return file;
	}
	
}
