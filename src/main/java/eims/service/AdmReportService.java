package eims.service;

import eims.model.com.AdmReport;
import eims.exception.AdmReportNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface AdmReportService {

    public AdmReport findByCode(String code);

    public AdmReport findById(BigInteger id);
    
    public AdmReport create(AdmReport admReport);
    
    public AdmReport update(AdmReport admReport) throws AdmReportNotFoundException;
    
    public AdmReport copy(AdmReport admReport);
    
    public AdmReport delete(BigInteger id) throws AdmReportNotFoundException;
   
    public Iterable<AdmReport> search(_SearchDTO pageable);
    
    public Iterable<AdmReport> findAll(_SearchDTO pageable);
    
    public Iterable<AdmReport> findAll();
}