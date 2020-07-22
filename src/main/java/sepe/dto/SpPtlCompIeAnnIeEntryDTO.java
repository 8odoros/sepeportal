package sepe.dto;

import java.io.Serializable;

/**
 * Created by Marios on 5/9/2016.
 */
public class SpPtlCompIeAnnIeEntryDTO implements Serializable {

    private Long cooperationType;
    private String ieAfm;
    private String ieFullname;
    private Long ieSpeciality;
    private String ieSpecialityOther;
    private Long doctorRegrequestId;
    private Long doctorRegrequestUserId;
    private Integer ieAnnIeStatus;

    public SpPtlCompIeAnnIeEntryDTO(Long cooperationType, String ieAfm, String ieFullname, Long ieSpeciality, String ieSpecialityOther, Long doctorRegrequestId, Long doctorRegrequestUserId, Integer ieAnnIeStatus) {
        this.cooperationType = cooperationType;
        this.ieAfm = ieAfm;
        this.ieFullname = ieFullname;
        this.ieSpeciality = ieSpeciality;
        this.ieSpecialityOther = ieSpecialityOther;
        this.doctorRegrequestId = doctorRegrequestId;
        this.doctorRegrequestUserId = doctorRegrequestUserId;
        this.ieAnnIeStatus = ieAnnIeStatus;
    }



    public SpPtlCompIeAnnIeEntryDTO(){
    }

    public Long getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(Long cooperationType) {
        this.cooperationType = cooperationType;
    }

    public String getIeAfm() {
        return ieAfm;
    }

    public void setIeAfm(String ieAfm) {
        this.ieAfm = ieAfm;
    }

    public String getIeFullname() {
        return ieFullname;
    }

    public void setIeFullname(String ieFullname) {
        this.ieFullname = ieFullname;
    }

    public Long getIeSpeciality() {
        return ieSpeciality;
    }

    public void setIeSpeciality(Long ieSpeciality) {
        this.ieSpeciality = ieSpeciality;
    }

    public String getIeSpecialityOther() {
        return ieSpecialityOther;
    }

    public void setIeSpecialityOther(String ieSpecialityOther) {
        this.ieSpecialityOther = ieSpecialityOther;
    }

    public Long getDoctorRegrequestId() {
        return doctorRegrequestId;
    }

    public void setDoctorRegrequestId(Long doctorRegrequestId) {
        this.doctorRegrequestId = doctorRegrequestId;
    }

    public Long getDoctorRegrequestUserId() {
        return doctorRegrequestUserId;
    }

    public void setDoctorRegrequestUserId(Long doctorRegrequestUserId) {
        this.doctorRegrequestUserId = doctorRegrequestUserId;
    }

    public Integer getIeAnnIeStatus() {
        return ieAnnIeStatus;
    }

    public void setIeAnnIeStatus(Integer ieAnnIeStatus) {
        this.ieAnnIeStatus = ieAnnIeStatus;
    }
}
