package com.yr.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//��ʱ������
public class Test implements Job{

	@Override
	public void execute(JobExecutionContext je) throws JobExecutionException {
System.out.println(je.getMergedJobDataMap().get("name"));
		System.out.println("11111");
	}

}
