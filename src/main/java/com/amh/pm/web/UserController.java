package com.amh.pm.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amh.pm.entity.User;
import com.amh.pm.service.UserService;

@Controller
public class UserController {

    private UserService userService;
    HttpSession session;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }   
    
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(@Validated @ModelAttribute User user, BindingResult result,
			HttpServletRequest request) {

    	ModelAndView  model = new ModelAndView();
    	session = request.getSession(true);
		String name = user.getName();
		String password = user.getPassword();

		List<User> u = userService.userByName(name, password);
		
		if (u.size()== 0) {
			
			session.setAttribute("usermessage", "Username and Password are incorrect!");
			model.setViewName("redirect:login");
			return model;
		}

		else {
			for(User userList: u){
				session.setAttribute("userId", userList.getId());	
			}
			model.setViewName("redirect:organizations");
			return model;
		}
	}
    
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addCustomer(@Validated @ModelAttribute User user, BindingResult result, Model model) {
        userService.add(user);
        return "redirect:users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", this.userService.findAll());
        return "users";
    }

    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)
    public String showDetailForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));  
        session.setAttribute("userid", id);
        return "userDetails";
    }
    
    @RequestMapping(value = "/user/{id}/edited", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") int userid,@Validated @ModelAttribute User user, BindingResult result, Model model) {  
    	User u = userService.findById(userid);
    	userService.edit(u);
        return "redirect:users";
    }
    
   @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") int id, Model model) {
  
    	userService.delete(id);
       
        return "redirect:users";
    }
    
    

}
