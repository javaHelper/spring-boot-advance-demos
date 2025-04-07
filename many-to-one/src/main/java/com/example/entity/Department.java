package com.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setDepartment(this);
    }

    public void addEmployees(List<Employee> employees){
        for (Employee employee : employees) {
            addEmployee(employee);
        }
    }


    public void removeEmployee(Employee employee) {
        employee.setDepartment(null);
        this.employees.remove(employee);
    }

    public void removeEmployees() {
        Iterator<Employee> iterator = this.employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            employee.setDepartment(null);
            iterator.remove();
        }
    }
}
