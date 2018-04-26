package org.chenweifeng.util;

import java.sql.Timestamp;

public class ReportInfo {
	private int reportID;
	private String stuID;
	private String stuName;
	private String groupName;
	private String reportContent;
	private Timestamp reportTimestamp;
	public int getReportID() {
		return reportID;
	}
	public void setReportID(int reportID) {
		this.reportID = reportID;
	}
	public String getStuID() {
		return stuID;
	}
	public void setStuID(String stuID) {
		this.stuID = stuID;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public Timestamp getReportTimestamp() {
		return reportTimestamp;
	}
	public void setReportTimestamp(Timestamp reportTimestamp) {
		this.reportTimestamp = reportTimestamp;
	}
}
