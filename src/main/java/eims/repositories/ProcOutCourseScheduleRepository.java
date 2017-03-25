package eims.repositories;

import eims.model.acad.ProcOutCourseSchedule;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcOutCourseScheduleRepository extends JpaRepository<ProcOutCourseSchedule, BigInteger> {

}
