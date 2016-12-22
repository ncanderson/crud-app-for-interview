package nate.anderson.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import nate.anderson.model.Project;
import nate.anderson.model.Task;
import nate.anderson.model.User;

@Component
public class JDBCTaskDAO implements TaskDAO {

	private JdbcTemplate jdbcTemplate;
	private TaskEntryDAO taskEntryDAO;
	private ProjectDAO projectDAO;
	
	@Autowired
	public JDBCTaskDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
		taskEntryDAO = new JDBCTaskEntryDAO(datasource);
		projectDAO = new JDBCProjectDAO(datasource);
	}
	
	@Override
	public List<Task> getTasksByProject(Project project) {
		String sqlQuery = "SELECT * " +
						  "FROM tasks " +
						  "INNER JOIN projects ON tasks.project_id = projects.project_id " +
						  "WHERE projects.project_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, project.getProjectId());
		
		return mapResultsToTask(results);
	}

	@Override
	public List<Task> getTasksByUser(User user) {
		String sqlQuery = "SELECT * " +
						  "FROM tasks " +
						  "INNER JOIN users ON users.user_id = tasks.user_id " +
						  "INNER JOIN projects ON projects.project_id = tasks.task_id " +
						  "WHERE users.user_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, user.getUserId());
			
		return mapResultsToTask(results);
	}
	
	@Override
	public Task getTaskById(int id) {
		
		String sqlQuery = "SELECT * " + 
						  "FROM tasks " +
						  "WHERE task_id =?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, id);
		
		Task task = new Task();
		
		while (results.next()) {
			task.setTaskId(results.getInt("task_id"));
			task.setProjectId(results.getInt("project_id"));
			task.setUserId(results.getInt("user_id"));
			task.setTaskName(results.getString("task_name"));
			task.setCreateAt(results.getDate("created_at").toLocalDate());
			task.setUpdatedAt(results.getDate("updated_at").toLocalDate());
		}
		return task;
	}
	
	@Override
	public void addNewTask(Task task) {
		
		String sqlQuery = "INSERT INTO tasks " +
						  "(project_id, user_id, task_name, created_at, updated_at) " +
						  "VALUES (?, ?, ?, NOW(), NOW())";
		
		jdbcTemplate.update(sqlQuery, task.getProjectId(), task.getUserId(), task.getProjectName());
	}
	
	private List<Task> mapResultsToTask(SqlRowSet results) {
		List<Task> taskList = new ArrayList<Task>();
		
		while (results.next()) {
			Task task = new Task();
			task.setTaskId(results.getInt("task_id"));
			task.setProjectId(results.getInt("project_id"));
			task.setUserId(results.getInt("user_id"));
			task.setTaskName(results.getString("task_name"));
			task.setCreateAt(results.getDate("created_at").toLocalDate());
			task.setUpdatedAt(results.getDate("updated_at").toLocalDate());
		
			if (results.getString("project_id") != null) {
				task.setProjectName(results.getString("project_name"));				
			}
			taskList.add(task);
		}
		
		return taskList;
	}
	
}
