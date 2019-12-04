package com.javainuse.controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.Employee;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/employees"})
public class TestController {

    private List<Employee> employees;

    {
        try {
            employees = createList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(produces = "application/json")
    public List<Employee> firstPage() {
        return employees;
    }

    @DeleteMapping(path = {"/{id}"})
    public Employee delete(@PathVariable("id") int id) {
        Employee deletedEmp = null;
        for (Employee emp : employees) {
            if (emp.getEmpId().equals(id)) {
                employees.remove(emp);
                deletedEmp = emp;
                break;
            }
        }
        return deletedEmp;
    }

    @PostMapping
    public Employee create(@RequestBody Employee user) throws IOException {
        BufferedWriter bw = new BufferedWriter( new FileWriter("employee_db.txt",true) );
        Employee employee = new Employee();
        user.setEmpId(employee.getEmpId());
        bw.write(user.getEmpId()+","+user.getName()+","+user.getPhone()+","+user.getMail());
        bw.newLine();

        bw.flush();
        bw.newLine();
        bw.close();

        employees.add(user);
        return user;
    }

    private static List<Employee> createList() throws IOException {
        BufferedWriter bw = new BufferedWriter( new FileWriter("employee_db.txt",true) );
        List<Employee> tempEmployees = new ArrayList<>();
        Employee emp1 = new Employee();
        emp1.setName("azer");
        emp1.setPhone("+212611111111");
        emp1.setEmpId(1);
        emp1.setMail("azer@Adria.ma");
        bw.write(emp1.getEmpId()+","+emp1.getName()+","+emp1.getPhone()+","+emp1.getMail());
        bw.newLine();

        Employee emp2 = new Employee();
        emp2.setName("qwer");
        emp2.setPhone("+212622222222");
        emp2.setEmpId(2);
        emp2.setMail("qwer@Adria.ma");
        bw.write(emp2.getEmpId()+","+emp2.getName()+","+emp2.getPhone()+","+emp2.getMail());
        bw.newLine();

        bw.flush();
        bw.newLine();
        bw.close();

        tempEmployees.add(emp1);
        tempEmployees.add(emp2);
        return tempEmployees;
    }

}