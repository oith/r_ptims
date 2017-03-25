package eims.service;

import eims.model.acad.Student;
import eims.dto._SearchDTO;
import eims.exception.StudentNotFoundException;
import eims.repositories.StudentRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService")
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;



    @Transactional
    @Override
    public Student create(Student lookup) {
        return studentRepository.save(lookup);
    }

    @Override
    @Transactional
    public Student findById(BigInteger id) {
        Student student=studentRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return student;
    }

    @Override
    @Transactional(rollbackFor = StudentNotFoundException.class)
    public Student delete(BigInteger id) throws StudentNotFoundException {

        Student student = studentRepository.findOne(id);

        if (student == null) {
            throw new StudentNotFoundException();
        }
        studentRepository.delete(id);
        return student;
    }

    @Override
    @Transactional
    public Iterable<Student> findAll() {
        Iterable<Student> students=studentRepository.findAll();
        
        for (Student student : students) {

        //Hibernate.initialize(student.getA());
        //Hibernate.initialize(student.getZs());
        }
        
        return students;
    }

    @Transactional(rollbackFor = StudentNotFoundException.class)
    @Override
    public Student update(Student updated) throws StudentNotFoundException {

        Student student = studentRepository.findOne(updated.getId());

        if (student == null) {
            throw new StudentNotFoundException();
        }

        BeanUtils.copyProperties(updated, student);
        return studentRepository.save(student);
    }

    @Transactional(rollbackFor = StudentNotFoundException.class)
    @Override
    public Student copy(final Student copied) {

        Student student = new Student();
        BeanUtils.copyProperties(copied, student);
        student.setId(null);

        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public Iterable<Student> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Student> search(_SearchDTO pageable) {
        return findAll();
    }
}