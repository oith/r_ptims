package eims.service;

import eims.model.com.AdmReportDetail;
import eims.dto._SearchDTO;
import eims.exception.AdmReportDetailNotFoundException;
import eims.repositories.AdmReportDetailRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("admReportDetailService")
@Transactional(readOnly = true)
public class AdmReportDetailServiceImpl implements AdmReportDetailService {

    @Autowired
    private AdmReportDetailRepository admReportDetailRepository;



    @Transactional
    @Override
    public AdmReportDetail create(AdmReportDetail lookup) {
        return admReportDetailRepository.save(lookup);
    }

    @Override
    @Transactional
    public AdmReportDetail findById(BigInteger id) {
        AdmReportDetail admReportDetail=admReportDetailRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return admReportDetail;
    }

    @Override
    @Transactional(rollbackFor = AdmReportDetailNotFoundException.class)
    public AdmReportDetail delete(BigInteger id) throws AdmReportDetailNotFoundException {

        AdmReportDetail admReportDetail = admReportDetailRepository.findOne(id);

        if (admReportDetail == null) {
            throw new AdmReportDetailNotFoundException();
        }
        admReportDetailRepository.delete(id);
        return admReportDetail;
    }

    @Override
    @Transactional
    public Iterable<AdmReportDetail> findAll() {
        Iterable<AdmReportDetail> admReportDetails=admReportDetailRepository.findAll();
        
        for (AdmReportDetail admReportDetail : admReportDetails) {

        //Hibernate.initialize(admReportDetail.getA());
        //Hibernate.initialize(admReportDetail.getZs());
        }
        
        return admReportDetails;
    }

    @Transactional(rollbackFor = AdmReportDetailNotFoundException.class)
    @Override
    public AdmReportDetail update(AdmReportDetail updated) throws AdmReportDetailNotFoundException {

        AdmReportDetail admReportDetail = admReportDetailRepository.findOne(updated.getId());

        if (admReportDetail == null) {
            throw new AdmReportDetailNotFoundException();
        }

        BeanUtils.copyProperties(updated, admReportDetail);
        return admReportDetailRepository.save(admReportDetail);
    }

    @Transactional(rollbackFor = AdmReportDetailNotFoundException.class)
    @Override
    public AdmReportDetail copy(final AdmReportDetail copied) {

        AdmReportDetail admReportDetail = new AdmReportDetail();
        BeanUtils.copyProperties(copied, admReportDetail);
        admReportDetail.setId(null);

        return admReportDetailRepository.save(admReportDetail);
    }

    @Transactional
    @Override
    public Iterable<AdmReportDetail> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmReportDetail> search(_SearchDTO pageable) {
        return findAll();
    }
}