package eims.service;

import java.util.List;

import eims.model.hr.Employee;
import java.math.BigInteger;

public interface EmployeeService {

    Employee findById(BigInteger id);

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeByCode(String code);

    List<Employee> findAllEmployees();

    Employee findEmployeeByCode(String code);

    boolean isEmployeeCodeUnique(BigInteger id, String code);

}
