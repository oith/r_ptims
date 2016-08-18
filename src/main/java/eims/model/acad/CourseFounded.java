package eims.model.acad;

import eims.model.com.AbstractEntity;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "COURSE_FOUNDED")
@XmlRootElement
public class CourseFounded extends AbstractEntity {

    @JoinColumn(name = "COURSE_ID", nullable = false)
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "INSTRUCTOR_ID")
    @ManyToOne
    private Instructor instructor;

    @OneToMany
    @JoinTable(name = "COURSE_FOUNDED_ROOM_LK",
            joinColumns = @JoinColumn(name = "COURSE_FOUNDED_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROOM_ID"))
    @OrderBy(value = "code ASC")
    private Set<Room> rooms;

    @Column(name = "TOTAL_COURSE_HOUR")
    @Range(min = 1, max = 120)
    private Integer totalCourseHour;
    @Column(name = "TOTAL_NO_OF_CLASS")
    @Range(min = 1, max = 120)
    private Integer totalNoOfClass;
    @Column(name = "NO_OF_CLASS_IN_WEEK")
    @Range(min = 1, max = 7)
    private Integer noOfClassInWeek;
    @Temporal(TemporalType.DATE)
    @Column(name = "ORIENTATION_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date orientationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "COURSE_FOUNDED_STATUS", length = 30)
    private CourseFoundedStatus courseFoundedStatus;

    public CourseFounded() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public CourseFoundedStatus getCourseFoundedStatus() {
        return courseFoundedStatus;
    }

    public void setCourseFoundedStatus(CourseFoundedStatus courseFoundedStatus) {
        this.courseFoundedStatus = courseFoundedStatus;
    }

    public Integer getTotalNoOfClass() {
        return totalNoOfClass;
    }

    public void setTotalNoOfClass(Integer totalNoOfClass) {
        this.totalNoOfClass = totalNoOfClass;
    }

    public Integer getNoOfClassInWeek() {
        return noOfClassInWeek;
    }

    public void setNoOfClassInWeek(Integer noOfClassInWeek) {
        this.noOfClassInWeek = noOfClassInWeek;
    }

    public Date getOrientationDate() {
        return orientationDate;
    }

    public void setOrientationDate(Date orientationDate) {
        this.orientationDate = orientationDate;
    }

    public Integer getTotalCourseHour() {
        return totalCourseHour;
    }

    public void setTotalCourseHour(Integer totalCourseHour) {
        this.totalCourseHour = totalCourseHour;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

}
