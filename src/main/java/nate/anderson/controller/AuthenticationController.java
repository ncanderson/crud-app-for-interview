package nate.anderson.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import nate.anderson.dao.UserDAO;
import nate.anderson.model.User;

@Controller
@SessionAttributes("currentUser")
public class AuthenticationController {

	private UserDAO userDAO;
	
	@Autowired
	public AuthenticationController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(ModelMap model,
					    @RequestParam String username,
					    @RequestParam String password,
					    @RequestParam(required=false) String destination,
					    HttpSession session) {
			
		if (userDAO.validUser(username, password)) {
			session.invalidate();
			
			User user = userDAO.getUserByCredentials(username, password);
			
			model.put("currentUser", user);
			
			if (isValidRedirect(destination)) {
				return "redirect:" + destination;				
			}
			else {
				return "redirect:/user-home";
			}
			
		}
		
		return "redirect:/login";
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		
		model.remove("currentUser");
		session.removeAttribute("currentUser");
		return "redirect:/";
	}
	
	private boolean isValidRedirect(String destination) {
		return destination != null && destination.startsWith("http://localhost");
	}
	
}