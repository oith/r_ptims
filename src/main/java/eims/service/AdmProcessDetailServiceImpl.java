package eims.service;

import eims.model.com.AdmProcessDetail;
import eims.dto._SearchDTO;
import eims.exception.AdmProcessDetailNotFoundException;
import eims.repositories.AdmProcessDetailRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("admProcessDetailService")
@Transactional(readOnly = true)
public class AdmProcessDetailServiceImpl implements AdmProcessDetailService {

    @Autowired
    private AdmProcessDetailRepository admProcessDetailRepository;



    @Transactional
    @Override
    public AdmProcessDetail create(AdmProcessDetail lookup) {
        return admProcessDetailRepository.save(lookup);
    }

    @Override
    @Transactional
    public AdmProcessDetail findById(BigInteger id) {
        AdmProcessDetail admProcessDetail=admProcessDetailRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return admProcessDetail;
    }

    @Override
    @Transactional(rollbackFor = AdmProcessDetailNotFoundException.class)
    public AdmProcessDetail delete(BigInteger id) throws AdmProcessDetailNotFoundException {

        AdmProcessDetail admProcessDetail = admProcessDetailRepository.findOne(id);

        if (admProcessDetail == null) {
            throw new AdmProcessDetailNotFoundException();
        }
        admProcessDetailRepository.delete(id);
        return admProcessDetail;
    }

    @Override
    @Transactional
    public Iterable<AdmProcessDetail> findAll() {
        Iterable<AdmProcessDetail> admProcessDetails=admProcessDetailRepository.findAll();
        
        for (AdmProcessDetail admProcessDetail : admProcessDetails) {

        //Hibernate.initialize(admProcessDetail.getA());
        //Hibernate.initialize(admProcessDetail.getZs());
        }
        
        return admProcessDetails;
    }

    @Transactional(rollbackFor = AdmProcessDetailNotFoundException.class)
    @Override
    public AdmProcessDetail update(AdmProcessDetail updated) throws AdmProcessDetailNotFoundException {

        AdmProcessDetail admProcessDetail = admProcessDetailRepository.findOne(updated.getId());

        if (admProcessDetail == null) {
            throw new AdmProcessDetailNotFoundException();
        }

        BeanUtils.copyProperties(updated, admProcessDetail);
        return admProcessDetailRepository.save(admProcessDetail);
    }

    @Transactional(rollbackFor = AdmProcessDetailNotFoundException.class)
    @Override
    public AdmProcessDetail copy(final AdmProcessDetail copied) {

        AdmProcessDetail admProcessDetail = new AdmProcessDetail();
        BeanUtils.copyProperties(copied, admProcessDetail);
        admProcessDetail.setId(null);

        return admProcessDetailRepository.save(admProcessDetail);
    }

    @Transactional
    @Override
    public Iterable<AdmProcessDetail> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AdmProcessDetail> search(_SearchDTO pageable) {
        return findAll();
    }
}