package com.amh.pm.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amh.pm.entity.Organization;
import com.amh.pm.entity.User;
import com.amh.pm.service.OrganizationService;
import com.amh.pm.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class OrganizationMemberController {

    private UserService userService;

    private OrganizationService organizationService;

    HttpSession session;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "organizationService")
    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/organizations/{id}/members", method = RequestMethod.GET)
    public String showOrganizationMembers(@PathVariable("id") int orgid, Model model, HttpServletRequest request) {

        session = request.getSession();
        if ((session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))) {
            return "redirect:/login";
        }

        int loginUserId = (Integer) request.getSession().getAttribute("userId");
        List<User> userMemberList = userService.findUserNameByOrgnId(orgid);

        List<User> users = userService.findAll();

        List<String> members = new ArrayList<String>();
        for (User user : users) {
            members.add(user.getName());
        }
        Organization organization = organizationService.findById(orgid);
        if (organization.getOwner().getId() == loginUserId) {
            model.addAttribute("members", members);
            model.addAttribute("orgMembers", userMemberList);
            model.addAttribute("user", new User());
            model.addAttribute("orgid", orgid);
            return "organizationMember";

        } else {
            model.addAttribute("orgMembers", userMemberList);
            return "organizationMemberList";
        }
    }

    @RequestMapping(value = "/organizations/{id}/members", method = RequestMethod.POST)
    public String addOrganizationMember(@PathVariable("id") int orgid, @ModelAttribute User user, BindingResult result, Model model, HttpServletRequest request) {

        User u = userService.findUserIdByName(user.getName());
        Organization organization = organizationService.findById(orgid);

        session = request.getSession();
        session.removeAttribute("message");
        session.removeAttribute("existMember");

        if (u == null || organization == null) {

            String noUserName = "User name does not exists.";
            session.setAttribute("message", noUserName);
        } else {
            if (organization.getUserList().contains(u)) {
                // already exists
                String member = "Exist Member!";
                session.setAttribute("existMember", member);
            } else {
                organization.getUserList().add(u);
                organizationService.save(organization);
            }
        }
        showOrganizationMembers(orgid, model, request);
        return "organizationMember";
    }

    @RequestMapping(value = "/organizations/{id}/members/searchMembers", method = RequestMethod.GET)
    public void searchMembers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String text = request.getParameter("term");
        try {
            List<User> users = userService.getUsers(text);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String searchResult = gson.toJson(users);

            PrintWriter writer = response.getWriter();
            writer.write(searchResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
