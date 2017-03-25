package eims.model.acad;

import eims.model.com.AbstractEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "MONEY_RECEIPT")
@XmlRootElement
public class MoneyReceipt extends AbstractEntity {

    @Basic(optional = false)
    @Column(name = "CODE", unique = true, length = 20, nullable = false)
    private String code;
    @JoinColumn(name = "REGISTRATION_ID", nullable = true)
    @ManyToOne(optional = true)
    private Registration registration;
    @Temporal(TemporalType.DATE)
    @Column(name = "PAYMENT_DATE", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;
    @Digits(integer = 8, fraction = 2)
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public MoneyReceipt() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

}
