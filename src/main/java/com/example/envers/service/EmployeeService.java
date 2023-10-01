package com.example.envers.service;

import com.example.envers.model.request.EmployeeSaveRequest;

public interface EmployeeService {

    void saveOrUpdate(EmployeeSaveRequest saveRequest);

    void deleteEmployeeById(Long id);

    int bulkSave();
}
