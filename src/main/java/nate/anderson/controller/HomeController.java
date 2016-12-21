package nate.anderson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(path={"/", "/home"}, method=RequestMethod.GET)
	public String getHomePage() {
		return "home";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.GET) 
	public String login() {
		
		return "login";
	}
	
}
