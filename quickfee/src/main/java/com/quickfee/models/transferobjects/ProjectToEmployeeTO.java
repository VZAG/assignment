package com.quickfee.models.transferobjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectToEmployeeTO {

    private Integer employeeId;
    private Integer projectId;
}
