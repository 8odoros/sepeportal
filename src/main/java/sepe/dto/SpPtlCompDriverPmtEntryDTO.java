package sepe.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Annita on 5/2/2015.
 */
public class SpPtlCompDriverPmtEntryDTO implements Serializable {
    private Long companyId;
    private String week;
    private Timestamp day;

    public SpPtlCompDriverPmtEntryDTO(
            Long companyId,
            String week,
            Timestamp day
    ) {
        this.companyId = companyId;
        this.week = week;
        this.day = day;
    }

    public SpPtlCompDriverPmtEntryDTO(){
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) { this.week = week; }

    public Timestamp getDay() {
        return day;
    }

    public void setDay(Timestamp day) {
        this.day = day;
    }

}
