/**
 * 
 */
package org.hrs.jobschedule.Bean;

/**
 * @author heruishan
 *
 */
public class JobDefineBean {
	
	public String jobID = "";
	
	public String groupID = "";
	
	public String jobName = "";
	
	public String scheduleType = "";
	
	public String scheduleDetail = "";
	
	public String jobClassName = "";
	
	public String jobParameter = "";
	
	public boolean isNeedRefresh = false;
	
	public String refreshType = "";
			
	public boolean isExecuteManually = false;
	
	public String hostName = "";

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getScheduleDetail() {
		return scheduleDetail;
	}

	public void setScheduleDetail(String scheduleDetail) {
		this.scheduleDetail = scheduleDetail;
	}

	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}

	public String getJobParameter() {
		return jobParameter;
	}

	public void setJobParameter(String jobParameter) {
		this.jobParameter = jobParameter;
	}

	public boolean isNeedRefresh() {
		return isNeedRefresh;
	}

	public void setNeedRefresh(boolean isNeedRefresh) {
		this.isNeedRefresh = isNeedRefresh;
	}

	public String getRefreshType() {
		return refreshType;
	}

	public void setRefreshType(String refreshType) {
		this.refreshType = refreshType;
	}

	public boolean isExecuteManually() {
		return isExecuteManually;
	}

	public void setExecuteManually(boolean isExecuteManually) {
		this.isExecuteManually = isExecuteManually;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	
}
