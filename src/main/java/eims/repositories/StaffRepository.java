package eims.repositories;

import eims.model.acad.Staff;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, BigInteger> {
    public Staff findByCode(String code);
}
