package com.nishasoni.emsbackend.repository;

import com.nishasoni.emsbackend.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
