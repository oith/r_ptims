package eims.repositories;

import eims.model.com.AdmReportDetail;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmReportDetailRepository extends JpaRepository<AdmReportDetail, BigInteger> {

}
