package eims.service;

import eims.model.acad.CourseFounded;
import eims.exception.CourseFoundedNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface CourseFoundedService {



    public CourseFounded findById(BigInteger id);
    
    public CourseFounded create(CourseFounded courseFounded);
    
    public CourseFounded update(CourseFounded courseFounded) throws CourseFoundedNotFoundException;
    
    public CourseFounded copy(CourseFounded courseFounded);
    
    public CourseFounded delete(BigInteger id) throws CourseFoundedNotFoundException;
   
    public Iterable<CourseFounded> search(_SearchDTO pageable);
    
    public Iterable<CourseFounded> findAll(_SearchDTO pageable);
    
    public Iterable<CourseFounded> findAll();
}