package sepe.domain.company;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Evangelos on 18/4/2015.
 */
@Entity
@Table(name = "SP_PTL_COMPANY_DAILY_CARD", schema = "SP_PTL", catalog = "")
public class SpPtlCompanyDailyCard {
    private Long id;
    private Integer projectId;
    private Integer incNum;
    private Timestamp cardDate;
    private Set<SpPtlCompanyPersonnelBook> spPtlCompanyPersonnelBookById;
    private SpPtlCompanyProjects spPtlCompanyProjects;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Basic
    @Column(name = "INC_NUM")
    public Integer getIncNum() {
        return incNum;
    }

    public void setIncNum(Integer incNum) {
        this.incNum = incNum;
    }

    @Basic
    @Column(name = "CARD_DATE")
    public Timestamp getCardDate() {
        return cardDate;
    }

    public void setCardDate(Timestamp cardDate) {
        this.cardDate = cardDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompanyDailyCard that = (SpPtlCompanyDailyCard) o;

        if (cardDate != null ? !cardDate.equals(that.cardDate) : that.cardDate != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (incNum != null ? !incNum.equals(that.incNum) : that.incNum != null) return false;
        if (projectId != null ? !projectId.equals(that.projectId) : that.projectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (incNum != null ? incNum.hashCode() : 0);
        result = 31 * result + (cardDate != null ? cardDate.hashCode() : 0);
        return result;
    }


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spPtlCompanyDailyCard", cascade = CascadeType.ALL, orphanRemoval = true) /* SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!! */
    public Set<SpPtlCompanyPersonnelBook> getSpPtlCompanyPersonnelBookById() {
        return spPtlCompanyPersonnelBookById;
    }

    public void setSpPtlCompanyPersonnelBookById(Set<SpPtlCompanyPersonnelBook> spPtlCompanyPersonnelBookById) {
        this.spPtlCompanyPersonnelBookById = spPtlCompanyPersonnelBookById;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROJECT_ID", nullable = false, insertable = true, updatable = false)
    public SpPtlCompanyProjects getSpPtlCompanyProjects() {
        return spPtlCompanyProjects;
    }

    public void setSpPtlCompanyProjects(SpPtlCompanyProjects spPtlCompanyProjects) {
        this.spPtlCompanyProjects = spPtlCompanyProjects;
    }

    @Transient
    public Integer getProjectIdd() {
        return projectId;
    }

    @Transient
    public void setProjectIdd(Integer projectId) {
        this.projectId = projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

}
