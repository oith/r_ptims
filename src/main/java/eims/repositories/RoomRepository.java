package eims.repositories;

import eims.model.acad.Room;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, BigInteger> {

}
