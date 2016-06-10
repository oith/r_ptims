package eims.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import eims.model.acad.ProfessionalStudent;

@Repository("studentDao")
public class StudentDaoImpl extends AbstractDao<Long, ProfessionalStudent> implements StudentDao {

    @Override
    public ProfessionalStudent findById(Long id) {
        return getByKey(id);
    }

    @Override
    public void saveStudent(ProfessionalStudent employee) {
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
    public List<ProfessionalStudent> findAllStudents() {
        Criteria criteria = createEntityCriteria();
        return (List<ProfessionalStudent>) criteria.list();
    }

    @Override
    public ProfessionalStudent findStudentByCode(String code) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("code", code));
        return (ProfessionalStudent) criteria.uniqueResult();
    }
}
