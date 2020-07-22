package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/10/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_SEC_DIARY_ENTRY", schema = "SP_PTL", catalog = "")
public class SpPtlCompSecDiaryEntry {
    private Long id;
    private Long compSecDiaryId;
    private Integer aa;
    private Long companyId;
    private Timestamp creationDate;
    private String assessment;
    private String suggestion;
    private Timestamp updateDate;
    private Long compSecDiaryEngId;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Basic
    @Column(name = "COMP_SEC_DIARY_ID")
    public Long getCompSecDiaryId() {
        return compSecDiaryId;
    }

    public void setCompSecDiaryId(Long compSecDiaryId) {
        this.compSecDiaryId = compSecDiaryId;
    }


    @Basic
    @Column(name = "AA")
    public Integer getAa() {
        return aa;
    }

    public void setAa(Integer aa) {
        this.aa = aa;
    }

    @Basic
    @Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "CREATION_DATE")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "ASSESSMENT")
    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    @Basic
    @Column(name = "SUGGESTION")
    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Basic
    @Column(name = "UPDATE_DATE")
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "COMP_SEC_DIARY_ENG_ID")
    public Long getCompSecDiaryEngId() {
        return compSecDiaryEngId;
    }

    public void setCompSecDiaryEngId(Long compSecDiaryEngId) {
        this.compSecDiaryEngId = compSecDiaryEngId;
    }

    /////////////////////////////////////////////////
     /*
    private SpPtlCompSecDiary compSecDiary_entry;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMP_SEC_DIARY_ID", nullable = true, insertable = true, updatable = true) // Name is the DB column name of the attribute
    public SpPtlCompSecDiary getCompSecDiary_entry() {
        return this.compSecDiary_entry;
    }

    public void setCompSecDiary_entry(SpPtlCompSecDiary compSecDiary_entry) {
        this.compSecDiary_entry = compSecDiary_entry;
    }
     */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompSecDiaryEntry that = (SpPtlCompSecDiaryEntry) o;

        if (id != that.id) return false;
        if (compSecDiaryId != null ? !compSecDiaryId.equals(that.compSecDiaryId) : that.compSecDiaryId != null) return false;
        if (aa != null ? !aa.equals(that.aa) : that.aa != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (assessment != null ? !assessment.equals(that.assessment) : that.assessment != null) return false;
        if (suggestion != null ? !suggestion.equals(that.suggestion) : that.suggestion != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (compSecDiaryEngId != null ? !compSecDiaryEngId.equals(that.compSecDiaryEngId) : that.compSecDiaryEngId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (compSecDiaryId != null ? compSecDiaryId.hashCode() : 0);
        result = 31 * result + (aa != null ? aa.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (assessment != null ? assessment.hashCode() : 0);
        result = 31 * result + (suggestion != null ? suggestion.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (compSecDiaryEngId != null ? compSecDiaryEngId.hashCode() : 0);
        return result;
    }
}
