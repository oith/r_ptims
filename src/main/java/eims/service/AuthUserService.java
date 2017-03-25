package eims.service;

import eims.model.security.AuthUser;
import eims.dto._SearchDTO;
import eims.exception.AuthUserNotFoundException;
import java.math.BigInteger;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserService extends UserDetailsService {

    public AuthUser findById(BigInteger id);

    public AuthUser findByUsername(String username);

    public AuthUser create(AuthUser emp);

    public AuthUser update(AuthUser emp) throws AuthUserNotFoundException;

    public AuthUser copy(AuthUser authUser);

    public AuthUser delete(BigInteger id) throws AuthUserNotFoundException;

    public Boolean deleteByUsername(String username);

    public Iterable<AuthUser> search(_SearchDTO pageable);

    public Iterable<AuthUser> findAll(_SearchDTO pageable);

    public Iterable<AuthUser> findAll();

    public Boolean isUsernameUnique(BigInteger id, String username);
}
