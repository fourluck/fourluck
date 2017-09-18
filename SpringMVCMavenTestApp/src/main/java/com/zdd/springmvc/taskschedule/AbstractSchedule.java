package com.zdd.springmvc.taskschedule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公用定时job
 * @author Gao
 * @since 0.0.1
 *
 */
public abstract class AbstractSchedule {
	protected transient final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * before Job invoke
	 */
	protected void beforeSchedule(){
		
	}
	/**
	 * after Job invoke
	 */
	protected void afterSchedule(){
		
	}
	/**
	 * the Job invoke method
	 */
	protected abstract void scheduleMethod();
	public void doJob(){
		try {
			beforeSchedule();
			scheduleMethod();
			afterSchedule();
		} catch (Exception e) {
			logger.error("this is a exception in the schedule job in the time of ["+new Date()+"],your job might be not terminated regularly");
			e.printStackTrace();
		}
		
	}
}
