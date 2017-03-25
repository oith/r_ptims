package eims.service;

import eims.model.acad.Institute;
import eims.exception.InstituteNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface InstituteService {



    public Institute findById(BigInteger id);
    
    public Institute create(Institute institute);
    
    public Institute update(Institute institute) throws InstituteNotFoundException;
    
    public Institute copy(Institute institute);
    
    public Institute delete(BigInteger id) throws InstituteNotFoundException;
   
    public Iterable<Institute> search(_SearchDTO pageable);
    
    public Iterable<Institute> findAll(_SearchDTO pageable);
    
    public Iterable<Institute> findAll();
}