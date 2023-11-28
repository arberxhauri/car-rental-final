package com.example.carrental.controller;

import com.example.carrental.dto.BranchDto;
import com.example.carrental.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private BranchService branchService;

    @PostMapping("/save")
    public BranchDto save(@RequestBody BranchDto branchDto){
        return branchService.save(branchDto);
    }

    @GetMapping("/list")
    public List<BranchDto> findAll(){
        return branchService.findAll();
    }

    @GetMapping("/{branchId}")
    public BranchDto findById(@PathVariable(name = "branchId") long branchId){
        return branchService.findById(branchId);
    }

    @PutMapping("/update/{branchId}")
    public BranchDto update(@RequestBody BranchDto branchDto, @PathVariable(name = "branchId") long branchId){
        return branchService.update(branchDto, branchId);
    }
}
