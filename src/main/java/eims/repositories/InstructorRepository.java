package eims.repositories;

import eims.model.acad.Instructor;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, BigInteger> {
    public Instructor findByCode(String code);
}
