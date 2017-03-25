package eims.service;

import eims.model.acad.ProcOutCourseSchedule;
import eims.dto._SearchDTO;
import eims.exception.ProcOutCourseScheduleNotFoundException;
import eims.repositories.ProcOutCourseScheduleRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("procOutCourseScheduleService")
@Transactional(readOnly = true)
public class ProcOutCourseScheduleServiceImpl implements ProcOutCourseScheduleService {

    @Autowired
    private ProcOutCourseScheduleRepository procOutCourseScheduleRepository;



    @Transactional
    @Override
    public ProcOutCourseSchedule create(ProcOutCourseSchedule lookup) {
        return procOutCourseScheduleRepository.save(lookup);
    }

    @Override
    @Transactional
    public ProcOutCourseSchedule findById(BigInteger id) {
        ProcOutCourseSchedule procOutCourseSchedule=procOutCourseScheduleRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return procOutCourseSchedule;
    }

    @Override
    @Transactional(rollbackFor = ProcOutCourseScheduleNotFoundException.class)
    public ProcOutCourseSchedule delete(BigInteger id) throws ProcOutCourseScheduleNotFoundException {

        ProcOutCourseSchedule procOutCourseSchedule = procOutCourseScheduleRepository.findOne(id);

        if (procOutCourseSchedule == null) {
            throw new ProcOutCourseScheduleNotFoundException();
        }
        procOutCourseScheduleRepository.delete(id);
        return procOutCourseSchedule;
    }

    @Override
    @Transactional
    public Iterable<ProcOutCourseSchedule> findAll() {
        Iterable<ProcOutCourseSchedule> procOutCourseSchedules=procOutCourseScheduleRepository.findAll();
        
        for (ProcOutCourseSchedule procOutCourseSchedule : procOutCourseSchedules) {

        //Hibernate.initialize(procOutCourseSchedule.getA());
        //Hibernate.initialize(procOutCourseSchedule.getZs());
        }
        
        return procOutCourseSchedules;
    }

    @Transactional(rollbackFor = ProcOutCourseScheduleNotFoundException.class)
    @Override
    public ProcOutCourseSchedule update(ProcOutCourseSchedule updated) throws ProcOutCourseScheduleNotFoundException {

        ProcOutCourseSchedule procOutCourseSchedule = procOutCourseScheduleRepository.findOne(updated.getId());

        if (procOutCourseSchedule == null) {
            throw new ProcOutCourseScheduleNotFoundException();
        }

        BeanUtils.copyProperties(updated, procOutCourseSchedule);
        return procOutCourseScheduleRepository.save(procOutCourseSchedule);
    }

    @Transactional(rollbackFor = ProcOutCourseScheduleNotFoundException.class)
    @Override
    public ProcOutCourseSchedule copy(final ProcOutCourseSchedule copied) {

        ProcOutCourseSchedule procOutCourseSchedule = new ProcOutCourseSchedule();
        BeanUtils.copyProperties(copied, procOutCourseSchedule);
        procOutCourseSchedule.setId(null);

        return procOutCourseScheduleRepository.save(procOutCourseSchedule);
    }

    @Transactional
    @Override
    public Iterable<ProcOutCourseSchedule> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ProcOutCourseSchedule> search(_SearchDTO pageable) {
        return findAll();
    }
}