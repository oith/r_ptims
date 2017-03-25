package eims.model.com;

import com.oith.annotation.MacImagable;
import com.oith.annotation.MacSearchable;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PERSON")
@XmlRootElement
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends AbstractEntity {

    @MacSearchable
    @Column(name = "CODE", unique = true, length = 20, nullable = false)
    private String code;
    @MacImagable
    @Column(name = "PIC_FILE", length = 255)
    private String picFile;
    @MacSearchable
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 100)
   
    private String fullName;
    @MacSearchable
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 6)
    private Gender gender;
    @MacSearchable
    @Temporal(TemporalType.DATE)
    @Past
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dob;
    @Column(name = "EMAIL", length = 30)
    @Email
    private String email;

    @Embedded
    //@NaturalId
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "PERMANENT_CITY")),
        @AttributeOverride(name = "lineTwo", column = @Column(name = "PERMANENT_LINE_TWO")),
        @AttributeOverride(name = "street", column = @Column(name = "PERMANENT_STREET")),
        @AttributeOverride(name = "zip", column = @Column(name = "PERMANENT_ZIP")),
        @AttributeOverride(name = "lineOne", column = @Column(name = "PERMANENT_LINE_ONE"))
    })
    private Address addressPermanent;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "PRESENT_CITY")),
        @AttributeOverride(name = "lineTwo", column = @Column(name = "PRESENT_LINE_TWO")),
        @AttributeOverride(name = "street", column = @Column(name = "PRESENT_STREET")),
        @AttributeOverride(name = "zip", column = @Column(name = "PRESENT_ZIP")),
        @AttributeOverride(name = "lineOne", column = @Column(name = "PRESENT_LINE_ONE"))
    })
    private Address addressPresent;

    public Person() {
    }

    public String getPicFile() {
        return picFile;
    }

    public void setPicFile(String picFile) {
        this.picFile = picFile;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        return code + "-" + fullName;
    }

    public Address getAddressPermanent() {
        return addressPermanent;
    }

    public void setAddressPermanent(Address addressPermanent) {
        this.addressPermanent = addressPermanent;
    }

    public Address getAddressPresent() {
        return addressPresent;
    }

    public void setAddressPresent(Address addressPresent) {
        this.addressPresent = addressPresent;
    }
}
