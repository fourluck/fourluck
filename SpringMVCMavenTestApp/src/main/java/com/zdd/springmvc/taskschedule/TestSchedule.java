package com.zdd.springmvc.taskschedule;



public class TestSchedule extends AbstractSchedule{

	@Override
	protected void scheduleMethod() {
		System.out.println("这是定时任务");
	}

}
