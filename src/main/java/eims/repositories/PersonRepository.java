package eims.repositories;

import eims.model.com.Person;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, BigInteger> {

}
