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
	
	@Autowired
	public JDBCTaskDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate();
		this.taskEntryDAO = new JDBCTaskEntryDAO(datasource);
	}
	
	@Override
	public List<Task> getTasksByProject(Project project) {
		String sqlQuery = "SELECT * " +
						  "FROM tasks " +
						  "INNER JOIN projects ON tasks.project_id = project.project_id " +
						  "WHERE project_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, project.getProjectId());
		
		return mapResultsToTask(results);
	}

	@Override
	public List<Task> getTasksByUser(User user) {
		String sqlQuery = "SELECT * " +
						  "FROM tasks " +
						  "INNER JOIN users " +
						  "ON users.user_id = tasks.user_id " +
						  "WHERE user.user_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, user.getUserId());
			
		return mapResultsToTask(results);
	}
	
	private List<Task> mapResultsToTask(SqlRowSet results) {
		List<Task> taskList = new ArrayList<Task>();
		
		while (results.next()) {
			Task task = new Task();
			task.setTaskId(results.getInt("task_id"));
			task.setProjectId(results.getInt("project_id"));
			task.setUserId(results.getInt("user_id"));
			task.setTaskName(results.getString("task_name"));
			task.setCreateAt(results.getDate("created_ad").toLocalDate());
			task.setUpdatedAt(results.getDate("updated_at").toLocalDate());
			task.setTaskEntries(taskEntryDAO.getTaskEntriesByTask(task));
			taskList.add(task);
		}
		
		return taskList;
	}
	
}
