package com.example.service;

import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public void insertDepartmentWithEmployees(){
        Department hrDepartment = new Department();
        hrDepartment.setName("HR");

        Employee employee1 = getEmployee("John Doe", 1000L);
        Employee employee2 = getEmployee("Mike Doe", 2000L);
        Employee employee3 = getEmployee("Sam Doe", 3000L);

        List<Employee> employeeList = Arrays.asList(employee1, employee2, employee3);
        hrDepartment.addEmployees(employeeList);

        departmentRepository.save(hrDepartment);

        Department itDepartment = new Department();
        itDepartment.setName("IT");

        Employee e1 = getEmployee("Neha Parate", 1000L);
        Employee e2 = getEmployee("Ashish Parate", 2000L);
        Employee e3 = getEmployee("Sagar Parate", 3000L);

        List<Employee> employees = Arrays.asList(e1, e2, e3);
        itDepartment.addEmployees(employees);

        departmentRepository.save(itDepartment);
    }

    private Employee getEmployee(String name, Long salary){
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSalary(salary);
        return employee;
    }


    @Transactional
    public void deleteEmployeeOfDept(){
        Department department = departmentRepository.findByName("HR");
        Employee employee = department.getEmployees().get(0);

        department.removeEmployee(employee);
    }

    @Transactional
    public void deleteAllEmployeesOfDept(){
        Department department = departmentRepository.findByName("HR");
        department.removeEmployees();
    }
}
