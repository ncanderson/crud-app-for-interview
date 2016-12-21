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
	
	@Autowired
	public UserController(UserDAO userDAO, CustomerDAO customerDAO, ProjectDAO projectDAO, TaskDAO taskDAO) {
		this.userDAO = userDAO;
		this.customerDAO = customerDAO;
		this.projectDAO = projectDAO;
		this.taskDAO = taskDAO;
	}
	
	@RequestMapping(path="/user-home", method=RequestMethod.GET)
	public String userHomePage(HttpServletRequest request,
							   ModelMap model) {
		
		User user = (User) model.get("currentUser");
		
		List<Project> userProjects = projectDAO.getProjectsByUser(user);
		List<Task> userTasks = taskDAO.getTasksByUser(user);
		
		request.setAttribute("userProjects", userProjects);
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
	
	@RequestMapping(path="/new-projects", method=RequestMethod.GET)
	public String enterNewProject() {
		return null;
	}
	
	@RequestMapping(path="/view-project-details", method=RequestMethod.GET)
	public String viewProjects(HttpServletRequest request) {
		
		int projectId = Integer.parseInt(request.getParameter("projectId"));

		Project detailProject = projectDAO.getProjectById(projectId);
		
		request.setAttribute("detailProject", detailProject);
		
		return "view-project-details";
	}
	
	@RequestMapping(path="/view-customers", method=RequestMethod.GET)
	public String viewCustomers(HttpServletRequest request) {
		
		List<Customer> customerList = customerDAO.getAllCustomers();
		
		request.setAttribute("customerList", customerList);
				
		return "view-customers";
	}
	
}
