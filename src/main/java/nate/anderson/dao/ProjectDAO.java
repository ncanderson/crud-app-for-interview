package nate.anderson.dao;

import java.util.List;

import nate.anderson.model.Project;
import nate.anderson.model.User;
import nate.anderson.model.Task;

public interface ProjectDAO {

	public List<Project> getProjectsByUser(User user);

	public Project getProjectById(int projectId);
	
	public Project getProjectByTask(Task task);
	
	public void addNewProject(String projectName, int customerId);
	
	public List<Project> getAllProjects();
	
}
