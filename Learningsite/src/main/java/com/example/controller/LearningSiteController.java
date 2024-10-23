package com.example.controller;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.User;
import com.example.service.UserService;

@Controller
//@RequestMapping("/LeardEd")
public class LearningSiteController 
{
	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	String testing()
	{
		return "home";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@PostMapping("/index")
	public String index(@ModelAttribute User vr)
	{
		return "index";
	}
	
	@RequestMapping("/jee")
	public String jee()
	{
		return "jee";
	}
	@RequestMapping("/gate")
	public String gate()
	{
		return "gate";
	}
	
	@RequestMapping("/computer_courses")
	public String comcourse()
	{
		return "computer_courses";
	}
	@RequestMapping("/index")
	public String toindex()
	{
		return "index";
	}
	
	@GetMapping("/chatbot")
	public String chatbot()
	{
		return "chatbot";
	}
	
//	@PostMapping("/register")
//	public String register(Model model, @RequestParam("fullname") String fullname, 
//            @RequestParam("email") String email, 
//            @RequestParam("password") String password, 
//            @RequestParam("confirmpassword") String confirmpassword) {
//	    if (userService.emailExists(email)) {
//	        model.addAttribute("errorMessage", "An account with this email already exists.");
//	         
//	    } else {
//	        User user=new User(email, password, fullname);
//	        userService.saveUser(user);
//	        model.addAttribute("user", user);
//	        model.addAttribute("successMessage", "Your account was created successfully.");
//	    }
//	    return "login";
//	}
	
	
	@PostMapping("/register")
    public String register(Model model, 
                           @RequestParam("fullname") String fullname, 
                           @RequestParam("email") String email, 
                           @RequestParam("password") String password, 
                           @RequestParam("confirmpassword") String confirmpassword) {
        
        // Check if passwords match
        if (!password.equals(confirmpassword)) {
            model.addAttribute("errorMessage", "Passwords do not match.");
            model.addAttribute("activeForm", "register");  // Stay on registration form
            return "login";  // Return to the login page (which contains both forms)
        }

        // Check if email already exists
        if (userService.emailExists(email)) {
            model.addAttribute("errorMessage", "An account with this email already exists.");
            model.addAttribute("activeForm", "register");  // Stay on registration form
            return "login";  // Return to the login page
        }

        // Save user if everything is correct
        User user = new User(email, password, fullname);
        userService.saveUser(user);
        model.addAttribute("successMessage", "Your account was created successfully.");
        model.addAttribute("activeForm", "login");  // After success, show login form

        return "login";  // Go back to the login page
    }
	
	
	
//	@PostMapping("/loginRequest")
//	public String loginRequest(@RequestParam(value="email") String email, @RequestParam(value="password") String password)
//	{
//		Boolean user=userService.emailExists(email);
//		if(user)
//		{
//			Boolean u1=userService.userLogin(email, password);
//			if(u1)
//				return "index";
//			else {
//				System.out.println("Invalid Credentials");
//				return "login";
//			}
//		}
//		else
//			System.out.println("Invalid Email");
//		return "login";
//	}
	
	@PostMapping("/loginRequest")
	public String loginRequest(@RequestParam(value="email") String email, 
	                           @RequestParam(value="password") String password,
	                           Model model) 
	{
	    Boolean user = userService.emailExists(email);
	    if (user) {
	        Boolean u1 = userService.userLogin(email, password);
	        if (u1) {
	            return "index";  // Redirect to index if login successful
	        } else {
	            model.addAttribute("errorMessage1", "Invalid Credentials");
	            return "login";  // Redirect back to login form with error
	        }
	    } else {
	        model.addAttribute("errorMessage1", "Invalid Email");
	        return "login";  // Redirect back to login form with error
	    }
	}
	
	
	@GetMapping("/searchbar")
	public String Searchbar(@RequestParam (value="searchkey") String searchkey)
	{
		if(searchkey.equalsIgnoreCase("jee"))
			return "jee";
		else if(searchkey.equalsIgnoreCase("gate"))
			return "gate";
		else if(searchkey.equalsIgnoreCase("java") || searchkey.equalsIgnoreCase("c") || searchkey.equalsIgnoreCase("c++") ||
				searchkey.equalsIgnoreCase("python") || searchkey.equalsIgnoreCase("data structure") || 
				searchkey.equalsIgnoreCase("javascript") || searchkey.equalsIgnoreCase("algorith") || searchkey.equalsIgnoreCase("computer courses") )
			return "computer_courses";
		return "home";
	}
}
