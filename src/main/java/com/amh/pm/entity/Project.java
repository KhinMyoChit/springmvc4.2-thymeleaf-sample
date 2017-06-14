package com.amh.pm.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Project {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int project_id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "scheduleStartDate")
    private Date scheduleStartDate;

    @Column(name = "scheduleEndDate")
    private Date scheduleEndDate;

    @Column(name = "actualStartDat")
    private Date actualStartDate;

    @Column(name = "actualEndDate")
    private Date actualEndDate;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    private Organization organization;

    public Project() {
        super();
    }

    public Project(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public Project(String name, String description, Organization organization) {
        super();
        this.name = name;
        this.description = description;
        this.organization = organization;
    }

    public Project(int project_id, String name, String description, Organization organization) {
        super();
        this.project_id = project_id;
        this.name = name;
        this.description = description;
        this.organization = organization;
    }

    public Project(int project_id, String name, String description, Date scheduleStartDate, Date scheduleEndDate, Date actualStartDate, Date actualEndDate,
            Organization organization) {
        super();
        this.project_id = project_id;
        this.name = name;
        this.description = description;
        this.scheduleStartDate = scheduleStartDate;
        this.scheduleEndDate = scheduleEndDate;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
        this.organization = organization;
    }

    public Project(String name, String description, Date scheduleStartDate, Date scheduleEndDate, Date actualStartDate, Date actualEndDate, Organization organization) {
        super();
        this.name = name;
        this.description = description;
        this.scheduleStartDate = scheduleStartDate;
        this.scheduleEndDate = scheduleEndDate;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
        this.organization = organization;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(Date scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public Date getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(Date scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
