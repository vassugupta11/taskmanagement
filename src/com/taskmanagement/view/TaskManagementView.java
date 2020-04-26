package com.taskmanagement.view;

import java.util.Scanner;

import com.taskmanagement.dao.TaskManagementDao;
import com.taskmanagement.models.Task;
import com.taskmanagement.models.User;

public class TaskManagementView {
	
	private static TaskManagementDao taskManagementDao = new TaskManagementDao();
	private static Scanner sc = new Scanner(System.in);

	private static final int ASSIGN_TASK = 1;
	private static final int CHANGE_STATUS_OF_TASK = 2;
	
	public static void main(String[] args) {
		showLoginMenu();
	}

	private static void showLoginMenu() {
		System.out.println("================= TASK MANAGEMENT =================");
		System.out.println("PLEASE LOGIN : \nENTER THE USERNAME");
		String username = sc.nextLine();
		System.out.println("ENTER THE PASSWORD");
		String password = sc.nextLine();
		if(taskManagementDao.validateLoginDetails(username, password) != null) {
			System.out.println("LOGIN SUCCESSS");
			showMainMenu();
		} else {
			System.out.println("INVALID LOGIN DETAILS");
			showLoginMenu();
		}
	}

	private static void showMainMenu() {
		System.out.println("========= MAIN MENU ===========");
		System.out.println("1. ASSIGN TASK\n2. CHANGE STATUS OF TASK");
		System.out.println("ENTER YOUR CHOICE : ");
		
		int selectedChoice = sc.nextInt();
		switch (selectedChoice) {
		case ASSIGN_TASK:
			assignTask();
			break;

		case CHANGE_STATUS_OF_TASK:
			changeStatusOfTask();
			break;
			
		default:
			System.out.println("INVALID OPTION SELECTED");
			showMainMenu();
			break;
		}
		
	}

	private static void changeStatusOfTask() {
		Task task = null;
		do {
			showAllTasks();
			System.out.println("PLEASE SELECT THE VALID TASK ID : ");
			int taskId = sc.nextInt();
			task = taskManagementDao.getTask(taskId);
		} while (task == null);
		
		int taskStatus = 999999;
		do {
			System.out.println("PLEASE SELECT THE STATUS OF THE TASK");
			System.out.println("1. UNASSIGNED\n2. PENDING\n3. IN PROGRESS\n4. COMPLETED\n5. BLOCKED");
			taskStatus = sc.nextInt();
		} while (!(taskStatus>=Task.TASK_STATUS_UNASSIGNED && taskStatus<=Task.TASK_STATUS_BLOCKED));
		
		if(taskManagementDao.changeTaskStatus(task, taskStatus)) {
			System.out.println("TASK STATUS CHANGED SUCCESSFULLY");
		} else {
			System.out.println("SOME ERROR OCCURED DURING CHANGING THE STATUS");
		}
			
	}

	private static void assignTask() {
		Task task = null;
		do {
			showAllTasks();
			System.out.println("PLEASE SELECT THE VALID TASK ID : ");
			int taskId = sc.nextInt();
			task = taskManagementDao.getTask(taskId);
		} while (task == null);
		User user = null;
		do {
			showAllUsers();
			System.out.println("PLEASE SELECT THE VALID USER ID : ");
			int userId = sc.nextInt();
			user = taskManagementDao.getUser(userId);
		} while (task == null);
		
		if(taskManagementDao.assignTask(user, task)) {
			System.out.println("TASK : "+task.getName()+" ASSIGNED TO USER : "+user.getName()+" SUCCESSFULLY");
		} else {
			System.out.println("SOME ERROR OCCURED WHILE ASSIGNING THE TASK");
		}
	}
	
	private static void showAllUsers() {
		boolean printHeading = true;
		for (User user: taskManagementDao.getAllUsers()) {
			if (printHeading) {
				System.out.println("USER ID\t\tUSER NAME");
				printHeading = false;
			}
			System.out.println(user.getId() + "\t\t" + user.getName());
		}
	}

	private static void showAllTasks() {
		boolean printHeading = true;
		for (Task task: taskManagementDao.getAllTasks()) {
			if (printHeading) {
				System.out.println("TASK ID\t\tTASK NAME");
				printHeading = false;
			}
			System.out.println(task.getId() + "\t\t" + task.getName());
		}
	}
}
