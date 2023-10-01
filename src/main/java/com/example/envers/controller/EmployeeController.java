package com.example.envers.controller;

import com.example.envers.model.request.EmployeeSaveRequest;
import com.example.envers.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/save")
    public void createEmployee(@RequestBody EmployeeSaveRequest saveRequest) {
        employeeService.saveOrUpdate(saveRequest);
    }

    @DeleteMapping("/delete/{employeeId}")
    public void deleteEmployeeById(@PathVariable("employeeId") Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @PostMapping("/bulk")
    public int createBulkEmployee() {
        return employeeService.bulkSave();
    }
}
