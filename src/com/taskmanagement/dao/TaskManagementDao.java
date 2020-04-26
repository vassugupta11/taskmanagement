package com.taskmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.taskmanagement.db.TaskManagementDb;
import com.taskmanagement.models.Task;
import com.taskmanagement.models.User;

public class TaskManagementDao {

	private Connection con;
	private String sql;
	private PreparedStatement ps;
	private ResultSet rs;

	public boolean insertUser(User user) {
		boolean isInserted = false;

		sql = "INSERT INTO users (username, password, name) VALUES (?, ?, ?)";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted > 0) {
				isInserted = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isInserted;
	}

	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();

		sql = "SELECT * FROM users";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));

				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return users;
	}

	public User getUser(int id) {
		User user = null;

		sql = "SELECT * FROM users WHERE id = ?";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}
	
	public boolean updateUser(User user) {

		boolean isUpdated = false;

		sql = "UPDATE users SET username = ?, password = ?,  name = ? WHERE id = ?";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getId());

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				isUpdated = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isUpdated;

	}

	public boolean deleteUser(User user) {
		boolean isDeleted = false;

		sql = "DELETE FROM users WHERE id = ?";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, user.getId());

			int rowsDeleted = ps.executeUpdate();

			if (rowsDeleted > 0) {
				isDeleted = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isDeleted;
	}

	public boolean insertTask(Task task) {
		boolean isInserted = false;

		sql = "INSERT INTO tasks (name, end_date, status) VALUES (?, ?, ?)";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, task.getName());
			ps.setDate(2, task.getEndDate());
			ps.setInt(3, Task.TASK_STATUS_UNASSIGNED);

			int rowsInserted = ps.executeUpdate();

			if (rowsInserted > 0) {
				isInserted = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isInserted;
	}

	public boolean updateTask(Task task) {

		boolean isUpdated = false;

		sql = "UPDATE tasks SET name = ?, end_date = ?,  status = ? WHERE id = ?";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, task.getName());
			ps.setDate(2, task.getEndDate());
			ps.setInt(3, task.getStatus());
			ps.setInt(4, task.getId());

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				isUpdated = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isUpdated;

	}

	public boolean deleteTask(Task task) {
		boolean isDeleted = false;

		sql = "DELETE FROM tasks WHERE id = ?";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, task.getId());

			int rowsDeleted = ps.executeUpdate();

			if (rowsDeleted > 0) {
				isDeleted = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isDeleted;
	}

	public Task getTask(int id) {
		Task task = null;

		sql = "SELECT * FROM tasks WHERE id = ?";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				task = new Task();
				task.setId(rs.getInt(1));
				task.setName(rs.getString(2));
				task.setEndDate(rs.getDate(3));
				task.setStatus(rs.getInt(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return task;
	}
	
	public ArrayList<Task> getAllTasks() {
		ArrayList<Task> tasks = new ArrayList<>();

		sql = "SELECT * FROM tasks";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				Task task = new Task();
				task.setId(rs.getInt(1));
				task.setName(rs.getString(2));
				task.setEndDate(rs.getDate(3));
				task.setStatus(rs.getInt(4));

				tasks.add(task);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return tasks;
	}
	
	public boolean assignTask(User user, Task task) {
		boolean isAssigned = false;

		sql = "INSERT INTO tasks_assignment (user_id, task_id) VALUES (?, ?)";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, user.getId());
			ps.setInt(2, task.getId());

			int rowsAssigned = ps.executeUpdate();

			if (rowsAssigned > 0) {
				isAssigned = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isAssigned;
	}
	
	public User validateLoginDetails(String username, String password) {
		User user = null;
		sql = "SELECT * FROM users WHERE username = ? AND password = ?";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}
	
	public boolean changeTaskStatus(Task task, int taskStatus) {
		boolean isChanged = false;
		
		sql = "UPDATE tasks SET status = ? WHERE id = ?";

		try {
			con = TaskManagementDb.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, taskStatus);
			ps.setInt(2, task.getId());

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated > 0) {
				isChanged = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isChanged;
	}
}
