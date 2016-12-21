package nate.anderson.dao;

import java.util.List;

import nate.anderson.model.Task;
import nate.anderson.model.TaskEntry;

public interface TaskEntryDAO {

	public List<TaskEntry> getTaskEntriesByTask(Task task);
	
	public TaskEntry getTaskEntryById(TaskEntry taskEntry);
	
	public void createUpdateTaskEntry(Task task);

}
