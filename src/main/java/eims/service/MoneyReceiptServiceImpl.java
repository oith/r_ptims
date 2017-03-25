package eims.service;

import eims.model.acad.MoneyReceipt;
import eims.dto._SearchDTO;
import eims.exception.MoneyReceiptNotFoundException;
import eims.repositories.MoneyReceiptRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("moneyReceiptService")
@Transactional(readOnly = true)
public class MoneyReceiptServiceImpl implements MoneyReceiptService {

    @Autowired
    private MoneyReceiptRepository moneyReceiptRepository;



    @Transactional
    @Override
    public MoneyReceipt create(MoneyReceipt lookup) {
        return moneyReceiptRepository.save(lookup);
    }

    @Override
    @Transactional
    public MoneyReceipt findById(BigInteger id) {
        MoneyReceipt moneyReceipt=moneyReceiptRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return moneyReceipt;
    }

    @Override
    @Transactional(rollbackFor = MoneyReceiptNotFoundException.class)
    public MoneyReceipt delete(BigInteger id) throws MoneyReceiptNotFoundException {

        MoneyReceipt moneyReceipt = moneyReceiptRepository.findOne(id);

        if (moneyReceipt == null) {
            throw new MoneyReceiptNotFoundException();
        }
        moneyReceiptRepository.delete(id);
        return moneyReceipt;
    }

    @Override
    @Transactional
    public Iterable<MoneyReceipt> findAll() {
        Iterable<MoneyReceipt> moneyReceipts=moneyReceiptRepository.findAll();
        
        for (MoneyReceipt moneyReceipt : moneyReceipts) {

        //Hibernate.initialize(moneyReceipt.getA());
        //Hibernate.initialize(moneyReceipt.getZs());
        }
        
        return moneyReceipts;
    }

    @Transactional(rollbackFor = MoneyReceiptNotFoundException.class)
    @Override
    public MoneyReceipt update(MoneyReceipt updated) throws MoneyReceiptNotFoundException {

        MoneyReceipt moneyReceipt = moneyReceiptRepository.findOne(updated.getId());

        if (moneyReceipt == null) {
            throw new MoneyReceiptNotFoundException();
        }

        BeanUtils.copyProperties(updated, moneyReceipt);
        return moneyReceiptRepository.save(moneyReceipt);
    }

    @Transactional(rollbackFor = MoneyReceiptNotFoundException.class)
    @Override
    public MoneyReceipt copy(final MoneyReceipt copied) {

        MoneyReceipt moneyReceipt = new MoneyReceipt();
        BeanUtils.copyProperties(copied, moneyReceipt);
        moneyReceipt.setId(null);

        return moneyReceiptRepository.save(moneyReceipt);
    }

    @Transactional
    @Override
    public Iterable<MoneyReceipt> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<MoneyReceipt> search(_SearchDTO pageable) {
        return findAll();
    }
}