package eims.model.acad;

import eims.model.com.Person;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student extends Person {

    @Column(name = "EXPERIENCE_IN_INFO_TECH", length = 30)
    private String experienceInInfoTech;

    public String getExperienceInInfoTech() {
        return experienceInInfoTech;
    }

    public void setExperienceInInfoTech(String experienceInInfoTech) {
        this.experienceInInfoTech = experienceInInfoTech;
    }

}
