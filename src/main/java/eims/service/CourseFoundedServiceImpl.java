package eims.service;

import eims.model.acad.CourseFounded;
import eims.dto._SearchDTO;
import eims.exception.CourseFoundedNotFoundException;
import eims.repositories.CourseFoundedRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("courseFoundedService")
@Transactional(readOnly = true)
public class CourseFoundedServiceImpl implements CourseFoundedService {

    @Autowired
    private CourseFoundedRepository courseFoundedRepository;



    @Transactional
    @Override
    public CourseFounded create(CourseFounded lookup) {
        return courseFoundedRepository.save(lookup);
    }

    @Override
    @Transactional
    public CourseFounded findById(BigInteger id) {
        CourseFounded courseFounded=courseFoundedRepository.findOne(id);
            Hibernate.initialize(courseFounded.getRooms());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return courseFounded;
    }

    @Override
    @Transactional(rollbackFor = CourseFoundedNotFoundException.class)
    public CourseFounded delete(BigInteger id) throws CourseFoundedNotFoundException {

        CourseFounded courseFounded = courseFoundedRepository.findOne(id);

        if (courseFounded == null) {
            throw new CourseFoundedNotFoundException();
        }
        courseFoundedRepository.delete(id);
        return courseFounded;
    }

    @Override
    @Transactional
    public Iterable<CourseFounded> findAll() {
        Iterable<CourseFounded> courseFoundeds=courseFoundedRepository.findAll();
        
        for (CourseFounded courseFounded : courseFoundeds) {
            Hibernate.initialize(courseFounded.getRooms());

        //Hibernate.initialize(courseFounded.getA());
        //Hibernate.initialize(courseFounded.getZs());
        }
        
        return courseFoundeds;
    }

    @Transactional(rollbackFor = CourseFoundedNotFoundException.class)
    @Override
    public CourseFounded update(CourseFounded updated) throws CourseFoundedNotFoundException {

        CourseFounded courseFounded = courseFoundedRepository.findOne(updated.getId());

        if (courseFounded == null) {
            throw new CourseFoundedNotFoundException();
        }

        BeanUtils.copyProperties(updated, courseFounded);
        return courseFoundedRepository.save(courseFounded);
    }

    @Transactional(rollbackFor = CourseFoundedNotFoundException.class)
    @Override
    public CourseFounded copy(final CourseFounded copied) {

        CourseFounded courseFounded = new CourseFounded();
        BeanUtils.copyProperties(copied, courseFounded);
        courseFounded.setId(null);

        return courseFoundedRepository.save(courseFounded);
    }

    @Transactional
    @Override
    public Iterable<CourseFounded> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<CourseFounded> search(_SearchDTO pageable) {
        return findAll();
    }
}