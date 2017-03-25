package eims.service;

import eims.model.acad.ProcOutAttendance;
import eims.dto._SearchDTO;
import eims.exception.ProcOutAttendanceNotFoundException;
import eims.repositories.ProcOutAttendanceRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("procOutAttendanceService")
@Transactional(readOnly = true)
public class ProcOutAttendanceServiceImpl implements ProcOutAttendanceService {

    @Autowired
    private ProcOutAttendanceRepository procOutAttendanceRepository;



    @Transactional
    @Override
    public ProcOutAttendance create(ProcOutAttendance lookup) {
        return procOutAttendanceRepository.save(lookup);
    }

    @Override
    @Transactional
    public ProcOutAttendance findById(BigInteger id) {
        ProcOutAttendance procOutAttendance=procOutAttendanceRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return procOutAttendance;
    }

    @Override
    @Transactional(rollbackFor = ProcOutAttendanceNotFoundException.class)
    public ProcOutAttendance delete(BigInteger id) throws ProcOutAttendanceNotFoundException {

        ProcOutAttendance procOutAttendance = procOutAttendanceRepository.findOne(id);

        if (procOutAttendance == null) {
            throw new ProcOutAttendanceNotFoundException();
        }
        procOutAttendanceRepository.delete(id);
        return procOutAttendance;
    }

    @Override
    @Transactional
    public Iterable<ProcOutAttendance> findAll() {
        Iterable<ProcOutAttendance> procOutAttendances=procOutAttendanceRepository.findAll();
        
        for (ProcOutAttendance procOutAttendance : procOutAttendances) {

        //Hibernate.initialize(procOutAttendance.getA());
        //Hibernate.initialize(procOutAttendance.getZs());
        }
        
        return procOutAttendances;
    }

    @Transactional(rollbackFor = ProcOutAttendanceNotFoundException.class)
    @Override
    public ProcOutAttendance update(ProcOutAttendance updated) throws ProcOutAttendanceNotFoundException {

        ProcOutAttendance procOutAttendance = procOutAttendanceRepository.findOne(updated.getId());

        if (procOutAttendance == null) {
            throw new ProcOutAttendanceNotFoundException();
        }

        BeanUtils.copyProperties(updated, procOutAttendance);
        return procOutAttendanceRepository.save(procOutAttendance);
    }

    @Transactional(rollbackFor = ProcOutAttendanceNotFoundException.class)
    @Override
    public ProcOutAttendance copy(final ProcOutAttendance copied) {

        ProcOutAttendance procOutAttendance = new ProcOutAttendance();
        BeanUtils.copyProperties(copied, procOutAttendance);
        procOutAttendance.setId(null);

        return procOutAttendanceRepository.save(procOutAttendance);
    }

    @Transactional
    @Override
    public Iterable<ProcOutAttendance> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ProcOutAttendance> search(_SearchDTO pageable) {
        return findAll();
    }
}