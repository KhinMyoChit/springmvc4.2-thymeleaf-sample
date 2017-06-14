package com.amh.pm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.amh.pm.dao.ProjectDao;
import com.amh.pm.entity.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
    
    @Override
    @Transactional
    public void add(Project project) {
        projectDao.add(project);
    }

    @Override
    @Transactional
    public void edit(Project project) {
        projectDao.edit(project);
    }

    @Override
    @Transactional
    public void delete(int id) {
        projectDao.delete(id);
    }

    @Override
    @Transactional
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    @Transactional
    public Project findById(int id) {
        return projectDao.findById(id);
    }

    @Override
    @Transactional
    public List<Project> findByOrganizationId(int organizationId) {    
        return projectDao.findByOrganizationId(organizationId);
    }
}
