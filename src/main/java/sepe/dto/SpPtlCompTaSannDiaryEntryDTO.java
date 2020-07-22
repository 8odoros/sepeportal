package sepe.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Annita on 5/20/2015.
 */
public class SpPtlCompTaSannDiaryEntryDTO implements Serializable {
    private String visitTime;
    private Integer visitDurationMinutes;
    private Timestamp visitDate;

    public SpPtlCompTaSannDiaryEntryDTO(
            String visitTime,
            Integer visitDurationMinutes,
            Timestamp visitDate
    ) {
        this.visitTime = visitTime;
        this.visitDurationMinutes = visitDurationMinutes;
        this.visitDate = visitDate;
    }

    public SpPtlCompTaSannDiaryEntryDTO(){
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
}
