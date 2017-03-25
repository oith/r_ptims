package eims.repositories;

import eims.model.com.AdmModule;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmModuleRepository extends JpaRepository<AdmModule, BigInteger> {
    public AdmModule findByCode(String code);
    
    //public List<AdmModule> findByRemarksLike(String remarks);
    //select x from AdmModule x where x.code=:code
    
    //select x from AdmModule x where x.remarks LIKE :code
    
    
    //@Query("select u from User u where u.firstname like %?1")
  //List<AdmModule> findByAbc(String firstname);
  
  
}


