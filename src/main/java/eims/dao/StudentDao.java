package eims.dao;

import java.util.List;
import eims.model.acad.ProfessionalStudent;

public interface StudentDao {

    ProfessionalStudent findById(Long id);

    void saveStudent(ProfessionalStudent employee);

    void deleteStudentByCode(String code);

    List<ProfessionalStudent> findAllStudents();

    ProfessionalStudent findStudentByCode(String code);

}
