package eims.model.hr;

import eims.model.common.Person;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMPLOYEE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee extends Person {

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "JOINING_DATE", nullable = false)
    @Past
    private Date joiningDate;

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

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

}
