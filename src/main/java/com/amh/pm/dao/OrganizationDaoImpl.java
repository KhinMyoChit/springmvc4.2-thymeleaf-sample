package com.amh.pm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

import com.amh.pm.entity.Organization;
import com.amh.pm.entity.User;

public class OrganizationDaoImpl implements OrganizationDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(Organization organization) {
		entityManager.persist(organization);
		
	}

	@Override
	public List<Organization> findAll() {
		return entityManager.createQuery("SELECT o FROM Organization o", Organization.class).getResultList();
	}

	@Override
	public Organization findById(int id) {
		return entityManager.find(Organization.class, id);
	}

	@Override
	public void save(Organization organ) throws RollbackException {
		entityManager.merge(organ);
	}

	@Override
	public void edit(Organization organization) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
