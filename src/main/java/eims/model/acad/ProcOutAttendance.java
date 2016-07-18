package eims.model.acad;

import eims.model.common.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PROC_OUT_ATTENDANCE")
@XmlRootElement
public class ProcOutAttendance extends AbstractEntity {

    @JoinColumn(name = "STUDENT_ID", nullable = false)
    @ManyToOne(optional = false)
    private Student student;

    @JoinColumn(name = "PROC_OUT_COURSE_SCHEDULE_ID", nullable = false)
    @ManyToOne(optional = false)
    private ProcOutCourseSchedule procOutCourseSchedule;

    public ProcOutAttendance() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ProcOutCourseSchedule getProcOutCourseSchedule() {
        return procOutCourseSchedule;
    }

    public void setProcOutCourseSchedule(ProcOutCourseSchedule procOutCourseSchedule) {
        this.procOutCourseSchedule = procOutCourseSchedule;
    }

}
