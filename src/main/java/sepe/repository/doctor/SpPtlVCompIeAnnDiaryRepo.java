package sepe.repository.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.doctor.SpPtlVCompIeAnnDiary;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikos on 5/20/2015.
 */

// http://localhost:7001/vCompIeAnnDiary
// http://localhost:7001/vCompIeAnnDiary/search/findByCompIeAnnId?compIeAnnId=1350
// http://localhost:7001/vCompIeAnnDiary/search/findOne?id=1353
// http://localhost:7001/vCompIeAnnDiary/search/countIeVisits?idUserId=584&epochStart=1433934000&durationMins=1
// http://localhost:7001/vCompIeAnnDiary/search/findByMonthAndYear?month=6&year=2015
// http://localhost:7001/vCompIeAnnDiary/search/IeVisitConflict?idUserId=584&epochStart=1433934000&durationMins=1
@RepositoryRestResource(collectionResourceRel = "vCompIeAnnDiary", path = "/vCompIeAnnDiary")
public interface SpPtlVCompIeAnnDiaryRepo extends PagingAndSortingRepository<SpPtlVCompIeAnnDiary, Long> {

    /*
    * IE requests all his program [for all announcements/companies]
    */
    @Query(
            value = "SELECT o FROM SpPtlVCompIeAnnDiary o"
                    + " WHERE o.doctorRegrequestUserId =?#{principal.userId}"
            //,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompIeAnnDiary> findAll(Pageable pageable);

    /*
    * IE requests his program for a specific announcement
    */
    @Query(
            value = "SELECT o FROM SpPtlVCompIeAnnDiary o"
                    + " WHERE o.doctorRegrequestUserId =?#{principal.userId}"
                    + " AND o.compIeAnnId =:#{#compIeAnnId}"
            //,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompIeAnnDiary> findByCompIeAnnId(Pageable pageable, @Param("compIeAnnId") Long compIeAnnId);


    @Query(
            value = "SELECT o FROM SpPtlVCompIeAnnDiary o"
                    + " WHERE o.doctorRegrequestUserId =?#{principal.userId}"
                    + " AND o.id =:#{#id}"
            //,nativeQuery = false
    )
    @Nullable
    public SpPtlVCompIeAnnDiary findOne(@Param("id") Long id);


    @Query(
            value = "SELECT o FROM SpPtlVCompIeAnnDiary o"
                    + " WHERE o.doctorRegrequestUserId =?#{principal.userId}"
                    + " AND o.visitMonth =:#{#month} AND o.visitYear =:#{#year}"
            //,nativeQuery = false
    )
    @Nullable
    public List<SpPtlVCompIeAnnDiary> findByMonthAndYear(Pageable pageable, @Param("month") Integer month, @Param("year") Integer year);

    /*
    * Epoch is calculated in seconds.
    *
    * ,(ROUND((TO_DATE(to_char(VISIT_DATE,'DD/MM/YYYY HH24:MI'),'DD/MM/YYYY HH24:MI') - TO_DATE('01/01/1970 00:00','DD/MM/YYYY HH24:MI')) * 60 * 60 * 24)) AS VISIT_DATE_EPOCH_SEC
    *
    *
        SELECT * FROM
        (SELECT
        ID,
        (ROUND((TO_DATE(to_char(VISIT_DATE,'DD/MM/YYYY HH24:MI'),'DD/MM/YYYY HH24:MI') - TO_DATE('01/01/1970 00:00','DD/MM/YYYY HH24:MI')) * 60 * 60 * 24)) AS VISIT_MINUTE_START,
        (ROUND((TO_DATE(to_char(VISIT_DATE,'DD/MM/YYYY HH24:MI'),'DD/MM/YYYY HH24:MI') - TO_DATE('01/01/1970 00:00','DD/MM/YYYY HH24:MI')) * 60 * 60 * 24)) + VISIT_DURATION_MINUTES*60 AS VISIT_MINUTE_END
        from SP_PTL_V_COMP_IE_ANN_DIARY
        WHERE DOCTOR_REGREQUEST_USER_ID = 55)
        WHERE
        0 <= VISIT_MINUTE_START AND 1 >= VISIT_MINUTE_START
        OR
        0 >= VISIT_MINUTE_START AND 1 <= VISIT_MINUTE_END
        OR
        0 >= VISIT_MINUTE_END AND 1 >= VISIT_MINUTE_END
    */
    @Query(
            /*
            value = "SELECT count(*) FROM"
            + " (SELECT"
            +  " ID,"
            + " (ROUND((TO_DATE(to_char(VISIT_DATE,'DD/MM/YYYY HH24:MI'),'DD/MM/YYYY HH24:MI') - TO_DATE('01/01/1970 00:00','DD/MM/YYYY HH24:MI')) * 60 * 60 * 24)) AS VISIT_MINUTE_START,"
            + " (ROUND((TO_DATE(to_char(VISIT_DATE,'DD/MM/YYYY HH24:MI'),'DD/MM/YYYY HH24:MI') - TO_DATE('01/01/1970 00:00','DD/MM/YYYY HH24:MI')) * 60 * 60 * 24)) + (VISIT_DURATION_MINUTES*60) AS VISIT_MINUTE_END"
            + " FROM SP_PTL_V_COMP_IE_ANN_DIARY"
            + " WHERE DOCTOR_REGREQUEST_USER_ID =:#{#idUserId})"
            + " WHERE"
            + " :epochStart <= VISIT_MINUTE_START AND :#{#epochStart} + ((:#{#durationMins})*60) >= VISIT_MINUTE_START"
            + " OR"
            + " :epochStart >= VISIT_MINUTE_START AND :#{#epochStart} + ((:#{#durationMins})*60) <= VISIT_MINUTE_END"
            + " OR"
            + " :epochStart >= VISIT_MINUTE_END AND :#{#epochStart} + ((:#{#durationMins})*60) >= VISIT_MINUTE_END"
            ,nativeQuery = true
            */
            value = "SELECT count(o) FROM SpPtlVCompIeAnnDiary o"
                    + " WHERE o.doctorRegrequestUserId =:#{#idUserId}"
                    + " AND("
                    + " :#{#epochStart} <= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) > o.visitDateEpochSec"
                    + " OR"
                    + " :#{#epochStart} >= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) <= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + " OR"
                    + " :#{#epochStart} < o.visitDateEpochSec + o.visitDurationMinutes*60 AND :#{#epochStart} + ((:#{#durationMins})*60) >= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + ")"
            ,nativeQuery = false
    )
    public Long countIeVisits(@Param("idUserId") Long idUserId, @Param("epochStart") Long epochStart, @Param("durationMins") Long durationMins);

    @Query(
            value = "SELECT o.id FROM SpPtlVCompIeAnnDiary o"
                    + " WHERE o.doctorRegrequestUserId =:#{#idUserId}"
                    + " AND("
                    + " :#{#epochStart} <= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) > o.visitDateEpochSec"
                    + " OR"
                    + " :#{#epochStart} >= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) <= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + " OR"
                    + " :#{#epochStart} < o.visitDateEpochSec + o.visitDurationMinutes*60 AND :#{#epochStart} + ((:#{#durationMins})*60) >= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + ")"
            ,nativeQuery = false
    )
    public List<Long> IeVisitConflict(@Param("idUserId") Long idUserId, @Param("epochStart") Long epochStart, @Param("durationMins") Long durationMins);

    @Query(
            value = "SELECT o.id FROM SpPtlVCompIeAnnDiary o"
                    + " WHERE o.doctorRegrequestUserId =:#{#idUserId}"
                    + " AND("
                    + " :#{#epochStart} <= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) > o.visitDateEpochSec"
                    + " OR"
                    + " :#{#epochStart} >= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) <= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + " OR"
                    + " :#{#epochStart} < o.visitDateEpochSec + o.visitDurationMinutes*60 AND :#{#epochStart} + ((:#{#durationMins})*60) >= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + ")"
                    + " AND"
                    + "  o.compIeAnnId != :#{#idOfAnn} "
            ,nativeQuery = false
    )
    public List<Long> ieVisitConflictNotOne(@Param("idUserId") Long idUserId, @Param("epochStart") Long epochStart, @Param("durationMins") Long durationMins, @Param("idOfAnn") Long idOfAnn);

    @Query(
            value = "SELECT sum(o.visitDurationMinutes) FROM SpPtlVCompIeAnnDiary o"
                    + " WHERE o.compIeAnnId =:#{#ieAnnId}"
            ,nativeQuery = false
    )
    public Long sumOfIeAnnMinutes(@Param("ieAnnId") Long ieAnnId);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public SpPtlEmployeeGenrequest findOne(Long id);

    //@Override
    //@RestResource(exported = false)
    //public Page<SpPtlEmployeeGenrequest> findAll(Pageable pageable);
    @Override
    @RestResource(exported = false)
    public long count();

    @Override
    @RestResource(exported = false)
    public void delete(Long id);

//@Override
//@RestResource(exported = false)
//public void delete(List<OBJ_TYPE> entities);

//@Override
//@RestResource(exported = false)
//public void delete(OBJ_TYPE entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll();

    @Override
    @RestResource(exported = false)
    public boolean exists(Long id);

//@Override
//@RestResource(exported = false)
//public List<OBJ_TYPE> findAll(List<ID_TYPE> ids);

//@Override
//@RestResource(exported = false)
//public List<OBJ_TYPE> save(List<OBJ_TYPE> entities);

    @Override
    @RestResource(exported = false)
    public SpPtlVCompIeAnnDiary save(SpPtlVCompIeAnnDiary entity);
}