package com.example.carrental.service;

import com.example.carrental.dto.EmployeeDto;
import com.example.carrental.entity.Branch;
import com.example.carrental.entity.Employee;
import com.example.carrental.mapper.EmployeeMapper;
import com.example.carrental.repository.BranchRepository;
import com.example.carrental.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private BranchRepository branchRepository;

    public EmployeeDto save(EmployeeDto employeeDto) {
        Branch existingBranch = branchRepository.findById(employeeDto.getBranchId()).orElseThrow(() ->
                new RuntimeException("Branch with id " + employeeDto.getBranchId() + " was not found!"));

        Employee employee = employeeMapper.mapToEntity(employeeDto);
        employee.setBranch(existingBranch);

        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToDto(savedEmployee);
    }

    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(employee -> employeeMapper.mapToDto(employee)).collect(Collectors.toList());
    }

    public EmployeeDto findById(long employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new RuntimeException("Employee with id " + employeeId + " was not found!"));

        return employeeMapper.mapToDto(existingEmployee);
    }

    public EmployeeDto update(EmployeeDto employeeDto, long employeeId){
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new RuntimeException("Employee with id " + employeeId + " was not found!"));

        Branch existingBranch = branchRepository.findById(employeeDto.getBranchId()).orElseThrow(() ->
                new RuntimeException("Branch with id " + employeeDto.getBranchId() + " was not found!"));

        Employee employee = employeeMapper.mapToEntity(employeeDto);
        employee.setId(employeeId);
        employee.setBranch(existingBranch);
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.mapToDto(savedEmployee);
    }

    public String delete(long employeeId){
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new RuntimeException("Employee with id " + employeeId + " was not found!"));

        employeeRepository.delete(existingEmployee);

        return "Employee with id " + employeeId +  " was deleted successfully";
    }

}
