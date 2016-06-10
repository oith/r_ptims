package eims.model.acad;

import eims.model.common.Person;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student extends Person {


    @JoinColumn(name = "GARDIAN_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Gardian gardian;



    public Gardian getGardian() {
        return gardian;
    }

    public void setGardian(Gardian gardian) {
        this.gardian = gardian;
    }

}
