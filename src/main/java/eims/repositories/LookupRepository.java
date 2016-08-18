package eims.repositories;

import eims.model.com.Lookup;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LookupRepository extends JpaRepository<Lookup, BigInteger> {

}
