package com.quickfee.services;

import com.quickfee.helpers.EmployeeHelper;
import com.quickfee.models.entities.Employee;
import com.quickfee.models.transferobjects.EmployeeTO;
import com.quickfee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeHelper employeeHelper;

    public List<EmployeeTO> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeHelper.mapToEmployeeTO(employees);
    }

    public EmployeeTO findById(Integer id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(String.format("Invalid Employee id: %d", id)));
        return employeeHelper.mapToEmployeeTO(employee);
    }

    public EmployeeTO create(Employee employee) {
        employee.setCreatedDate(LocalDateTime.now());
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeHelper.mapToEmployeeTO(savedEmployee);
    }

    public EmployeeTO update(Employee employee, Integer id) {

        Employee employeeFromDb = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(String.format("Invalid Employee id: %d", id)));

        employeeFromDb.setEmail(
            StringUtils.isEmpty(employee.getEmail()) ? employeeFromDb.getEmail() : employee.getEmail());

        employeeFromDb.setFirstName(
            StringUtils.isEmpty(employee.getFirstName()) ? employeeFromDb.getFirstName() : employee.getFirstName());

        employeeFromDb.setLastName(
            StringUtils.isEmpty(employee.getLastName()) ? employeeFromDb.getLastName() : employee.getLastName());

        if(employee.getDesignation() != null) {
            employeeFromDb.setDesignation(employee.getDesignation());
        }

        employeeFromDb.setUpdatedDate(LocalDateTime.now());

        Employee updatedEmployee = employeeRepository.save(employeeFromDb);
        return employeeHelper.mapToEmployeeTO(updatedEmployee);
    }


    public EmployeeTO findByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException(String.format("Invalid email address: %s", email)));

        return employeeHelper.mapToEmployeeTO(employee);
    }
}
