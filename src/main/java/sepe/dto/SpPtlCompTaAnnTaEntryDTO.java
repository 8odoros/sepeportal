package sepe.dto;

import java.io.Serializable;

/**
 * Created by Marios on 5/9/2016.
 */
public class SpPtlCompTaAnnTaEntryDTO implements Serializable {

    private Long cooperationType;
    private String taAfm;
    private String taFullname;
    private String taSpeciality;
    private String taSpecialityOther;
    private Long technicianRegrequestId;
    private Long technicianRegrequestUserId;
    private Integer taAnnTaStatus;

    public SpPtlCompTaAnnTaEntryDTO(Long cooperationType, String taAfm, String taFullname, String taSpeciality, String taSpecialityOther, Long technicianRegrequestId, Long technicianRegrequestUserId, Integer taAnnTaStatus) {
        this.cooperationType = cooperationType;
        this.taAfm = taAfm;
        this.taFullname = taFullname;
        this.taSpeciality = taSpeciality;
        this.taSpecialityOther = taSpecialityOther;
        this.technicianRegrequestId = technicianRegrequestId;
        this.technicianRegrequestUserId = technicianRegrequestUserId;
        this.taAnnTaStatus = taAnnTaStatus;
    }



    public SpPtlCompTaAnnTaEntryDTO(){
    }

    public Long getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(Long cooperationType) {
        this.cooperationType = cooperationType;
    }

    public String getTaAfm() {
        return taAfm;
    }

    public void setTaAfm(String taAfm) {
        this.taAfm = taAfm;
    }

    public String getTaFullname() {
        return taFullname;
    }

    public void setTaFullname(String taFullname) {
        this.taFullname = taFullname;
    }

    public String getTaSpeciality() {
        return taSpeciality;
    }

    public void setTaSpeciality(String taSpeciality) {
        this.taSpeciality = taSpeciality;
    }

    public String getTaSpecialityOther() {
        return taSpecialityOther;
    }

    public void setTaSpecialityOther(String taSpecialityOther) {
        this.taSpecialityOther = taSpecialityOther;
    }

    public Long getTechnicianRegrequestId() {
        return technicianRegrequestId;
    }

    public void setTechnicianRegrequestId(Long technicianRegrequestId) {
        this.technicianRegrequestId = technicianRegrequestId;
    }

    public Long getTechnicianRegrequestUserId() {
        return technicianRegrequestUserId;
    }

    public void setTechnicianRegrequestUserId(Long technicianRegrequestUserId) {
        this.technicianRegrequestUserId = technicianRegrequestUserId;
    }

    public Integer getTaAnnTaStatus() {
        return taAnnTaStatus;
    }

    public void setTaAnnTaStatus(Integer taAnnTaStatus) {
        this.taAnnTaStatus = taAnnTaStatus;
    }
}
