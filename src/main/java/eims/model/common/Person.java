package eims.model.common;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class Person extends AbstractEntity {

    @NotEmpty
    @Column(name = "CODE", length = 20, unique = true, nullable = false)
    private String code;
    @Size(min = 3, max = 50)
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;
    @Column(name = "FATHER_NAME", length = 100)
    private String fatherName;
    @Column(name = "MOTHER_NAME", length = 100)
    private String motherName;
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 6)
    private Gender gender;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DOB", nullable = false)
    @Past
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    @Column(name = "FB_ID", length = 30, unique = true)
    private String fbId;
    @Embedded
    private Address address;

  

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
