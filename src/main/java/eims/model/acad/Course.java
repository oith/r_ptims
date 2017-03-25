package eims.model.acad;

import eims.model.com.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "COURSE")
@XmlRootElement
public class Course extends AbstractEntity {

    @Column(name = "CODE", unique = true, length = 20, nullable = false)
    private String code;
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String fullName;
    @Column(name = "TOTAL_COURSE_HOUR")
    @Range(min = 1, max = 120)
    private Integer totalCourseHour;
    @Column(name = "COURSE_OUTLINE", length = 500)
    private String courseOutline;

    public Course() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getTotalCourseHour() {
        return totalCourseHour;
    }

    public void setTotalCourseHour(Integer totalCourseHour) {
        this.totalCourseHour = totalCourseHour;
    }

    public String getCourseOutline() {
        return courseOutline;
    }

    public void setCourseOutline(String courseOutline) {
        this.courseOutline = courseOutline;
    }

    @Override
    public String toString() {
        //return ToStringBuilder.reflectionToString(this);
        return code + "-" + fullName;
    }
}
