package nate.anderson.dao;

import java.util.List;

import nate.anderson.model.Project;
import nate.anderson.model.Task;
import nate.anderson.model.User;

public interface TaskDAO {

	public List<Task> getTasksByProject(Project project);
	
	public List<Task> getTasksByUser(User user);
	
	public Task getTaskById(int id);
	
	public void addNewTask(Task task);
}
