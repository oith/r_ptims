package eims.model.acad;

import eims.model.hr.Employee;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACADEMIC_STAFF")
public class AcademicStaff extends Employee {

    String specility;

}
