package com.quickfee.helpers;

import com.quickfee.models.entities.Employee;
import com.quickfee.models.transferobjects.EmployeeTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeHelper {

    public EmployeeTO mapToEmployeeTO(Employee employee) {

        return EmployeeTO.builder()
            .employee_id(employee.getEmployee_id())
            .firstName(employee.getFirstName())
            .lastName(employee.getLastName())
            .email(employee.getEmail())
            .designation(employee.getDesignation())
            .createdDate(employee.getCreatedDate())
            .updatedDate(employee.getUpdatedDate())
            .projects(employee.getProjects())
            .build();
    }

    public List<EmployeeTO> mapToEmployeeTO(List<Employee> employees) {
        return employees.stream()
            .map(employee -> mapToEmployeeTO(employee))
            .collect(Collectors.toList());
    }
}
