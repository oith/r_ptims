package eims.repositories;

import eims.model.acad.MoneyReceipt;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyReceiptRepository extends JpaRepository<MoneyReceipt, BigInteger> {

}
