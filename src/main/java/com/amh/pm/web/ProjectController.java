package com.amh.pm.web;

import java.util.ArrayList;
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

import com.amh.pm.entity.Organization;
import com.amh.pm.entity.Project;
import com.amh.pm.entity.User;
import com.amh.pm.service.OrganizationService;
import com.amh.pm.service.ProjectService;
import com.amh.pm.service.UserService;

@Controller
public class ProjectController {

    private ProjectService projectService;

    private OrganizationService organizationService;

    HttpSession session;

    @Autowired(required = true)
    @Qualifier(value = "projectService")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired(required = true)
    @Qualifier(value = "organizationService")
    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/organizations/{id}/projects", method = RequestMethod.GET)
    public String showProjects(@PathVariable("id") int organizationId, Model model, HttpServletRequest request) {

        session = request.getSession();
        if ((session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))) {
            return "redirect:/login";
        }

        List<Project> projects = projectService.findAll();
        List<Project> projectsByOrganizationId = new ArrayList<Project>();
        for (Project project : projects) {
            if (project.getOrganization().getId() == organizationId) {
                projectsByOrganizationId.add(project);
            }
        }
        model.addAttribute("projects", projectsByOrganizationId);
        model.addAttribute("organizationId", organizationId);

        return "projects";
    }

    @RequestMapping(value = "/organizations/{id}/projects/new", method = RequestMethod.GET)
    public String showProjectNewForm(@PathVariable("id") int organizationId, HttpServletRequest request, Model model) {

        session = request.getSession();

        if ((session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))) {
            return "redirect:/login";
        }

        model.addAttribute("organizationId", organizationId);
        model.addAttribute("project", new Project());

        return "addProject";
    }

    @RequestMapping(value = "/organizations/{id}/projects/new", method = RequestMethod.POST)
    public String createProject(@PathVariable("id") int organizationId, @Validated @ModelAttribute Project project, BindingResult result, Model model, HttpServletRequest request) {

        session = request.getSession();
        if ((session.getAttribute("userId") == null || session.getAttribute("userId").equals(""))) {
            return "redirect:/login";
        }

        Organization organization = organizationService.findById(organizationId);

        if (organization != null) {
            project.setOrganization(organization);
            projectService.add(project);
        }
        showProjects(organizationId, model, request);
        return "projects";
    }
}
