package com.quickfee.helpers;

import com.quickfee.models.entities.Project;
import com.quickfee.models.transferobjects.ProjectTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectHelper {

    public ProjectTO mapToProjectTO(Project project) {
        return ProjectTO.builder()
            .project_id(project.getProject_id())
            .name(project.getName())
            .cost(project.getCost())
            .type(project.getType())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .createdDate(project.getCreatedDate())
            .updatedDate(project.getUpdatedDate())
            .employees(project.getEmployees())
            .build();
    }

    public List<ProjectTO> mapToProjectTO(List<Project> projects) {
        return projects.stream()
            .map(project -> mapToProjectTO(project))
            .collect(Collectors.toList());
    }
}
