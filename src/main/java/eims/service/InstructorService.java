package eims.service;

import eims.model.acad.Instructor;
import eims.exception.InstructorNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface InstructorService {

    public Instructor findByCode(String code);

    public Instructor findById(BigInteger id);
    
    public Instructor create(Instructor instructor);
    
    public Instructor update(Instructor instructor) throws InstructorNotFoundException;
    
    public Instructor copy(Instructor instructor);
    
    public Instructor delete(BigInteger id) throws InstructorNotFoundException;
   
    public Iterable<Instructor> search(_SearchDTO pageable);
    
    public Iterable<Instructor> findAll(_SearchDTO pageable);
    
    public Iterable<Instructor> findAll();
}