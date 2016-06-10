package eims.model.acad;

import eims.model.common.Person;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "GARDIAN")
public class Gardian extends Person {

    Integer credit;
}
