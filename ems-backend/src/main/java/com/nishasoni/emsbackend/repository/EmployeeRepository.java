package com.nishasoni.emsbackend.repository;

import com.nishasoni.emsbackend.dto.EmployeeDto;
import com.nishasoni.emsbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<EmployeeDto> findByDepartment(String departmentName);
}
