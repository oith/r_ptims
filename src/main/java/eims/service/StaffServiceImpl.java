package eims.service;

import eims.model.acad.Staff;
import eims.dto._SearchDTO;
import eims.exception.StaffNotFoundException;
import eims.repositories.StaffRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("staffService")
@Transactional(readOnly = true)
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Transactional(readOnly = true)
    @Override
    public Staff findByCode(String code) {
        return staffRepository.findByCode(code);
    }

    @Transactional
    @Override
    public Staff create(Staff lookup) {
        return staffRepository.save(lookup);
    }

    @Override
    @Transactional
    public Staff findById(BigInteger id) {
        Staff staff=staffRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return staff;
    }

    @Override
    @Transactional(rollbackFor = StaffNotFoundException.class)
    public Staff delete(BigInteger id) throws StaffNotFoundException {

        Staff staff = staffRepository.findOne(id);

        if (staff == null) {
            throw new StaffNotFoundException();
        }
        staffRepository.delete(id);
        return staff;
    }

    @Override
    @Transactional
    public Iterable<Staff> findAll() {
        Iterable<Staff> staffs=staffRepository.findAll();
        
        for (Staff staff : staffs) {

        //Hibernate.initialize(staff.getA());
        //Hibernate.initialize(staff.getZs());
        }
        
        return staffs;
    }

    @Transactional(rollbackFor = StaffNotFoundException.class)
    @Override
    public Staff update(Staff updated) throws StaffNotFoundException {

        Staff staff = staffRepository.findOne(updated.getId());

        if (staff == null) {
            throw new StaffNotFoundException();
        }

        BeanUtils.copyProperties(updated, staff);
        return staffRepository.save(staff);
    }

    @Transactional(rollbackFor = StaffNotFoundException.class)
    @Override
    public Staff copy(final Staff copied) {

        Staff staff = new Staff();
        BeanUtils.copyProperties(copied, staff);
        staff.setId(null);

        return staffRepository.save(staff);
    }

    @Transactional
    @Override
    public Iterable<Staff> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Staff> search(_SearchDTO pageable) {
        return findAll();
    }
}