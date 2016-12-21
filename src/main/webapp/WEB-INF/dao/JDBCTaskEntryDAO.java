package nate.anderson.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import nate.anderson.model.Task;
import nate.anderson.model.TaskEntry;

@Component
public class JDBCTaskEntryDAO implements TaskEntryDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCTaskEntryDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<TaskEntry> getTaskEntriesByTask(Task task) {
		
		 String sqlQuery = "SELECT * " +
				 		   "FROM task_entries " +
				 		   "INNER JOIN tasks " +
				 		   "ON task_entries.task_id = tasks.task_id " +
				 		   "WHERE task.task_id = ?";
		 
		 SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, task.getTaskId());
		 
		return mapResultsToTaskEntry(results);
	}
	
	private List<TaskEntry> mapResultsToTaskEntry(SqlRowSet results) {
		List<TaskEntry> taskEntries = new ArrayList<TaskEntry>();
		
		while (results.next()) {
			TaskEntry taskEntry = new TaskEntry();
			taskEntry.setTaskEntryId(results.getInt("task_entries_id"));
			taskEntry.setTaskId(results.getInt("task_id"));
			taskEntry.setDuration(results.getDouble("duration"));
			taskEntry.setNote(results.getString("note"));
			taskEntry.setStartTime(results.getDate("start_time").toLocalDate());
			taskEntry.setCreatedAt(results.getDate("created_at").toLocalDate());
			taskEntry.setUpdatedAt(results.getDate("updated_at").toLocalDate());
			taskEntries.add(taskEntry);
		}
		
		return taskEntries;
	}
	
}