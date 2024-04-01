package com.nishasoni.emsbackend.service.Impl;

import com.nishasoni.emsbackend.dto.EmployeeDto;
import com.nishasoni.emsbackend.entity.Employee;
import com.nishasoni.emsbackend.exception.ResourceNotFoundException;
import com.nishasoni.emsbackend.mapper.EmployeeMapper;
import com.nishasoni.emsbackend.repository.EmployeeRepository;
import com.nishasoni.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Employee is not exists with given id : " + employeeId));

            return EmployeeMapper.mapToEmployeeDto(employee);
        }


    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exists with given id: " + employeeId)
        );

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not exists with given id: " + employeeId)
        );

        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(String departmentName) {
        return employeeRepository.findByDepartment(departmentName);
    }

    @Override
    public void uploadImage(Long id, MultipartFile file) throws IOException {
        byte[] imageData = file.getBytes();
        employeeRepository.saveImage(id, imageData);
    }

    @Override
    public byte[] downloadImage(Long id) {
        return new byte[0];
    }
}
