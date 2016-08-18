package eims.service;

import eims.model.com.AdmReportDetail;
import eims.exception.AdmReportDetailNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface AdmReportDetailService {



    public AdmReportDetail findById(BigInteger id);
    
    public AdmReportDetail create(AdmReportDetail admReportDetail);
    
    public AdmReportDetail update(AdmReportDetail admReportDetail) throws AdmReportDetailNotFoundException;
    
    public AdmReportDetail copy(AdmReportDetail admReportDetail);
    
    public AdmReportDetail delete(BigInteger id) throws AdmReportDetailNotFoundException;
   
    public Iterable<AdmReportDetail> search(_SearchDTO pageable);
    
    public Iterable<AdmReportDetail> findAll(_SearchDTO pageable);
    
    public Iterable<AdmReportDetail> findAll();
}