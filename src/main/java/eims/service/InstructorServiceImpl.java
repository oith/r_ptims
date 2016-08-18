package eims.service;

import eims.model.acad.Instructor;
import eims.dto._SearchDTO;
import eims.exception.InstructorNotFoundException;
import eims.repositories.InstructorRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("instructorService")
@Transactional(readOnly = true)
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Transactional(readOnly = true)
    @Override
    public Instructor findByCode(String code) {
        return instructorRepository.findByCode(code);
    }

    @Transactional
    @Override
    public Instructor create(Instructor lookup) {
        return instructorRepository.save(lookup);
    }

    @Override
    @Transactional
    public Instructor findById(BigInteger id) {
        Instructor instructor=instructorRepository.findOne(id);
            Hibernate.initialize(instructor.getCourses());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return instructor;
    }

    @Override
    @Transactional(rollbackFor = InstructorNotFoundException.class)
    public Instructor delete(BigInteger id) throws InstructorNotFoundException {

        Instructor instructor = instructorRepository.findOne(id);

        if (instructor == null) {
            throw new InstructorNotFoundException();
        }
        instructorRepository.delete(id);
        return instructor;
    }

    @Override
    @Transactional
    public Iterable<Instructor> findAll() {
        Iterable<Instructor> instructors=instructorRepository.findAll();
        
        for (Instructor instructor : instructors) {
            Hibernate.initialize(instructor.getCourses());

        //Hibernate.initialize(instructor.getA());
        //Hibernate.initialize(instructor.getZs());
        }
        
        return instructors;
    }

    @Transactional(rollbackFor = InstructorNotFoundException.class)
    @Override
    public Instructor update(Instructor updated) throws InstructorNotFoundException {

        Instructor instructor = instructorRepository.findOne(updated.getId());

        if (instructor == null) {
            throw new InstructorNotFoundException();
        }

        BeanUtils.copyProperties(updated, instructor);
        return instructorRepository.save(instructor);
    }

    @Transactional(rollbackFor = InstructorNotFoundException.class)
    @Override
    public Instructor copy(final Instructor copied) {

        Instructor instructor = new Instructor();
        BeanUtils.copyProperties(copied, instructor);
        instructor.setId(null);

        return instructorRepository.save(instructor);
    }

    @Transactional
    @Override
    public Iterable<Instructor> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Instructor> search(_SearchDTO pageable) {
        return findAll();
    }
}