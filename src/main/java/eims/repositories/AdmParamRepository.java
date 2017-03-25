package eims.repositories;

import eims.model.com.AdmParam;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmParamRepository extends JpaRepository<AdmParam, BigInteger> {

}
