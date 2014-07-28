package org.hrs.jobschedule.frame;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hrs.jobschedule.Bean.JobDefineBean;
import org.hrs.jobschedule.gate.ScheduleGate;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

public class AddJobThread extends Thread {

	static Logger logger = Logger.getLogger(AddJobThread.class.getName());

	ArrayList<JobDefineBean> jobList = new ArrayList<JobDefineBean>();

	long waitingDataTime = 60000l;
	long nextJobWaitTime = 10000l;

	public AddJobThread() {}


	public void run() {

		boolean isThreadOK = true;
		
		while (isThreadOK) {
			try {
				logger.info("Start to get job list.");
				jobList = getNewJob();
				logger.info("Get job list done, job size:" + jobList.size());

				if (jobList == null) {
					logger.info("No job in the list, sleep " + waitingDataTime
							/ 1000 + " seconds");

					Thread.sleep(waitingDataTime);
					continue;
				}

				for (JobDefineBean job : jobList) {
					addNewJob(job);
					updateJobDefineStatus(job);
				}
                logger.debug("Add Job list done.");
				Thread.sleep(nextJobWaitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.error("Get exception, thread will be stoped!");
				isThreadOK = false;
				ScheduleGate.setThreadStatus(false);
			}
		}
	}

	//update the status of Job define, do not add the job again.
	private void updateJobDefineStatus(JobDefineBean job) {
		
	}


	private ArrayList<JobDefineBean> getNewJob() {
		jobList = testJob();
		return jobList;
	}


	private void addNewJob(JobDefineBean job) {
		@SuppressWarnings("rawtypes")
		Class jobClass = null;
		try {
			jobClass = Class.forName(job.getJobClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// build job detail
		@SuppressWarnings("unchecked")
		JobDetail jobDetail = newJob(jobClass).withIdentity(job.getJobID(),
				job.getGroupID()).build();

		Trigger sTtrigger = null;
		// build trigger
		if ("DAILY".equals(job.getScheduleType())) {

		} else if ("CALENDER".equals(job.getScheduleType())) {

		} else if ("REPEAT".equals(job.getScheduleType())) {

			sTtrigger = newTrigger()
					.withIdentity(job.getJobID(), job.getGroupID())
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(4)
									.repeatForever()).build();
		} else {

			logger.warn("job [" + job.getJobID()
					+ "]Schedule type not defined, please check again.");

		}

		jobDetail.getJobDataMap().put("JOB_PARAMETER", job.getJobParameter());
//		jobDetail.getJobDataMap().put("JOB_PARAMETER", job.getJobParameter());

		// add the job to scheduler.
		try {
			ScheduleGate.scheduler.scheduleJob(jobDetail, sTtrigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

	private ArrayList<JobDefineBean> testJob() {

		ArrayList<JobDefineBean> jobList = new ArrayList<JobDefineBean>();
		JobDefineBean job = new JobDefineBean();
		Properties properties = new Properties();
		try {
			properties.load(GetJobThread.class.getClassLoader()
					.getResourceAsStream("testjob.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		job.setJobID(properties.getProperty("jobID"));
		job.setGroupID(properties.getProperty("groupID"));
		job.setHostName(properties.getProperty("hostName"));
		job.setJobClassName(properties.getProperty("jobClassName"));
		job.setJobName(properties.getProperty("jobName"));
		job.setJobParameter(properties.getProperty("jobParameter"));
		job.setRefreshType(properties.getProperty("refreshType"));
		job.setScheduleDetail(properties.getProperty("scheduleDetail"));
		job.setScheduleType(properties.getProperty("scheduleType"));
		logger.debug("Job class name:" + job.getJobClassName());
		jobList.add(job);
        logger.debug("Job list size:" + jobList.size());
		return jobList;
	}

	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			properties.load(GetJobThread.class.getClassLoader()
					.getResourceAsStream("testjob.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(properties.getProperty("jobID"));
		System.out.println(properties.getProperty("scheduleDetail"));
		System.out.println(properties.getProperty("jobClassName"));

	}
}
