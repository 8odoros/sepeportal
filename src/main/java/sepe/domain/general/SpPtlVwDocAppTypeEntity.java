package sepe.domain.general;


import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Evangelos on 17/3/2015.
 */
@Entity
@Table(name = "SP_PTL_VW_DOC_APP_TYPE", schema = "SP_PTL", catalog = "")
public class SpPtlVwDocAppTypeEntity {
    private Integer docId;
    private Integer userId;
    private Integer applicationType;
    private Integer applicationId;
    private Integer subStatus;
    private String protNo;
    private Timestamp protDate;


    @Id
    @Column(name = "DOC_ID")
    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    @Basic
    @Column(name = "USER_ID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "APPLICATION_TYPE")
    public Integer getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(Integer applicationType) {
        this.applicationType = applicationType;
    }

    @Basic
    @Column(name = "APPLICATION_ID")
    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    @Basic
    @Column(name = "SUB_STATUS")
    public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    @Basic
    @Column(name = "PROT_NO")
    public String getProtNo() {
        return protNo;
    }

    public void setProtNo(String protNo) {
        this.protNo = protNo;
    }

    @Basic
    @Column(name = "PROT_DATE")
    public Timestamp getProtDate() {
        return protDate;
    }

    public void setProtDate(Timestamp protDate) {
        this.protDate = protDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVwDocAppTypeEntity that = (SpPtlVwDocAppTypeEntity) o;

        if (applicationType != null ? !applicationType.equals(that.applicationType) : that.applicationType != null)
            return false;
        if (applicationId != null ? !applicationId.equals(that.applicationId) : that.applicationId != null)
            return false;

        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = docId != null ? docId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (applicationType != null ? applicationType.hashCode() : 0);
        result = 31 * result + (applicationId != null ? applicationId.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        return result;
    }
}
