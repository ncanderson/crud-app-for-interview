package nate.anderson.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import nate.anderson.dao.CustomerDAO;
import nate.anderson.dao.ProjectDAO;
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
	
	@Autowired
	public UserController(UserDAO userDAO, CustomerDAO customerDAO, ProjectDAO projectDAO) {
		this.userDAO = userDAO;
		this.customerDAO = customerDAO;
		this.projectDAO = projectDAO;
	}
	
	@RequestMapping(path="/user-home", method=RequestMethod.GET)
	public String userHomePage(HttpServletRequest request,
							   ModelMap model) {
		
		User user = (User) model.get("currentUser");
		
		List<Project> userProjects = projectDAO.getProjectsByUser(user);
		
		request.setAttribute("userProjects", userProjects);
		
		return "user-home";
	}

	@RequestMapping(path="/update-user", method=RequestMethod.GET)
	public String updateUserInformation() {
		
		return "update-user";
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
