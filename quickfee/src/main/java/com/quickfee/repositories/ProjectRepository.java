package com.quickfee.repositories;

import com.quickfee.models.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query(nativeQuery = true,
        value = "SELECT p.*  FROM project p JOIN project_employee USING (project_id) WHERE employee_id IN (:employeeIds) limit 1")
    Optional<Project> getProject(List<Integer> employeeIds);

}
