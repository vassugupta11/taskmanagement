package com.taskmanagement.db;

import java.sql.*;

public class TaskManagementDb {

	public static void createDB() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:task_management.sqlite");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			String userTableCreateQuery = "CREATE TABLE IF NOT EXISTS users " + "(id INTEGER PRIMARY KEY autoincrement,"
					+ " username CHAR(50)  NOT NULL, " + " password CHAR(10)  NOT NULL, " + " name CHAR(50)  NOT NULL)";

			String taskTableCreateQuery = "CREATE TABLE IF NOT EXISTS tasks " + "(id INTEGER PRIMARY KEY autoincrement,"
					+ " name CHAR(50) NOT NULL, " + " end_date datetime NOT NULL, " + " status INTEGER  NOT NULL)";

			String tasksAssignmentTable = "CREATE TABLE IF NOT EXISTS tasks_assignment " + "(user_id INTEGER NOT NULL,"
					+ "task_id INTEGER NOT NULL," + "foreign key (user_id) references users(id),"
					+ " foreign key (task_id) references tasks(id)) ";

			stmt.executeUpdate(userTableCreateQuery);
			stmt.executeUpdate(taskTableCreateQuery);
			stmt.executeUpdate(tasksAssignmentTable);
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Tables created successfully");
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:task_management.sqlite");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/*
	 * public static void connectDB() { Connection con = null; try {
	 * Class.forName("org.sqlite.JDBC"); con =
	 * DriverManager.getConnection("jdbc:sqlite:task_management.sqlite"); } catch
	 * (Exception e) { System.err.println(e.getClass().getName() + ": " +
	 * e.getMessage()); System.exit(0); }
	 * System.out.println("Database connected sucessfully"); }
	 */
}
