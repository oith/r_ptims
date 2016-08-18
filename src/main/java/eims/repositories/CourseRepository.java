package eims.repositories;

import eims.model.acad.Course;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, BigInteger> {

}
