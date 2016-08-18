package eims.service;

import eims.model.com.Lookup;
import eims.exception.LookupNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface LookupService {



    public Lookup findById(BigInteger id);
    
    public Lookup create(Lookup lookup);
    
    public Lookup update(Lookup lookup) throws LookupNotFoundException;
    
    public Lookup copy(Lookup lookup);
    
    public Lookup delete(BigInteger id) throws LookupNotFoundException;
   
    public Iterable<Lookup> search(_SearchDTO pageable);
    
    public Iterable<Lookup> findAll(_SearchDTO pageable);
    
    public Iterable<Lookup> findAll();
}