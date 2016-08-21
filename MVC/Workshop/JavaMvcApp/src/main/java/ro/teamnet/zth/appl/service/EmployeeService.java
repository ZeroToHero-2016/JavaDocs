package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAllEmployees();
    public Employee findOneEmployee(Long id);
    public void deleteEmployee(Long id);
}
