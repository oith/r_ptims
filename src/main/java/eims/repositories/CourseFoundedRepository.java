package eims.repositories;

import eims.model.acad.CourseFounded;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseFoundedRepository extends JpaRepository<CourseFounded, BigInteger> {

}
