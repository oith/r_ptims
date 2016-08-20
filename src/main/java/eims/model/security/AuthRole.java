package eims.model.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import eims.model.com.AbstractEntity;

@Entity
@Table(name = "AUTH_ROLE")
@XmlRootElement
public class AuthRole extends AbstractEntity{

   
    @Column(name = "authority", length = 128, unique = true)
    private String authority;


    public AuthRole() {
    }

    public AuthRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return authority;
    }
}
