package nate.anderson.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
					    HttpSession session) {
	
		User user = userDAO.getUserByCredentials(username, password);
		
		if (user != null) {
			session.invalidate();
			
			model.put("currentUser", user);
			
			return "redirect:/user-home";
		}
		
		return "redirect:/home";
	}
	
}