package eims.model.hr;

import eims.model.common.Person;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMPLOYEE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee extends Person {


    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "JOINING_DATE", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Past
    private LocalDate joiningDate;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "Basic", nullable = false)
    private BigDecimal basic;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "House_rent", nullable = false)
    private BigDecimal houseRent;



    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getBasic() {
        return basic;
    }

    public void setBasic(BigDecimal basic) {
        this.basic = basic;
    }

    public BigDecimal getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(BigDecimal houseRent) {
        this.houseRent = houseRent;
    }
    
    
    

}
