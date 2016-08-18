package eims.repositories;

import eims.model.security.AuthMenu;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthMenuRepository extends JpaRepository<AuthMenu, BigInteger> {

}
