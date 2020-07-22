package sepe.domain.company;

import javax.persistence.*;

/**
 * Created by Nikos on 6/16/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_IE_ANN_IE_ENTRY", schema = "SP_PTL", catalog = "")
public class SpPtlCompIeAnnIeEntry {
    private Long id;
    private Long companyId;
    //private Long compIeAnnId;

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



   /*@Basic
    @Column(name = "COMP_IE_ANN_ID")
    public Long getCompIeAnnId() {
        return compIeAnnId;
    }

    public void setCompIeAnnId(Long compIeAnnId) {
        this.compIeAnnId = compIeAnnId;
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

    private Long doctorRegrequestId;

    @Basic
    @javax.persistence.Column(name = "DOCTOR_REGREQUEST_ID")
    public Long getDoctorRegrequestId() {
        return doctorRegrequestId;
    }

    public void setDoctorRegrequestId(Long doctorRegrequestId) {
        this.doctorRegrequestId = doctorRegrequestId;
    }

    private Long doctorRegrequestUserId;

    @Basic
    @javax.persistence.Column(name = "DOCTOR_REGREQUEST_USER_ID")
    public Long getDoctorRegrequestUserId() {
        return doctorRegrequestUserId;
    }

    public void setDoctorRegrequestUserId(Long doctorRegrequestUserId) {
        this.doctorRegrequestUserId = doctorRegrequestUserId;
    }

    private String ieFullname;

    @Basic
    @javax.persistence.Column(name = "IE_FULLNAME")
    public String getIeFullname() {
        return ieFullname;
    }

    public void setIeFullname(String ieFullname) {
        this.ieFullname = ieFullname;
    }

    private String ieAfm;

    @Basic
    @javax.persistence.Column(name = "IE_AFM")
    public String getIeAfm() {
        return ieAfm;
    }

    public void setIeAfm(String ieAfm) {
        this.ieAfm = ieAfm;
    }

    private Long ieSpeciality;

    @Basic
    @javax.persistence.Column(name = "IE_SPECIALITY")
    public Long getIeSpeciality() {
        return ieSpeciality;
    }

    public void setIeSpeciality(Long ieSpeciality) {
        this.ieSpeciality = ieSpeciality;
    }

    private String ieSpecialityOther;

    @Basic
    @javax.persistence.Column(name = "IE_SPECIALITY_OTHER")
    public String getIeSpecialityOther() {
        return ieSpecialityOther;
    }

    public void setIeSpecialityOther(String ieSpecialityOther) {
        this.ieSpecialityOther = ieSpecialityOther;
    }

    private Integer ieAnnIeStatus;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_IE_STATUS")
    public Integer getIeAnnIeStatus() {
        return ieAnnIeStatus;
    }

    public void setIeAnnIeStatus(Integer ieAnnIeStatus) {
        this.ieAnnIeStatus = ieAnnIeStatus;
    }

    private Integer ieAnnIeReplaceResponse;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_IE_REPLACE_RESPONSE")
    public Integer getIeAnnIeReplaceResponse() {
        return ieAnnIeReplaceResponse;
    }

    public void setIeAnnIeReplaceResponse(Integer ieAnnIeReplaceResponse) {
        this.ieAnnIeReplaceResponse = ieAnnIeReplaceResponse;
    }
    /////////////////////////////////////////////////
    // /*
    private SpPtlCompIeAnn compIeAnn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_IE_ANN_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompIeAnn getCompIeAnn() {
        return this.compIeAnn;
    }

    public void setCompIeAnn(SpPtlCompIeAnn compIeAnnIe) {
        this.compIeAnn = compIeAnnIe;
    }
    // */
    /////////////////////////////////////////////////


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompIeAnnIeEntry that = (SpPtlCompIeAnnIeEntry) o;


        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (cooperationType != null ? !cooperationType.equals(that.cooperationType) : that.cooperationType != null)
            return false;
        if (doctorRegrequestId != that.doctorRegrequestId) return false;
        if (doctorRegrequestUserId != that.doctorRegrequestUserId) return false;

        if (ieFullname != null ? !ieFullname.equals(that.ieFullname) : that.ieFullname != null) return false;
        if (ieAfm != null ? !ieAfm.equals(that.ieAfm) : that.ieAfm != null) return false;
        if (ieSpeciality != null ? !ieSpeciality.equals(that.ieSpeciality) : that.ieSpeciality != null) return false;
        if (ieSpecialityOther != null ? !ieSpecialityOther.equals(that.ieSpecialityOther) : that.ieSpecialityOther != null)
            return false;
        if (ieAnnIeStatus != null ? !ieAnnIeStatus.equals(that.ieAnnIeStatus) : that.ieAnnIeStatus != null)
            return false;
        if (ieAnnIeReplaceResponse != null ? !ieAnnIeReplaceResponse.equals(that.ieAnnIeReplaceResponse) : that.ieAnnIeReplaceResponse != null)
            return false;
        //if (compIeAnnId != null ? !compIeAnnId.equals(that.compIeAnnId) : that.compIeAnnId != null) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (cooperationType != null ? cooperationType.hashCode() : 0);
        result = 31 * result + (doctorRegrequestId != null ? doctorRegrequestId.hashCode() : 0);
        result = 31 * result + (doctorRegrequestUserId != null ? doctorRegrequestUserId.hashCode() : 0);
        result = 31 * result + (ieFullname != null ? ieFullname.hashCode() : 0);
        result = 31 * result + (ieAfm != null ? ieAfm.hashCode() : 0);
        result = 31 * result + (ieSpeciality != null ? ieSpeciality.hashCode() : 0);
        result = 31 * result + (ieSpecialityOther != null ? ieSpecialityOther.hashCode() : 0);
        result = 31 * result + (ieAnnIeStatus != null ? ieAnnIeStatus.hashCode() : 0);
        result = 31 * result + (ieAnnIeReplaceResponse != null ? ieAnnIeReplaceResponse.hashCode() : 0);
        //result = 31 * result + (compIeAnnId != null ? compIeAnnId.hashCode() : 0);
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
