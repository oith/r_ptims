package eims.model.com;

import com.oith.annotation.MacSearchable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "LOOKUP", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"KEYWORD", "TITLE"})})
@XmlRootElement
public class Lookup extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "KEYWORD", length = 30)
    private LookupKeyword lookupKeyword;

    @MacSearchable
    @Basic(optional = false)
    @Column(name = "TITLE", nullable = false, length = 100)
    @NotNull
    @Size(min = 1, max = 100)
    @NotEmpty
    private String title;
    @Column(name = "TITLE_BNG", length = 500)
    private String titleBng;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;
    @MacSearchable
    @Column(name = "REMARKS", length = 500)
    private String remarks;

    public Lookup() {
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getSlNo() {
        return slNo;
    }

    public void setSlNo(Integer slNo) {
        this.slNo = slNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleBng() {
        return titleBng;
    }

    public void setTitleBng(String titleBng) {
        this.titleBng = titleBng;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LookupKeyword getLookupKeyword() {
        return lookupKeyword;
    }

    public void setLookupKeyword(LookupKeyword lookupKeyword) {
        this.lookupKeyword = lookupKeyword;
    }

    @Override
    public String toString() {
        return title;
    }

}
