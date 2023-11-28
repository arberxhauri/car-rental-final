package com.example.carrental.controller;


import com.example.carrental.dto.EmployeeDto;
import com.example.carrental.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/save")
    public EmployeeDto save(@RequestBody EmployeeDto employeeDto) {
        return employeeService.save(employeeDto);
    }

    @GetMapping("/list")
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDto findById(@PathVariable(name = "employeeId") long employeeId) {
        return employeeService.findById(employeeId);
    }

    @PutMapping("/update/{employeeId}")
    public EmployeeDto update(@RequestBody EmployeeDto employeeDto, @PathVariable(name = "employeeId") long employeeId) {
        return employeeService.update(employeeDto, employeeId);
    }

    @DeleteMapping("delete/{employeeId}")
    public String delete(@PathVariable(name = "employeeId") long employeeId) {
        return employeeService.delete(employeeId);
    }

}
