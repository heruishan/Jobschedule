/**
 * 
 */
package org.hrs.jobschedule.gate;

import org.hrs.jobschedule.frame.AddJobThread;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author heruishan
 * 
 */
public class ScheduleGate {

	static Logger logger = Logger.getLogger(ScheduleGate.class.getName());

	static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	public static Scheduler scheduler = null;
	public static boolean isJobThreadStatusOK = true;

	public static void setThreadStatus(boolean status){
		isJobThreadStatusOK = status;
	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		

		try {
			// get and start a scheduler
			scheduler = schedulerFactory.getScheduler();
			scheduler.start();
			logger.info("schedule start");
			
			// add jobs to scheduler
			// start a thread to get and add the job
			AddJobThread addJobThread = new AddJobThread();
			addJobThread.start();

			//checking thread status
			while (true) {
				if(isJobThreadStatusOK){
					logger.debug("AddJobThread is running.");
				}else{
					logger.warn("AddJobThread has problem, restart the thread.");
					addJobThread.start();
				}

				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

}
