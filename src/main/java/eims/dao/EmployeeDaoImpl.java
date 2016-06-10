package eims.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import eims.model.hr.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Long, Employee> implements EmployeeDao {

    @Override
    public Employee findById(Long id) {
        return getByKey(id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        persist(employee);
    }

    @Override
    public void deleteEmployeeByCode(String code) {
        delete(findEmployeeByCode(code));
//        Query query = getSession().createSQLQuery("DELETE FROM EMP WHERE CODE = :code");
//        query.setString("code", code);
//        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> findAllEmployees() {
        Criteria criteria = createEntityCriteria();
        return (List<Employee>) criteria.list();
    }

    @Override
    public Employee findEmployeeByCode(String code) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("code", code));
        return (Employee) criteria.uniqueResult();
    }
}
