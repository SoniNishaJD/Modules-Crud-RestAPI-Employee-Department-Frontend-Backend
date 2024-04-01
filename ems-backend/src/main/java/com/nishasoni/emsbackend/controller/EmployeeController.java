package com.nishasoni.emsbackend.controller;

import com.nishasoni.emsbackend.dto.EmployeeDto;
import com.nishasoni.emsbackend.entity.Employee;
import com.nishasoni.emsbackend.repository.EmployeeRepository;
import com.nishasoni.emsbackend.service.CsvExportService;
import com.nishasoni.emsbackend.service.EmployeeService;
import com.nishasoni.emsbackend.service.Impl.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    private CsvExportService csvExportService;

    private EmployeeServiceImpl employeeService;

    private EmployeeRepository repository;


    // Build Add Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    // Build Get All Employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Build Update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDto updatedEmployee) {
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    // Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!.");
    }

    @GetMapping("/department/{departmentName}")
    public List<EmployeeDto> getEmployeesByDepartment(@PathVariable String departmentName) {
        return employeeService.getEmployeesByDepartment(departmentName);
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadEmployeeCsv() throws IOException {
        List<EmployeeDto> employees = employeeService.getAllEmployees();

        ByteArrayInputStream stream = csvExportService.exportEmployeesToCsv(employees);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employees.csv");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(stream));
    }

    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        employeeService.uploadImage(id, file);
        return ResponseEntity.ok("Image Uploaded Successfully");
    }

    @GetMapping("/{id}/downloadImage")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        byte[] imageData = employeeService.downloadImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }

}
