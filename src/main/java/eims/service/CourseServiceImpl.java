package eims.service;

import eims.model.acad.Course;
import eims.dto._SearchDTO;
import eims.exception.CourseNotFoundException;
import eims.repositories.CourseRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("courseService")
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;



    @Transactional
    @Override
    public Course create(Course lookup) {
        return courseRepository.save(lookup);
    }

    @Override
    @Transactional
    public Course findById(BigInteger id) {
        Course course=courseRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return course;
    }

    @Override
    @Transactional(rollbackFor = CourseNotFoundException.class)
    public Course delete(BigInteger id) throws CourseNotFoundException {

        Course course = courseRepository.findOne(id);

        if (course == null) {
            throw new CourseNotFoundException();
        }
        courseRepository.delete(id);
        return course;
    }

    @Override
    @Transactional
    public Iterable<Course> findAll() {
        Iterable<Course> courses=courseRepository.findAll();
        
        for (Course course : courses) {

        //Hibernate.initialize(course.getA());
        //Hibernate.initialize(course.getZs());
        }
        
        return courses;
    }

    @Transactional(rollbackFor = CourseNotFoundException.class)
    @Override
    public Course update(Course updated) throws CourseNotFoundException {

        Course course = courseRepository.findOne(updated.getId());

        if (course == null) {
            throw new CourseNotFoundException();
        }

        BeanUtils.copyProperties(updated, course);
        return courseRepository.save(course);
    }

    @Transactional(rollbackFor = CourseNotFoundException.class)
    @Override
    public Course copy(final Course copied) {

        Course course = new Course();
        BeanUtils.copyProperties(copied, course);
        course.setId(null);

        return courseRepository.save(course);
    }

    @Transactional
    @Override
    public Iterable<Course> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Course> search(_SearchDTO pageable) {
        return findAll();
    }
}