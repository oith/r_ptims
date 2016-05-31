package eims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eims.dao.EmployeeDao;
import eims.model.hr.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    public Employee findById(Long id) {
        return dao.findById(id);
    }

    public void saveEmployee(Employee employee) {
        dao.saveEmployee(employee);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
    public void updateEmployee(Employee employee) {
        Employee entity = dao.findById(employee.getId());
        if (entity != null) {
            entity.setName(employee.getName());
            entity.setFbId(employee.getFbId());
            entity.setGender(employee.getGender());
            entity.setJoiningDate(employee.getJoiningDate());
            entity.setSalary(employee.getSalary());
            entity.setCode(employee.getCode());
        }
    }

    @Override
    public void deleteEmployeeByCode(String code) {
        dao.deleteEmployeeByCode(code);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }

    @Override
    public Employee findEmployeeByCode(String code) {
        return dao.findEmployeeByCode(code);
    }

    public boolean isEmployeeCodeUnique(Long id, String code) {
        Employee employee = findEmployeeByCode(code);
        return (employee == null || ((id != null) && (employee.getId() == id)));
    }

}
