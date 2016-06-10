package eims.service;

import java.util.List;

import eims.model.acad.ProfessionalStudent;

public interface StudentService {

    ProfessionalStudent findById(Long id);

    void saveStudent(ProfessionalStudent employee);

    void updateStudent(ProfessionalStudent employee);

    void deleteStudentByCode(String code);

    List<ProfessionalStudent> findAllStudents();

    ProfessionalStudent findStudentByCode(String code);

    boolean isStudentCodeUnique(Long id, String code);

}
