package eims.service;

import eims.model.acad.Course;
import eims.exception.CourseNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface CourseService {



    public Course findById(BigInteger id);
    
    public Course create(Course course);
    
    public Course update(Course course) throws CourseNotFoundException;
    
    public Course copy(Course course);
    
    public Course delete(BigInteger id) throws CourseNotFoundException;
   
    public Iterable<Course> search(_SearchDTO pageable);
    
    public Iterable<Course> findAll(_SearchDTO pageable);
    
    public Iterable<Course> findAll();
}