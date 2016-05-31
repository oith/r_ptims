package eims.dao;

import java.util.List;
import eims.model.acad.Student;

public interface StudentDao {

    Student findById(Long id);

    void saveStudent(Student employee);

    void deleteStudentByCode(String code);

    List<Student> findAllStudents();

    Student findStudentByCode(String code);

}
