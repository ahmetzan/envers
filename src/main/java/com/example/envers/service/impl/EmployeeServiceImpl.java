package com.example.envers.service.impl;

import com.example.envers.model.entity.Employee;
import com.example.envers.model.request.EmployeeSaveRequest;
import com.example.envers.repository.EmployeeRepository;
import com.example.envers.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void saveOrUpdate(EmployeeSaveRequest saveRequest) {

        Employee employee = employeeRepository.findById(saveRequest.id())
                .orElseGet(Employee::new);

        employee.setBirthDate(saveRequest.birthDate());
        employee.setName(saveRequest.name());
        employee.setSurname(saveRequest.surname());
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public int bulkSave() {

        List<Employee> saveList = new ArrayList<>();
        for (int i = 0 ; i < 5000 ; i++) {
            Employee employee = new Employee();
            employee.setBirthDate(new Date());
            employee.setName("name+" + i);
            employee.setSurname("surname+ " + i);
            saveList.add(employee);
        }
        employeeRepository.saveAll(saveList);
        return saveList.size();
    }
}
