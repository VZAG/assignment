package com.quickfee.models.transferobjects;

import com.quickfee.models.entities.Project;
import com.quickfee.models.enums.Designation;
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
public class EmployeeTO {

    private Integer employee_id;
    private String firstName;
    private String lastName;
    private String email;
    private Designation designation;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<Project> projects = new ArrayList<>();

}
