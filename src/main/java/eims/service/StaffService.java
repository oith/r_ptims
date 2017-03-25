package eims.service;

import eims.model.acad.Staff;
import eims.exception.StaffNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface StaffService {

    public Staff findByCode(String code);

    public Staff findById(BigInteger id);
    
    public Staff create(Staff staff);
    
    public Staff update(Staff staff) throws StaffNotFoundException;
    
    public Staff copy(Staff staff);
    
    public Staff delete(BigInteger id) throws StaffNotFoundException;
   
    public Iterable<Staff> search(_SearchDTO pageable);
    
    public Iterable<Staff> findAll(_SearchDTO pageable);
    
    public Iterable<Staff> findAll();
}