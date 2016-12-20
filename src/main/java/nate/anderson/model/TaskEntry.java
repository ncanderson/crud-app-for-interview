package nate.anderson.model;

import java.time.LocalDate;

public class TaskEntry {

	private int taskEntryId;
	private int taskId;
	private double duration;
	private String note;
	private LocalDate startTime;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	
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
	public LocalDate getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
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
