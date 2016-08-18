package eims.repositories;

import eims.model.acad.Institute;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepository extends JpaRepository<Institute, BigInteger> {

}
