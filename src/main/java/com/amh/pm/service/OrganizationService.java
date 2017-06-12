package com.amh.pm.service;

import java.util.List;

import com.amh.pm.entity.Organization;

public interface OrganizationService {
	void add(Organization organization);
    void edit(Organization organization);
    void delete(int id);

    List<Organization> findAll();

    Organization findById(int id);
	void save(Organization organization);
}
