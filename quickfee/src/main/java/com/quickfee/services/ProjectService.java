package com.quickfee.services;

import com.quickfee.helpers.ProjectHelper;
import com.quickfee.models.entities.Employee;
import com.quickfee.models.entities.Project;
import com.quickfee.models.transferobjects.ProjectTO;
import com.quickfee.models.transferobjects.ProjectToEmployeeTO;
import com.quickfee.repositories.EmployeeRepository;
import com.quickfee.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectHelper projectHelper;

    public List<ProjectTO> getProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectHelper.mapToProjectTO(projects);
    }

    public ProjectTO create(Project project) {
        project.setCreatedDate(LocalDateTime.now());
        Project savedProject = projectRepository.save(project);
        return projectHelper.mapToProjectTO(savedProject);
    }

    public ProjectTO update(Project project, Integer id) {
        Project projectFromDb = projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(String.format("Invalid project id: %d", id)));

        projectFromDb.setName(StringUtils.isEmpty(project.getName()) ? projectFromDb.getName() : project.getName());

        if(project.getCost() != null) {
            projectFromDb.setCost(project.getCost());
        }

        if(project.getType() != null) {
            projectFromDb.setType(project.getType());
        }

        if(project.getStartDate() != null) {
            projectFromDb.setStartDate(project.getStartDate());
        }

        if(project.getEndDate() != null) {
            projectFromDb.setEndDate(project.getEndDate());
        }

        if(!CollectionUtils.isEmpty(project.getEmployees())) {
            projectFromDb.setEmployees(project.getEmployees());
        }

        projectFromDb.setUpdatedDate(LocalDateTime.now());
        Project updatedProject = projectRepository.save(projectFromDb);
        return projectHelper.mapToProjectTO(updatedProject);
    }

    public ProjectTO assignProjectToEmployee(ProjectToEmployeeTO assignment) {

        Project project = getProject(assignment);
        Employee employee = getEmployee(assignment);

        List<Project> employeeProjects = employee.getProjects();
        employeeProjects.add(project);

        employee.setProjects(employeeProjects);
        employeeRepository.save(employee);

        return projectHelper.mapToProjectTO(project);
    }

    public ProjectTO removeProjectFromEmployee(ProjectToEmployeeTO assignment) {

        Project project = getProject(assignment);
        Employee employee = getEmployee(assignment);

        List<Project> employeeProjects = employee.getProjects();
        employeeProjects.remove(project);

        employee.setProjects(employeeProjects);
        employeeRepository.save(employee);

        return projectHelper.mapToProjectTO(project);
    }

    private Employee getEmployee(ProjectToEmployeeTO assignment) {
        return employeeRepository.findById(assignment.getEmployeeId())
            .orElseThrow(() -> new RuntimeException(
                String.format("Invalid Employee id: %d", assignment.getEmployeeId())));
    }

    private Project getProject(ProjectToEmployeeTO assignment) {
        return projectRepository.findById(assignment.getProjectId())
                .orElseThrow(() -> new RuntimeException(
                    String.format("Invalid project id: %d", assignment.getProjectId())));
    }

    public ProjectTO retrieveProjectByEmployeeIds(List<Integer> employeeIds) {

        // verify all the employeeIds are valid
        employeeIds.forEach(id -> employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(
                String.format("Invalid Employee id: %d", id))));

        Project project = projectRepository.getProject(employeeIds)
            .orElseThrow(() -> new RuntimeException("There is no project associated with given employee ids"));

        return projectHelper.mapToProjectTO(project);
    }
}
