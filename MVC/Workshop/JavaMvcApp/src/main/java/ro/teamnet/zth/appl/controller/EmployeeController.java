package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.service.EmployeeService;
import ro.teamnet.zth.appl.service.EmployeeServiceImpl;

import java.util.List;

@MyController(urlPath = "/employees")
public class EmployeeController {

    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees(){
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.findAllEmployees();
    }

    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(@MyRequestParam(name="id") Long employeeId){
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.findOneEmployee(employeeId);
     //   return "oneEmployee";
    }

    @MyRequestMethod(urlPath = "/delete")
    public void deleteOneEmployee(@MyRequestParam(name="id") Long employeeId){
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.deleteEmployee(employeeId);
    }
}
