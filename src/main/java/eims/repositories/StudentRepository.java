package eims.repositories;

import eims.model.acad.Student;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, BigInteger> {

}
