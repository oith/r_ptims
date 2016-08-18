package eims.repositories;

import eims.model.com.AdmProcessDetail;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmProcessDetailRepository extends JpaRepository<AdmProcessDetail, BigInteger> {

}
