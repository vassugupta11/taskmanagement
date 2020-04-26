package com.taskmanagement.models;

import java.sql.Date;

public class Task {
	
	public static final int TASK_STATUS_UNASSIGNED = 1;
	public static final int TASK_STATUS_PENDING = 2;
	public static final int TASK_STATUS_INPROGRESS = 3;
	public static final int TASK_STATUS_COMPLETED = 4;
	public static final int TASK_STATUS_BLOCKED = 5;
	
	private int id;
	private String name;
	private int status;
	private Date endDate;
	
	public Task() {
		super();
	}

	public Task(String name, int status, Date endDate) {
		super();
		this.name = name;
		this.status = status;
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", status=" + status + ", endDate=" + endDate + "]";
	}
	
}
