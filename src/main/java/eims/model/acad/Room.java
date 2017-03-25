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
@Table(name = "ROOM")
@XmlRootElement
public class Room extends AbstractEntity {

    @Column(name = "CODE", unique = true, length = 20, nullable = false)
    private String code;
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String fullName;
    @Column(name = "NO_OF_COMPUTER")
    @Range(min = 1, max = 32)
    private Integer noOfComputer;
    @Column(name = "NO_OF_SEAT")
    @Range(min = 1, max = 32)
    private Integer noOfSeat;
    @Column(name = "HAS_PROJECTOR")
    private Boolean hasProjector;
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    public Room() {
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

    public Boolean getHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(Boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNoOfComputer() {
        return noOfComputer;
    }

    public void setNoOfComputer(Integer noOfComputer) {
        this.noOfComputer = noOfComputer;
    }

    public Integer getNoOfSeat() {
        return noOfSeat;
    }

    public void setNoOfSeat(Integer noOfSeat) {
        this.noOfSeat = noOfSeat;
    }

    @Override
    public String toString() {
        return code + "-" + fullName;
    }

}
