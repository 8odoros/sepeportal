package sepe.domain.company;

import javax.persistence.*;

/**
 * Created by Nikos on 6/16/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_TA_ANN_TA_ENTRY", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaAnnTaEntry {
    private Long id;
    private Long companyId;
    //private Long compTaAnnId;

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
    @Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }



 /*   @Basic
    @Column(name = "COMP_TA_ANN_ID")
    public Long getCompTaAnnId() {
        return compTaAnnId;
    }

    public void setCompTaAnnId(Long compTaAnnId) {
        this.compTaAnnId = compTaAnnId;
    }*/

    private Long cooperationType;
    @Basic
    @javax.persistence.Column(name = "COOPERATION_TYPE")
    public Long getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(Long cooperationType) {
        this.cooperationType = cooperationType;
    }

    private Long technicianRegrequestId;

    @Basic
    @javax.persistence.Column(name = "TECHNICIAN_REGREQUEST_ID")
    public Long getTechnicianRegrequestId() {
        return technicianRegrequestId;
    }

    public void setTechnicianRegrequestId(Long technicianRegrequestId) {
        this.technicianRegrequestId = technicianRegrequestId;
    }

    private Long technicianRegrequestUserId;

    @Basic
    @javax.persistence.Column(name = "TECHNICIAN_REGREQUEST_USER_ID")
    public Long getTechnicianRegrequestUserId() {
        return technicianRegrequestUserId;
    }

    public void setTechnicianRegrequestUserId(Long technicianRegrequestUserId) {
        this.technicianRegrequestUserId = technicianRegrequestUserId;
    }

    private String taFullname;

    @Basic
    @javax.persistence.Column(name = "TA_FULLNAME")
    public String getTaFullname() {
        return taFullname;
    }

    public void setTaFullname(String taFullname) {
        this.taFullname = taFullname;
    }

    private String taAfm;

    @Basic
    @javax.persistence.Column(name = "TA_AFM")
    public String getTaAfm() {
        return taAfm;
    }

    public void setTaAfm(String taAfm) {
        this.taAfm = taAfm;
    }

    private String taSpeciality;

    @Basic
    @javax.persistence.Column(name = "TA_SPECIALITY")
    public String getTaSpeciality() {
        return taSpeciality;
    }

    public void setTaSpeciality(String taSpeciality) {
        this.taSpeciality = taSpeciality;
    }

    private String taSpecialityOther;

    @Basic
    @javax.persistence.Column(name = "TA_SPECIALITY_OTHER")
    public String getTaSpecialityOther() {
        return taSpecialityOther;
    }

    public void setTaSpecialityOther(String taSpecialityOther) {
        this.taSpecialityOther = taSpecialityOther;
    }

    private Integer taAnnTaStatus;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_TA_STATUS")
    public Integer getTaAnnTaStatus() {
        return taAnnTaStatus;
    }

    public void setTaAnnTaStatus(Integer taAnnTaStatus) {
        this.taAnnTaStatus = taAnnTaStatus;
    }

    private Integer taAnnTaReplaceResponse;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_TA_REPLACE_RESPONSE")
    public Integer getTaAnnTaReplaceResponse() {
        return taAnnTaReplaceResponse;
    }

    public void setTaAnnTaReplaceResponse(Integer taAnnTaReplaceResponse) {
        this.taAnnTaReplaceResponse = taAnnTaReplaceResponse;
    }
    /////////////////////////////////////////////////
    // /*
    private SpPtlCompTaAnn compTaAnn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_TA_ANN_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompTaAnn getCompTaAnn() {
        return this.compTaAnn;
    }

    public void setCompTaAnn(SpPtlCompTaAnn compTaAnnTa) {
        this.compTaAnn = compTaAnnTa;
    }
    // */
    /////////////////////////////////////////////////


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaAnnTaEntry that = (SpPtlCompTaAnnTaEntry) o;


        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (cooperationType != null ? !cooperationType.equals(that.cooperationType) : that.cooperationType != null)
            return false;
        if (technicianRegrequestId != that.technicianRegrequestId) return false;
        if (technicianRegrequestUserId != that.technicianRegrequestUserId) return false;

        if (taFullname != null ? !taFullname.equals(that.taFullname) : that.taFullname != null) return false;
        if (taAfm != null ? !taAfm.equals(that.taAfm) : that.taAfm != null) return false;
        if (taSpeciality != null ? !taSpeciality.equals(that.taSpeciality) : that.taSpeciality != null) return false;
        if (taSpecialityOther != null ? !taSpecialityOther.equals(that.taSpecialityOther) : that.taSpecialityOther != null)
            return false;
        if (taAnnTaStatus != null ? !taAnnTaStatus.equals(that.taAnnTaStatus) : that.taAnnTaStatus != null)
            return false;
        if (taAnnTaReplaceResponse != null ? !taAnnTaReplaceResponse.equals(that.taAnnTaReplaceResponse) : that.taAnnTaReplaceResponse != null)
            return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (cooperationType != null ? cooperationType.hashCode() : 0);
        result = 31 * result + (technicianRegrequestId != null ? technicianRegrequestId.hashCode() : 0);
        result = 31 * result + (technicianRegrequestUserId != null ? technicianRegrequestUserId.hashCode() : 0);
        result = 31 * result + (taFullname != null ? taFullname.hashCode() : 0);
        result = 31 * result + (taAfm != null ? taAfm.hashCode() : 0);
        result = 31 * result + (taSpeciality != null ? taSpeciality.hashCode() : 0);
        result = 31 * result + (taSpecialityOther != null ? taSpecialityOther.hashCode() : 0);
        result = 31 * result + (taAnnTaStatus != null ? taAnnTaStatus.hashCode() : 0);
        result = 31 * result + (taAnnTaReplaceResponse != null ? taAnnTaReplaceResponse.hashCode() : 0);
        //result = 31 * result + (compTaAnn != null ? compTaAnn.hashCode() : 0);
        return result;
    }
/*
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (visitTime != null ? visitTime.hashCode() : 0);
        result = 31 * result + (visitDurationMinutes != null ? visitDurationMinutes.hashCode() : 0);
       // result = 31 * result + (compTaAnnId != null ? compTaAnnId.hashCode() : 0);
        result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
        return result;
    }
    */
}
