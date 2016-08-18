package eims.repositories;

import eims.model.acad.ProcOutAttendance;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcOutAttendanceRepository extends JpaRepository<ProcOutAttendance, BigInteger> {

}
