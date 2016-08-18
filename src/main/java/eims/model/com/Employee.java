package eims.model.com;

import com.oith.annotation.MacCodable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement
@MacCodable(id = "id", code = "code", caption = "fullName")
public abstract class Employee extends Person {

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "JOINING_DATE", nullable = false)
    @Past
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date joiningDate;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
