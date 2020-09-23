package com.quickfee.controllers;

import com.quickfee.models.entities.Employee;
import com.quickfee.models.transferobjects.EmployeeTO;
import com.quickfee.services.EmployeeService;
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
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeTO> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("id/{id}")
    public EmployeeTO getEmployeeById(@PathVariable("id") Integer id) {
        return employeeService.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeTO create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public EmployeeTO update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return employeeService.update(employee, id);
    }

    @GetMapping("email/{email}")
    public EmployeeTO getEmployeeByEmail(@PathVariable("email") String email) {
        return employeeService.findByEmail(email);
    }


}
