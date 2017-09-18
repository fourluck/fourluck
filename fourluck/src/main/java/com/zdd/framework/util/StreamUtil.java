package com.zdd.framework.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流操作工具类
 * @author four-luck
 * @since 1.0.0
 * @date 2017年9月8日 下午2:47:49
 */
public final class StreamUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);
	
	public static String getString(InputStream is){
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = bufferedReader.readLine()) != null){
				sb.append(line);
			}
		} catch (Exception e) {
			LOGGER.error("get string failure",e);
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
	/**
	 * 从输入流复制到输出流
	 * @param inputStream
	 * @param outputStream
	 */
	public static void copyStream(InputStream inputStream,OutputStream outputStream){
		try {
			int length = 0;
			byte[] buffer = new byte[4 * 1024];
			while((length=inputStream.read(buffer, 0, buffer.length)) != -1){
				outputStream.write(buffer, 0, length);
			}
			outputStream.flush();
		} catch (Exception e) {
			LOGGER.error("copy stream failure",e);
			throw new RuntimeException(e);
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (Exception e) {
				LOGGER.error("close stream failure",e);
			}
		}
	}
}
