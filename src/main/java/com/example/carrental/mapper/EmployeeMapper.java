package com.example.carrental.mapper;

import com.example.carrental.dto.EmployeeDto;
import com.example.carrental.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeMapper {

    private BranchMapper branchMapper;

    public Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setPosition(employeeDto.getPosition());

        return employee;
    }

    public EmployeeDto mapToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setBranchId(employee.getBranch().getId());
        employeeDto.setBranchDto(branchMapper.mapToDto(employee.getBranch()));

        return employeeDto;
    }
}
