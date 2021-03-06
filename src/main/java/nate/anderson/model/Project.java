package nate.anderson.model;

import java.time.LocalDate;
import java.util.List;

public class Project {
		
	private int projectId;
	private String projectName;
	private int customerId;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private List<Task> projectTasks;
	
	public List<Task> getProjectTasks() {
		return projectTasks;
	}
	public void setProjectTasks(List<Task> projectTasks) {
		this.projectTasks = projectTasks;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
