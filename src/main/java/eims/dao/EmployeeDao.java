package eims.dao;

import java.util.List;
import eims.model.hr.Employee;
import java.math.BigInteger;

public interface EmployeeDao {

    Employee findById(BigInteger id);

    void saveEmployee(Employee employee);

    void deleteEmployeeByCode(String code);

    List<Employee> findAllEmployees();

    Employee findEmployeeByCode(String code);

}
