package com.amh.pm.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amh.pm.dao.OrganizationDao;
import com.amh.pm.entity.Organization;

public class OrganizationServiceImpl implements OrganizationService {
	
	private OrganizationDao organizationDao;

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

	@Override
	@Transactional
	public void add(Organization organization) {
		organizationDao.add(organization);
		
	}

	@Override
	public void edit(Organization organization) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<Organization> findAll() {
		return organizationDao.findAll();
	}

	@Override
	@Transactional
	public Organization findById(int id) {
		return organizationDao.findById(id);
	}

	@Override
	@Transactional
	public void save(Organization organization) {
		organizationDao.save(organization);
		
	}

}
