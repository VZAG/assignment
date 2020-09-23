package com.quickfee.controllers;

import com.quickfee.models.entities.Project;
import com.quickfee.models.transferobjects.ProjectEmployeesTO;
import com.quickfee.models.transferobjects.ProjectTO;
import com.quickfee.models.transferobjects.ProjectToEmployeeTO;
import com.quickfee.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<ProjectTO> getProjects() {
        return projectService.getProjects();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectTO create(@RequestBody Project project) {
        return projectService.create(project);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ProjectTO update(@PathVariable("id") Integer id, @RequestBody Project project) {
        return projectService.update(project, id);
    }

    @PostMapping(consumes = "application/json", path = "assign/project")
    @ResponseStatus(HttpStatus.OK)
    public ProjectTO assignProjectToEmployee(@RequestBody ProjectToEmployeeTO assignment) {
        return projectService.assignProjectToEmployee(assignment);
    }

    @PostMapping(consumes = "application/json", path = "remove/project")
    @ResponseStatus(HttpStatus.OK)
    public ProjectTO removeProjectFromEmployee(@RequestBody ProjectToEmployeeTO assignment) {
        return projectService.removeProjectFromEmployee(assignment);
    }

    @PostMapping(consumes = "application/json", path = "retrieve/project")
    public ProjectTO retrieveProjectByEmployeeIds(@RequestBody ProjectEmployeesTO employeesTO) {
        return projectService.retrieveProjectByEmployeeIds(employeesTO.getEmployeeIds());
    }

}
