package eims.repositories;

import eims.model.com.AdmProcess;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmProcessRepository extends JpaRepository<AdmProcess, BigInteger> {
    public AdmProcess findByCode(String code);
}
