package eims.service;

import eims.model.acad.ProcOutAttendance;
import eims.exception.ProcOutAttendanceNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface ProcOutAttendanceService {



    public ProcOutAttendance findById(BigInteger id);
    
    public ProcOutAttendance create(ProcOutAttendance procOutAttendance);
    
    public ProcOutAttendance update(ProcOutAttendance procOutAttendance) throws ProcOutAttendanceNotFoundException;
    
    public ProcOutAttendance copy(ProcOutAttendance procOutAttendance);
    
    public ProcOutAttendance delete(BigInteger id) throws ProcOutAttendanceNotFoundException;
   
    public Iterable<ProcOutAttendance> search(_SearchDTO pageable);
    
    public Iterable<ProcOutAttendance> findAll(_SearchDTO pageable);
    
    public Iterable<ProcOutAttendance> findAll();
}