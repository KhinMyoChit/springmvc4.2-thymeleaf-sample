package com.amh.pm.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.amh.pm.entity.Project;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Project project) {
        entityManager.persist(project);
    }

    @Override
    public void edit(Project project) {

    }

    @Override
    public void delete(int id) {
        Project project = findById(id);
        entityManager.remove(project);
    }

    @Override
    public List<Project> findAll() {
        return entityManager.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }

    @Override
    public Project findById(int id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public List<Project> findByOrganizationId(int organizationId) {
        
        Query quary = entityManager.createQuery("SELECT p FROM Project p WHERE p.organization_id=?");
        quary.setParameter(1, organizationId);
        List<Project> projects = quary.getResultList();
        return projects; 
    }
}
