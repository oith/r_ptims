package eims.repositories;

import eims.model.security.AuthRequestMap;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRequestMapRepository extends JpaRepository<AuthRequestMap, BigInteger> {

}
