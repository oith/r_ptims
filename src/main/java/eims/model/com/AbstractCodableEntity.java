package eims.model.com;

import com.oith.annotation.MacCodable;
import com.oith.annotation.MacSearchable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ABSTRACT_CODABLE_ENTITY")
@XmlRootElement
@MacCodable(id = "id", code = "code", caption = "fullName")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractCodableEntity extends AbstractEntity implements ICodeable {

    @MacSearchable
    @Column(name = "CODE", unique = true, length = 20, nullable = false)
    private String code;
    @MacSearchable
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String fullName;

    public AbstractCodableEntity() {
    }

    @Override
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

    @Override
    public String toString() {
        return code + "-" + fullName;
    }
}
