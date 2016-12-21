package nate.anderson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nate.anderson.dao.TaskDAO;
import nate.anderson.dao.TaskEntryDAO;
import nate.anderson.model.Task;

@RestController
public class AjaxController {

//	private TaskEntryDAO taskEntryDAO;
//	private TaskDAO taskDAO;
//	
//	@Autowired
//	public AjaxController(TaskEntryDAO taskEntryDAO, TaskDAO taskDAO) {
//		this.taskEntryDAO = taskEntryDAO;
//		this.taskDAO = taskDAO;
//	}
//	
//	@RequestMapping(path="/start-timer", method=RequestMethod.POST) 
//	public void createEntryForStartTimer(@RequestParam int taskId) {
//		
//		Task task = taskDAO.getTaskById(taskId);
//		
//		taskEntryDAO.createUpdateTaskEntry(task);
//		
//	}
	
}
