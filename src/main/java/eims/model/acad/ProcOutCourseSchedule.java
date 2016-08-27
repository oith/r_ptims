package eims.model.acad;

import eims.model.com.AbstractEntity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PROC_OUT_COURSE_SCHEDULE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"COURSE_FOUNDED_ID", "CLASS_DATE"})})
@XmlRootElement
public class ProcOutCourseSchedule extends AbstractEntity {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @JoinColumn(name = "COURSE_FOUNDED_ID", nullable = false)
    @ManyToOne(optional = false)
    private CourseFounded courseFounded;
    @JoinColumn(name = "ROOM_ID", nullable = false)
    @ManyToOne(optional = false)
    private Room room;
    @Temporal(TemporalType.DATE)
    @Column(name = "CLASS_DATE", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date classDate;
    @Temporal(TemporalType.TIME)
    @Column(name = "START_TIME", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private Date startTime;
    @Temporal(TemporalType.TIME)
    @Column(name = "END_TIME", nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private Date endTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "COURSE_DAY_STATUS", length = 30)
    private CourseDayStatus courseDayStatus = CourseDayStatus.PENDING;

    public ProcOutCourseSchedule() {
    }

    public ProcOutCourseSchedule(CourseFounded courseFounded, Room room, Date classDate, Date startTime, Date endTime) {
        this.courseFounded = courseFounded;
        this.room = room;
        this.classDate = classDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
  

    public CourseFounded getCourseFounded() {
        return courseFounded;
    }

    public void setCourseFounded(CourseFounded courseFounded) {
        this.courseFounded = courseFounded;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public CourseDayStatus getCourseDayStatus() {
        return courseDayStatus;
    }

    public void setCourseDayStatus(CourseDayStatus courseDayStatus) {
        this.courseDayStatus = courseDayStatus;
    }

    @Override
    public String toString() {

        return courseFounded.getCourse() + " on " + sdf.format(classDate) + " (" + courseDayStatus + ')';
    }

}
