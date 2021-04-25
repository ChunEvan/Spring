package com.example.demo.dao;

import com.example.demo.pojo.Department;
import com.example.demo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>();

        employees.put(1001, new Employee(1001, "evan1", "evanhuangyx@163.com", 1, new Department(101,"教学部"), new Date()));
        employees.put(1002, new Employee(1002, "evan2", "evanhuangyx@163.com", 1, new Department(102,"市场部"), new Date()));
        employees.put(1003, new Employee(1003, "evan3", "evanhuangyx@163.com", 1, new Department(103,"教研部"), new Date()));
        employees.put(1004, new Employee(1004, "evan4", "evanhuangyx@163.com", 1, new Department(104,"运营部"), new Date()));
        employees.put(1005, new Employee(1005, "evan5", "evanhuangyx@163.com", 1, new Department(105,"设计部"), new Date()));
    }

    private static Integer initId = 1006;

    public void save(Employee employee){
        if (employee.getId()==null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }

}
