package eims.model.acad;

import eims.model.common.Person;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public abstract class Student extends Person {

}
