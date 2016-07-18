package eims.model.acad;

import eims.model.common.AbstractEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "REGISTRATION")
@XmlRootElement
public class Registration extends AbstractEntity {

    @Basic(optional = false)
    @Column(name = "CODE", unique = true, length = 20, nullable = false)
    private String code;

    @JoinColumn(name = "COURSE_FOUNDED_ID", nullable = false)
    @ManyToOne(optional = false)
    private CourseFounded courseFounded;

    @JoinColumn(name = "STUDENT_ID", nullable = false)
    @ManyToOne(optional = false)
    private Student student;

    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTRATION_DATE", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registrationDate;

    @Column(name = "COURSE_PRICE")
    @Range(min = 0, max = 100000)
    private Double coursePrice;

    public Registration() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseFounded getCourseFounded() {
        return courseFounded;
    }

    public void setCourseFounded(CourseFounded courseFounded) {
        this.courseFounded = courseFounded;
    }

    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

}
