package com.nishasoni.emsbackend.service;

import com.nishasoni.emsbackend.dto.EmployeeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);

    void deleteEmployee(Long employeeId);

    List<EmployeeDto> getEmployeesByDepartment(String departmentName);

    void uploadImage(Long id, MultipartFile file) throws IOException;
    byte[] downloadImage(Long id);


}
