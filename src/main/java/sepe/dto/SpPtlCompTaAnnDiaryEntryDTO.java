package sepe.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Annita on 5/20/2015.
 */
public class SpPtlCompTaAnnDiaryEntryDTO implements Serializable {
    private String visitTime;
    private Integer visitDurationMinutes;
    private Timestamp visitDate;


    private Long compTaAnnTaId;

    public SpPtlCompTaAnnDiaryEntryDTO(
            String visitTime,
            Integer visitDurationMinutes,
            Timestamp visitDate,
            Long compTaAnnTaId
    ) {
        this.visitTime = visitTime;
        this.visitDurationMinutes = visitDurationMinutes;
        this.visitDate = visitDate;
        this.compTaAnnTaId = compTaAnnTaId;
    }

    public SpPtlCompTaAnnDiaryEntryDTO(){
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public Integer getVisitDurationMinutes() {
        return visitDurationMinutes;
    }

    public void setVisitDurationMinutes(Integer visitDurationMinutes) { this.visitDurationMinutes = visitDurationMinutes; }

    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }


    public void setCompTaAnnTaId(Long compTaAnnTaId) {
        this.compTaAnnTaId = compTaAnnTaId;
    }

    public Long getCompTaAnnTaId() {
        return compTaAnnTaId;
    }

}
