package eims.repositories;

import eims.model.com.AdmReport;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmReportRepository extends JpaRepository<AdmReport, BigInteger> {
    public AdmReport findByCode(String code);
}
