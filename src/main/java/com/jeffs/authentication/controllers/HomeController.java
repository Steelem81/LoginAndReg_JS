package com.jeffs.authentication.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeffs.authentication.models.LoginUser;
import com.jeffs.authentication.models.User;
import com.jeffs.authentication.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userServ; 
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser")User newUser,
							BindingResult result,
							Model model,
							HttpSession session) {
		
		User user = userServ.register(newUser, result);
		
		if (user == null) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}else {
			session.setAttribute("userId", user.getId());
			return "redirect:/home";
		}	

	}
	
	@PostMapping("/login")
	public String loging(@Valid @ModelAttribute("newLogin")LoginUser newLogin,
						BindingResult result,
						Model model,
						HttpSession session) {
		
		User user = userServ.login(newLogin, result);
		if (user == null) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		} else {
			session.setAttribute("userId", user.getId());
			return "redirect:/home";
		}
		
	}
	
	@GetMapping("/home")
	public String home(@ModelAttribute("user")User user, Model model, HttpSession session) {
		if(session != null) {
			return "redirect:/";
		}
		Long id = (Long) session.getAttribute("userId");
		model.addAttribute("user", userServ.getUserById(id));
		return "home.jsp";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
}
