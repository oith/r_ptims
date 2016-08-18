package eims.service;

import eims.model.acad.Registration;
import eims.dto._SearchDTO;
import eims.exception.RegistrationNotFoundException;
import eims.repositories.RegistrationRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("registrationService")
@Transactional(readOnly = true)
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;



    @Transactional
    @Override
    public Registration create(Registration lookup) {
        return registrationRepository.save(lookup);
    }

    @Override
    @Transactional
    public Registration findById(BigInteger id) {
        Registration registration=registrationRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return registration;
    }

    @Override
    @Transactional(rollbackFor = RegistrationNotFoundException.class)
    public Registration delete(BigInteger id) throws RegistrationNotFoundException {

        Registration registration = registrationRepository.findOne(id);

        if (registration == null) {
            throw new RegistrationNotFoundException();
        }
        registrationRepository.delete(id);
        return registration;
    }

    @Override
    @Transactional
    public Iterable<Registration> findAll() {
        Iterable<Registration> registrations=registrationRepository.findAll();
        
        for (Registration registration : registrations) {

        //Hibernate.initialize(registration.getA());
        //Hibernate.initialize(registration.getZs());
        }
        
        return registrations;
    }

    @Transactional(rollbackFor = RegistrationNotFoundException.class)
    @Override
    public Registration update(Registration updated) throws RegistrationNotFoundException {

        Registration registration = registrationRepository.findOne(updated.getId());

        if (registration == null) {
            throw new RegistrationNotFoundException();
        }

        BeanUtils.copyProperties(updated, registration);
        return registrationRepository.save(registration);
    }

    @Transactional(rollbackFor = RegistrationNotFoundException.class)
    @Override
    public Registration copy(final Registration copied) {

        Registration registration = new Registration();
        BeanUtils.copyProperties(copied, registration);
        registration.setId(null);

        return registrationRepository.save(registration);
    }

    @Transactional
    @Override
    public Iterable<Registration> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Registration> search(_SearchDTO pageable) {
        return findAll();
    }
}