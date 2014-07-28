package org.hrs.jobschedule.demo;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *  test job
 * @author heruishan
 */
public class DemoJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("test scheduleJob jobname:"+context.getJobDetail().getKey().getName());
		System.out.println("test scheduleJob param:"+context.getJobDetail().getJobDataMap().getString("test1"));
		System.out.println("test scheduleJob:"+new Date());
		//ScheduleJob scheduleJob = (ScheduleJob)
		System.out.println("Thread count:"+Thread.activeCount());

	}

}
