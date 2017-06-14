package com.amh.pm.service;

import java.util.List;
import com.amh.pm.entity.Project;

public interface ProjectService {

    void add(Project project);

    void edit(Project project);

    void delete(int id);

    List<Project> findAll();

    Project findById(int id);

    List<Project> findByOrganizationId(int orgId);
}
