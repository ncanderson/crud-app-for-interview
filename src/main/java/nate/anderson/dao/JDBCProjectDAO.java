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
public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCProjectDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Project> getProjectsByUser(User user) {
		
		String sqlQuery = "SELECT * " +
					      "FROM projects " +
					      "INNER JOIN tasks ON tasks.project_id = tasks.project_id " +
					      "INNER JOIN users ON tasks.user_id = users.user_id " +
					      "WHERE users.user_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, user.getUserId());
		
		return mapResultsToProject(results);
	}
	

	@Override
	public Project getProjectById(int projectId) {
		
		String sqlQuery = "SELECT * " +
						  "FROM projects " +
						  "WHERE project_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, projectId);
		
		return mapResultsToProject(results).get(0);
	}
	
	@Override
	public Project getProjectByTask(Task task) {
		
		String sqlQuery = "SELECT * " +
						  "FROM projects " +
						  "INNER JOIN tasks ON tasks.project_id = projects.project_id " +
						  "WHERE tasks.project_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, task.getProjectId());
		
		return mapResultsToProject(results).get(0);
	}
	
	private List<Project> mapResultsToProject(SqlRowSet results) {
		List<Project> projectList = new ArrayList<Project>();
		
		while (results.next()) {
			Project project = new Project();
			project.setProjectId(results.getInt("project_id"));
			project.setProjectName(results.getString("project_name"));
			project.setCustomerId(results.getInt("customer_id"));
			project.setCreatedAt(results.getDate("created_at").toLocalDate());
			project.setUpdatedAt(results.getDate("updated_at").toLocalDate());
			projectList.add(project);
		}
		
		return projectList;
	}

}
