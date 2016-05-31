package eims.service;

import java.util.List;

import eims.model.hr.Employee;

public interface EmployeeService {

    Employee findById(Long id);

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeByCode(String code);

    List<Employee> findAllEmployees();

    Employee findEmployeeByCode(String code);

    boolean isEmployeeCodeUnique(Long id, String code);

}
