package sepe.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Annita on 5/20/2015.
 */
public class SpPtlCompIeAnnDiaryEntryDTO implements Serializable {
    private String visitTime;
    private Integer visitDurationMinutes;
    private Timestamp visitDate;

    private Long compIeAnnIeId;

    public SpPtlCompIeAnnDiaryEntryDTO(
            String visitTime,
            Integer visitDurationMinutes,
            Timestamp visitDate,
            Long compIeAnnIeId
    ) {
        this.visitTime = visitTime;
        this.visitDurationMinutes = visitDurationMinutes;
        this.visitDate = visitDate;
        this.compIeAnnIeId = compIeAnnIeId;
    }

    public SpPtlCompIeAnnDiaryEntryDTO(){
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

    public void setCompIeAnnIeId(Long compIeAnnIeId) {
        this.compIeAnnIeId = compIeAnnIeId;
    }

    public Long getCompIeAnnIeId() {
        return compIeAnnIeId;
    }
}
