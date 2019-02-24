package phoviet.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import phoviet.company.dao.UserDao;
import phoviet.company.entity.User;


@Controller
public class LoginController {
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, HttpSession session, @RequestParam("uname") String username, 
			@RequestParam("psw") String password ) {
		// login
		
		User entity =  userDao.findByUsernameAndPassword(username, password);
		String viewPage = "index";
		
		if(entity == null) {
			viewPage = "redirect:/";	
		}
		else {
			viewPage = "redirect:/user-info";
			session.setAttribute("user", entity);
			model.addAttribute("user", entity); 
		}			
		return viewPage;
	}
	
	@RequestMapping(value="/user-info", method=RequestMethod.GET)
	public String userInfo(Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "login/login";
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("module", "user-info");
		return "user/user_info";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		session.invalidate();  
		return "redirect:/";
	}
}
