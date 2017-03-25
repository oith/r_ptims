package eims.service;

import eims.model.com.AdmProcessDetail;
import eims.exception.AdmProcessDetailNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface AdmProcessDetailService {



    public AdmProcessDetail findById(BigInteger id);
    
    public AdmProcessDetail create(AdmProcessDetail admProcessDetail);
    
    public AdmProcessDetail update(AdmProcessDetail admProcessDetail) throws AdmProcessDetailNotFoundException;
    
    public AdmProcessDetail copy(AdmProcessDetail admProcessDetail);
    
    public AdmProcessDetail delete(BigInteger id) throws AdmProcessDetailNotFoundException;
   
    public Iterable<AdmProcessDetail> search(_SearchDTO pageable);
    
    public Iterable<AdmProcessDetail> findAll(_SearchDTO pageable);
    
    public Iterable<AdmProcessDetail> findAll();
}