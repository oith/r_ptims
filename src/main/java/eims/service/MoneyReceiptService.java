package eims.service;

import eims.model.acad.MoneyReceipt;
import eims.exception.MoneyReceiptNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface MoneyReceiptService {



    public MoneyReceipt findById(BigInteger id);
    
    public MoneyReceipt create(MoneyReceipt moneyReceipt);
    
    public MoneyReceipt update(MoneyReceipt moneyReceipt) throws MoneyReceiptNotFoundException;
    
    public MoneyReceipt copy(MoneyReceipt moneyReceipt);
    
    public MoneyReceipt delete(BigInteger id) throws MoneyReceiptNotFoundException;
   
    public Iterable<MoneyReceipt> search(_SearchDTO pageable);
    
    public Iterable<MoneyReceipt> findAll(_SearchDTO pageable);
    
    public Iterable<MoneyReceipt> findAll();
}