package nate.anderson.dao;

import java.util.List;

import nate.anderson.model.Project;
import nate.anderson.model.User;

public interface ProjectDAO {

	public List<Project> getProjectsByUser(User user);

	public Project getProjectById(int projectId);
	
}
