package eims.service;

import eims.model.acad.Institute;
import eims.dto._SearchDTO;
import eims.exception.InstituteNotFoundException;
import eims.repositories.InstituteRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("instituteService")
@Transactional(readOnly = true)
public class InstituteServiceImpl implements InstituteService {

    @Autowired
    private InstituteRepository instituteRepository;



    @Transactional
    @Override
    public Institute create(Institute lookup) {
        return instituteRepository.save(lookup);
    }

    @Override
    @Transactional
    public Institute findById(BigInteger id) {
        Institute institute=instituteRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return institute;
    }

    @Override
    @Transactional(rollbackFor = InstituteNotFoundException.class)
    public Institute delete(BigInteger id) throws InstituteNotFoundException {

        Institute institute = instituteRepository.findOne(id);

        if (institute == null) {
            throw new InstituteNotFoundException();
        }
        instituteRepository.delete(id);
        return institute;
    }

    @Override
    @Transactional
    public Iterable<Institute> findAll() {
        Iterable<Institute> institutes=instituteRepository.findAll();
        
        for (Institute institute : institutes) {

        //Hibernate.initialize(institute.getA());
        //Hibernate.initialize(institute.getZs());
        }
        
        return institutes;
    }

    @Transactional(rollbackFor = InstituteNotFoundException.class)
    @Override
    public Institute update(Institute updated) throws InstituteNotFoundException {

        Institute institute = instituteRepository.findOne(updated.getId());

        if (institute == null) {
            throw new InstituteNotFoundException();
        }

        BeanUtils.copyProperties(updated, institute);
        return instituteRepository.save(institute);
    }

    @Transactional(rollbackFor = InstituteNotFoundException.class)
    @Override
    public Institute copy(final Institute copied) {

        Institute institute = new Institute();
        BeanUtils.copyProperties(copied, institute);
        institute.setId(null);

        return instituteRepository.save(institute);
    }

    @Transactional
    @Override
    public Iterable<Institute> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Institute> search(_SearchDTO pageable) {
        return findAll();
    }
}