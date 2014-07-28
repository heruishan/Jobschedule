/**
 * 
 */
package org.hrs.jobschedule.demo;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author heruishan
 *
 */
public class DemoSchedule2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SchedulerFactory schedulerFactory =  new StdSchedulerFactory();
		Scheduler scheduler = null;
		try {
			//get and start scheduler
			scheduler = schedulerFactory.getScheduler();
			scheduler.start();
			System.out.println("schedule start");
			int count = 0;
			//add job to scheduler
			//start a thread to add the job
			//start a thread to check the manually job.
			    //1. change the trigger to execute now
			    //2. check the job status
			    //3. rollback the trigger.
			while(true)
			{
				
				//get job list
				List<JobListBean> jobList = null;
				
				for(JobListBean job:jobList)
				{
					buildJob(scheduler,job);
				}
				
				if(count>10)
				{
					break;
				}
				
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
			
			
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
					
			
		} catch (SchedulerException e) {
			e.printStackTrace();
			try {
				scheduler.shutdown();
			} catch (SchedulerException e1) {
				e1.printStackTrace();
			}
		}

	}

	private static void buildJob(Scheduler scheduler, JobListBean job) throws SchedulerException {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		Class jobClass = null;
		try {
			jobClass = Class.forName(job.getJobClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		@SuppressWarnings("unchecked")
		JobDetail jobDetail = newJob(jobClass).withIdentity(job.getJobName(),job.getJobGroup()).build() ;// new JobDetailImpl("","",DemoJob.class); 
		
		//define trigger
		Trigger sTtrigger = newTrigger().withIdentity("job1", "group1").startNow().withSchedule(
                simpleSchedule().withIntervalInSeconds(4)
                .repeatForever()).build();
		
		jobDetail.getJobDataMap().put("test1", "oh yeah");
		
		scheduler.scheduleJob(jobDetail, sTtrigger);
		
	}


}
