package eims.model.com;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ADM_PROCESS")
public class AdmProcess extends AbstractCodableEntity {

    @JoinColumn(name = "ADM_MODULE_ID", nullable = false)
    @ManyToOne(optional = false)
    private AdmModule admModule;
    @Size(max = 2000)
    @Column(name = "query")
    private String query;
    @Column(name = "QUERY_ALIAS", length = 500)
    private String queryAlias;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "SL_NO")
    private Integer slNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admProcess", fetch = FetchType.EAGER)
    @OrderBy(value = "slNo DESC")
    private Set<AdmProcessDetail> admProcessDetails;

    @Column(name = "PARAM_SEARCHS", length = 1000)
    private String paramSearchs;
    @Column(name = "PARAM_FIXEDS", length = 1000)
    private String paramFixeds;
    @Column(name = "PROCESS_BTNS", length = 1000)
    private String processBtns;

    @Size(max = 500)
   @Column(name = "remarks", length = 500)
    private String remarks;

    public AdmProcess() {
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<AdmProcessDetail> getAdmProcessDetails() {
        return admProcessDetails;
    }

    public void setAdmProcessDetails(Set<AdmProcessDetail> admProcessDetails) {
        this.admProcessDetails = admProcessDetails;
    }

    public AdmModule getAdmModule() {
        return admModule;
    }

    public void setAdmModule(AdmModule admModule) {
        this.admModule = admModule;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQueryAlias() {
        return queryAlias;
    }

    public void setQueryAlias(String queryAlias) {
        this.queryAlias = queryAlias;
    }

    @Override
    public String toString() {
        return super.getFullName();
    }

    public String getParamSearchs() {
        return paramSearchs;
    }

    public void setParamSearchs(String paramSearchs) {
        this.paramSearchs = paramSearchs;
    }

    public String getParamFixeds() {
        return paramFixeds;
    }

    public void setParamFixeds(String paramFixeds) {
        this.paramFixeds = paramFixeds;
    }

    public String getProcessBtns() {
        return processBtns;
    }

    public void setProcessBtns(String processBtns) {
        this.processBtns = processBtns;
    }
}
