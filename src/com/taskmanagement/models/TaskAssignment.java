package com.taskmanagement.models;

public class TaskAssignment {
	
	private User user;
	private Task task;
	
	public TaskAssignment() {
		super();
	}

	public TaskAssignment(User user, Task task) {
		super();
		this.user = user;
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
}
