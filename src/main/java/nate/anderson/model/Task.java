package nate.anderson.model;

import java.time.LocalDate;
import java.util.List;

public class Task {

	private int taskId;
	private int projectId;
	private int userId;
	private String taskName;
	private String projectName;
	private LocalDate createAt;
	private LocalDate updatedAt;
	private List<TaskEntry> taskEntries;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public List<TaskEntry> getTaskEntries() {
		return taskEntries;
	}
	public void setTaskEntries(List<TaskEntry> taskEntries) {
		this.taskEntries = taskEntries;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public LocalDate getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
