package nate.anderson.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskEntry {

	private int taskEntryId;
	private int taskId;
	private double duration;
	private String note;
	private LocalDateTime startTime;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public int getTaskEntryId() {
		return taskEntryId;
	}
	public void setTaskEntryId(int taskEntryId) {
		this.taskEntryId = taskEntryId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
