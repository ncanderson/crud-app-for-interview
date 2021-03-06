package nate.anderson.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import nate.anderson.dao.CustomerDAO;
import nate.anderson.dao.ProjectDAO;
import nate.anderson.dao.TaskDAO;
import nate.anderson.dao.TaskEntryDAO;
import nate.anderson.dao.UserDAO;
import nate.anderson.model.Customer;
import nate.anderson.model.Project;
import nate.anderson.model.Task;
import nate.anderson.model.User;

@Controller
@SessionAttributes("currentUser")
public class UserController {

	private UserDAO userDAO;
	private CustomerDAO customerDAO;
	private ProjectDAO projectDAO;
	private TaskDAO taskDAO;
	private TaskEntryDAO taskEntryDAO;
	
	@Autowired
	public UserController(UserDAO userDAO, CustomerDAO customerDAO, 
						  ProjectDAO projectDAO, TaskDAO taskDAO,
						  TaskEntryDAO taskEntryDAO) {
		
		this.userDAO = userDAO;
		this.customerDAO = customerDAO;
		this.projectDAO = projectDAO;
		this.taskDAO = taskDAO;
		this.taskEntryDAO = taskEntryDAO;
	}
	
	@RequestMapping(path="/user-home", method=RequestMethod.GET)
	public String userHomePage(HttpServletRequest request,
							   ModelMap model) {
		
		User user = (User) model.get("currentUser");
		
		List<Project> allProjects = projectDAO.getAllProjects();
		List<Task> userTasks = taskDAO.getTasksByUser(user);
		
		request.setAttribute("userProjects", allProjects);
		request.setAttribute("userTasks", userTasks);
		
		return "user-home";
	}

	@RequestMapping(path="/update-user", method=RequestMethod.GET)
	public String updateUserInformation() {
		
		return "update-user";
	}
	
	@RequestMapping(path="/update-user", method=RequestMethod.POST)
	public String submitUpdatedUserInformation(User user, ModelMap model) {
		
		int userId = user.getUserId();
		
		userDAO.updateUserInformation(user);
		
		user = userDAO.getUserById(userId);
		
		model.put("currentUser", user);
		
		return "redirect:/user-home";
	}
	
	@RequestMapping(path="/sign-up", method=RequestMethod.POST)
	public String createNewUser(@RequestParam String username,
								@RequestParam String email,
								@RequestParam String password,
								ModelMap model) {

		User user = new User();
		
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		
		userDAO.createNewUser(user);
		
		model.put("currentUser", user);
		
		return "redirect:/user-home";
	}
	
	@RequestMapping(path="/new-project", method=RequestMethod.GET)
	public String enterNewProject(HttpServletRequest request) {
		
		List<Customer> customerList = customerDAO.getAllCustomers();
		
		request.setAttribute("customerList", customerList);
		
		return "new-project";
	}
	
	@RequestMapping(path="/new-project", method=RequestMethod.POST)
	public String newProjectUpdate(@RequestParam String projectName,
								   @RequestParam int customerId) {
		
		projectDAO.addNewProject(projectName, customerId);
		
		return "redirect:/user-home";
	}
	
	@RequestMapping(path="/view-project-details", method=RequestMethod.GET)
	public String viewProjects(HttpServletRequest request) {
		
		int projectId = Integer.parseInt(request.getParameter("projectId"));

		Project detailProject = projectDAO.getProjectById(projectId);
		
		request.setAttribute("detailProject", detailProject);
		
		return "view-project-details";
	}
	
	@RequestMapping(path="/start-timer", method=RequestMethod.POST) 
	public String createEntryForStartTimer(@RequestParam int taskId) {
		
		Task task = taskDAO.getTaskById(taskId);
		
		taskEntryDAO.createUpdateTaskEntry(task);
		
		return "redirect:/user-home";
	}
	
	@RequestMapping(path="/view-customers", method=RequestMethod.GET)
	public String viewCustomers(HttpServletRequest request) {
		
		List<Customer> customerList = customerDAO.getAllCustomers();
		
		request.setAttribute("customerList", customerList);
				
		return "view-customers";
	}
	
	@RequestMapping(path="/create-new-task", method=RequestMethod.GET)
	public String createNewTaskView(HttpServletRequest request) {
		
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		
		Project project = projectDAO.getProjectById(projectId);
		
		request.setAttribute("project", project);
		
		return "create-new-task";
	}
	
	@RequestMapping(path="/create-new-task", method=RequestMethod.POST)
	public String addNewTaskToProject(ModelMap model,
									  @RequestParam int projectId,
									  @RequestParam String taskName) {
		
		User user = (User) model.get("currentUser");
		
		Task task = new Task();
		
		task.setProjectId(projectId);
		task.setUserId(user.getUserId());
		task.setTaskName(taskName);
		
		taskDAO.addNewTask(task);
		
		return "redirect:/user-home";
	}
	
}






