package nate.anderson.dao;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.bcel.generic.GETSTATIC;
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
				 		   "WHERE tasks.task_id = ?";
		 
		 SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, task.getTaskId());
		 
		return mapResultsToTaskEntry(results);
	}
	
	@Override
	public TaskEntry getTaskEntryById(TaskEntry taskEntry) {
		
		String sqlQuery = "SELECT * " +
						  "FROM task_entries " +
						  "WHERE task_entries_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, taskEntry.getTaskEntryId());
		
		return mapResultsToTaskEntry(results).get(0);
	}
	
	@Override
	public void createUpdateTaskEntry(Task task) {
		
		List<TaskEntry> taskEntries = getTaskEntriesByTask(task);
		
		for (TaskEntry taskEntry : taskEntries) {
			if (timerStarted(taskEntry)) {
				finishTaskEntry(taskEntry);
				return;
			}
		}
		
		String sqlQuery = "INSERT INTO task_entries " +
						  "(task_id, start_time, created_at, updated_at) " +
						  "VALUES (?, NOW(), NOW(), NOW())";
		
		jdbcTemplate.update(sqlQuery, task.getTaskId());
	}
	
	private List<TaskEntry> mapResultsToTaskEntry(SqlRowSet results) {
		List<TaskEntry> taskEntries = new ArrayList<TaskEntry>();
		
		while (results.next()) {
			TaskEntry taskEntry = new TaskEntry();
			taskEntry.setTaskEntryId(results.getInt("task_entries_id"));
			taskEntry.setTaskId(results.getInt("task_id"));
			taskEntry.setDuration(results.getDouble("duration"));
			taskEntry.setNote(results.getString("note"));
			taskEntry.setStartTime(results.getTimestamp("start_time").toLocalDateTime());
			taskEntry.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
			taskEntry.setUpdatedAt(results.getTimestamp("updated_at").toLocalDateTime());
			taskEntries.add(taskEntry);
		}
		
		return taskEntries;
	}
	
	private boolean timerStarted(TaskEntry taskEntry) {
		if (taskEntry.getDuration() == 0.0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void finishTaskEntry(TaskEntry taskEntry) {
		
		LocalDateTime finishTime = LocalDateTime.now();
		double duration = calculateDurationInMinutes(taskEntry.getStartTime(), finishTime);
		
		String sqlQuery = "UPDATE task_entries " +
						  "SET updated_at = ?, " +
						  	  "duration = ? " + 
						  "WHERE task_entries_id = ?";
		
		jdbcTemplate.update(sqlQuery, finishTime, duration, taskEntry.getTaskEntryId());
	}
	
	private double calculateDurationInMinutes(LocalDateTime start, LocalDateTime end) {
		
		return (start.until(end, ChronoUnit.SECONDS)) / 3600;
	}
}








