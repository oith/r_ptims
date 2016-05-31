package eims.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import eims.model.acad.Student;

@Repository("studentDao")
public class StudentDaoImpl extends AbstractDao<Long, Student> implements StudentDao {

    @Override
    public Student findById(Long id) {
        return getByKey(id);
    }

    @Override
    public void saveStudent(Student employee) {
        persist(employee);
    }

    @Override
    public void deleteStudentByCode(String code) {
        delete(findStudentByCode(code));
        //Query query = getSession().createSQLQuery("DELETE FROM STD WHERE CODE = :code");
        //query.setString("code", code);
        //query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Student> findAllStudents() {
        Criteria criteria = createEntityCriteria();
        return (List<Student>) criteria.list();
    }

    @Override
    public Student findStudentByCode(String code) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("code", code));
        return (Student) criteria.uniqueResult();
    }
}
