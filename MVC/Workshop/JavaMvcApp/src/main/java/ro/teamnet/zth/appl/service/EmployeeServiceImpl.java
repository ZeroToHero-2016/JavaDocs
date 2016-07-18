package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{
    public List<Employee> findAllEmployees(){
        EmployeeDao emp = new EmployeeDao();
        return emp.getAllEmployees();
    }

    @Override
    public Employee findOneEmployee(Long id) {
        EmployeeDao emp = new EmployeeDao();

        return emp.getEmployeeById(id);
    }

    @Override
    public void deleteEmployee(Long id) {
        EmployeeDao emp = new EmployeeDao();
        emp.deleteEmployee(findOneEmployee(id));
    }
}
