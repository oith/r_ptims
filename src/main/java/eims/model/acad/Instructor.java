package eims.model.acad;

import eims.model.hr.Employee;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "INSTRUCTOR")
public class Instructor extends Employee {

    @Column(name = "SPECIALITY", length = 500)
    private String speciality;
    @OneToMany
    @JoinTable(name = "INSTRUCTOR_COURSE_LK",
            joinColumns = @JoinColumn(name = "INSTRUCTOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    @OrderBy(value = "code ASC")
    private Set<Course> courses;

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}
