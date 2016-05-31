package eims.service;

import java.util.List;

import eims.model.acad.Student;

public interface StudentService {

    Student findById(Long id);

    void saveStudent(Student employee);

    void updateStudent(Student employee);

    void deleteStudentByCode(String code);

    List<Student> findAllStudents();

    Student findStudentByCode(String code);

    boolean isStudentCodeUnique(Long id, String code);

}
