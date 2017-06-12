package com.amh.pm.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amh.pm.entity.Organization;
import com.amh.pm.entity.User;
import com.amh.pm.service.OrganizationService;
import com.amh.pm.service.UserService;


@Controller
public class OrganizationController {
	
	private OrganizationService organizationService;
	private UserService userService;
	HttpSession session;

    @Autowired(required = true)
    @Qualifier(value = "organizationService")
    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
    
    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

	@RequestMapping(value = "/organizations", method = RequestMethod.GET)
	public ModelAndView showOrganizations(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		session = request.getSession();
		
		if ((session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))) {
			model.setViewName("redirect:login");
			return model;
			
		}
		int userId = (Integer) request.getSession().getAttribute("userId");
		model.setViewName("organizations");

		List<Organization> orgList = organizationService.findAll();
		List<Organization> orgListByUser = new ArrayList<Organization>();
		List<Organization> orgListByMember = new ArrayList<Organization>();
		for (Organization org : orgList) {
			if (org.getOwner().getId() == userId) {
				orgListByUser.add(org);
			} else {
				orgListByMember.add(org);
			}
		}
		session.setAttribute("userId", userId);
		//session.setAttribute("orgList", orgList);
		model.addObject("orgListByUser", orgListByUser);
		model.addObject("orgListByMember", orgListByMember);

		return model;

	}
    
    @RequestMapping(value = "/organizations/new", method = RequestMethod.GET)
    public String showOrganizationNewForm(HttpServletRequest request, Model model) {
    	
		session = request.getSession();
		
		if ((session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))) {
			return "redirect:/login";
		}		
    	
        model.addAttribute("organization", new Organization());
        return "addorganization";
    }

    @RequestMapping(value = "/neworganization", method = RequestMethod.POST)
    public String createOrganization(@Validated @ModelAttribute Organization organization, BindingResult result, Model model,
    		HttpServletRequest req) {
    	
    	if(result.hasErrors()){
    		return "redirect:/organizations/new";
    	}
    	int userId = (Integer) req.getSession().getAttribute("userId");
    	User user = userService.findById(userId);
    	
    	List<Organization> orgList = organizationService.findAll();
    	for(Organization org: orgList){
    		if(org.getName().equals(organization.getName())){
    			//exist Organization
    			String existOrg = "Exist Organization Name!";
    			session.setAttribute("existOrg", existOrg);
    			return "redirect:/organizations/new";
    		}
    	}
    	
    	organization.setOwner(user);
        organizationService.add(organization);
        return "redirect:organizations";
    }


}
