package eims.service;

import eims.model.acad.Registration;
import eims.exception.RegistrationNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface RegistrationService {



    public Registration findById(BigInteger id);
    
    public Registration create(Registration registration);
    
    public Registration update(Registration registration) throws RegistrationNotFoundException;
    
    public Registration copy(Registration registration);
    
    public Registration delete(BigInteger id) throws RegistrationNotFoundException;
   
    public Iterable<Registration> search(_SearchDTO pageable);
    
    public Iterable<Registration> findAll(_SearchDTO pageable);
    
    public Iterable<Registration> findAll();
}