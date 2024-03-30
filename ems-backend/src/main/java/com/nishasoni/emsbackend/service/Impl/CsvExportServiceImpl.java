package com.nishasoni.emsbackend.service.Impl;

import com.nishasoni.emsbackend.dto.EmployeeDto;
import com.nishasoni.emsbackend.service.CsvExportService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
public class CsvExportServiceImpl implements CsvExportService {


    @Override
    public ByteArrayInputStream exportEmployeesToCsv(List<EmployeeDto> employees) throws IOException {
        String[] headers = { "ID", "First Name", "Last Name", "Email", "Department" };

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(out, StandardCharsets.UTF_8),
                     CSVFormat.DEFAULT.withHeader(headers))) {

            for (EmployeeDto employee : employees) {
                csvPrinter.printRecord(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail(),
                        employee.getDepartment().getDepartmentName()
                );
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
    }

