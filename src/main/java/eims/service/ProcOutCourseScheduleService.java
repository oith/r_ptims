package eims.service;

import eims.model.acad.ProcOutCourseSchedule;
import eims.exception.ProcOutCourseScheduleNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface ProcOutCourseScheduleService {



    public ProcOutCourseSchedule findById(BigInteger id);
    
    public ProcOutCourseSchedule create(ProcOutCourseSchedule procOutCourseSchedule);
    
    public ProcOutCourseSchedule update(ProcOutCourseSchedule procOutCourseSchedule) throws ProcOutCourseScheduleNotFoundException;
    
    public ProcOutCourseSchedule copy(ProcOutCourseSchedule procOutCourseSchedule);
    
    public ProcOutCourseSchedule delete(BigInteger id) throws ProcOutCourseScheduleNotFoundException;
   
    public Iterable<ProcOutCourseSchedule> search(_SearchDTO pageable);
    
    public Iterable<ProcOutCourseSchedule> findAll(_SearchDTO pageable);
    
    public Iterable<ProcOutCourseSchedule> findAll();
}