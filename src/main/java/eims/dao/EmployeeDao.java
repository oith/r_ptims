package eims.dao;

import java.util.List;
import eims.model.hr.Employee;

public interface EmployeeDao {

    Employee findById(Long id);

    void saveEmployee(Employee employee);

    void deleteEmployeeByCode(String code);

    List<Employee> findAllEmployees();

    Employee findEmployeeByCode(String code);

}
