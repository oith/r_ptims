package eims.repositories;

import eims.model.acad.Registration;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, BigInteger> {

}
