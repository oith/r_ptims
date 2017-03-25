package eims.service;

import eims.model.acad.Student;
import eims.exception.StudentNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface StudentService {



    public Student findById(BigInteger id);
    
    public Student create(Student student);
    
    public Student update(Student student) throws StudentNotFoundException;
    
    public Student copy(Student student);
    
    public Student delete(BigInteger id) throws StudentNotFoundException;
   
    public Iterable<Student> search(_SearchDTO pageable);
    
    public Iterable<Student> findAll(_SearchDTO pageable);
    
    public Iterable<Student> findAll();
}