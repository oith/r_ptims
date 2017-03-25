package eims.service;

import eims.model.security.AuthQuestion;
import eims.exception.AuthQuestionNotFoundException;
import eims.dto._SearchDTO;
import java.math.BigInteger;

public interface AuthQuestionService {



    public AuthQuestion findById(BigInteger id);
    
    public AuthQuestion create(AuthQuestion authQuestion);
    
    public AuthQuestion update(AuthQuestion authQuestion) throws AuthQuestionNotFoundException;
    
    public AuthQuestion copy(AuthQuestion authQuestion);
    
    public AuthQuestion delete(BigInteger id) throws AuthQuestionNotFoundException;
   
    public Iterable<AuthQuestion> search(_SearchDTO pageable);
    
    public Iterable<AuthQuestion> findAll(_SearchDTO pageable);
    
    public Iterable<AuthQuestion> findAll();
}