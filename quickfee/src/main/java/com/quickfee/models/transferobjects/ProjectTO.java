package com.quickfee.models.transferobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quickfee.models.entities.Employee;
import com.quickfee.models.entities.Project;
import com.quickfee.models.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTO {

    private Integer project_id;
    private String name;
    private Double cost;
    private Type type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<Employee> employees = new ArrayList<>();

}
