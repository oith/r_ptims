package eims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eims.dao.StudentDao;
import eims.model.acad.ProfessionalStudent;
import java.util.Objects;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao dao;

    public ProfessionalStudent findById(Long id) {
        return dao.findById(id);
    }

    public void saveStudent(ProfessionalStudent student) {
        dao.saveStudent(student);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
    @Override
    public void updateStudent(ProfessionalStudent student) {
        ProfessionalStudent entity = dao.findById(student.getId());
        if (entity != null) {
            entity.setName(student.getName());
            entity.setGender(student.getGender());
            //entity.setJoiningDate(student.getJoiningDate());
            //entity.setSalary(student.getSalary());
            entity.setCode(student.getCode());
        }
    }

    @Override
    public void deleteStudentByCode(String code) {
        dao.deleteStudentByCode(code);
    }

    @Override
    public List<ProfessionalStudent> findAllStudents() {
        return dao.findAllStudents();
    }

    @Override
    public ProfessionalStudent findStudentByCode(String code) {
        return dao.findStudentByCode(code);
    }

    public boolean isStudentCodeUnique(Long id, String code) {
        ProfessionalStudent student = findStudentByCode(code);
        return (student == null || ((id != null) && (Objects.equals(student.getId(), id))));
    }

}
