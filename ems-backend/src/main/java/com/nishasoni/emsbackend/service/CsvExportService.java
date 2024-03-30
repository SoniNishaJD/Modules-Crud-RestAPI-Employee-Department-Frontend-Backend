package com.nishasoni.emsbackend.service;

import com.nishasoni.emsbackend.dto.EmployeeDto;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface CsvExportService {

    ByteArrayInputStream exportEmployeesToCsv(List<EmployeeDto> employees) throws IOException;
}
