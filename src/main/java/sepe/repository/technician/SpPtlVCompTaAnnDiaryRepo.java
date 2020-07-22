package sepe.repository.technician;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.technician.SpPtlTechnicianRegrequest;
import sepe.domain.technician.SpPtlVCompTaAnnDiary;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikos on 6/18/2015.
 */
// http://localhost:7001/vCompTaAnnDiary
// http://localhost:7001/vCompTaAnnDiary/search/findByCompTaAnnId?compTaAnnId=1350
// http://localhost:7001/vCompTaAnnDiary/search/findOne?id=1353
// http://localhost:7001/vCompTaAnnDiary/search/countTaVisits?idUserId=584&epochStart=1433934000&durationMins=1
// http://localhost:7001/vCompTaAnnDiary/search/findByMonthAndYear?month=6&year=2015
// http://localhost:7001/vCompTaAnnDiary/search/taVisitConflict?idUserId=584&epochStart=1433934000&durationMins=1
@RepositoryRestResource(collectionResourceRel = "vCompTaAnnDiary", path = "/vCompTaAnnDiary")
public interface SpPtlVCompTaAnnDiaryRepo extends PagingAndSortingRepository<SpPtlVCompTaAnnDiary, Long> {

    /*
    * IE requests all his program [for all announcements/companies]
    */
    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnnDiary o"
                    + " WHERE o.technicianRegrequestUserId =?#{principal.userId}"
            //,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompTaAnnDiary> findAll(Pageable pageable);

    /*
    * IE requests his program for a specific announcement
    */
    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnnDiary o"
                    + " WHERE o.technicianRegrequestUserId =?#{principal.userId}"
                    + " AND o.compTaAnnId =:#{#compTaAnnId}"
            //,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompTaAnnDiary> findByCompTaAnnId(Pageable pageable, @Param("compTaAnnId") Long compTaAnnId);

    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnnDiary o"
                    + " WHERE o.compTaAnnId =:#{#compTaAnnId}"
            //,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompTaAnnDiary> findByCompTaAnnId2(Pageable pageable, @Param("compTaAnnId") Long compTaAnnId);

    @Query(
            value = "SELECT DISTINCT o FROM SpPtlVCompTaAnnDiary o, SpPtlVUserSafetyTechnician c"
                    + " WHERE o.technicianRegrequestUserId = c.id"
                    + " AND c.afm =:#{#afm}"
                    + " AND o.compTaAnnId =:#{#compTaAnnId}"
            //,nativeQuery = false
    )
    @Nullable
    public List<SpPtlVCompTaAnnDiary> findByCompTaAnnIdDiary(@Param("compTaAnnId") Long compTaAnnId, @Param("afm") String afm);

    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnnDiary o"
                    + " WHERE o.technicianRegrequestUserId =?#{principal.userId}"
                    + " AND o.id =:#{#id}"
            //,nativeQuery = false
    )
    @Nullable
    public SpPtlVCompTaAnnDiary findOne(@Param("id") Long id);


    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnnDiary o"
                    + " WHERE o.technicianRegrequestUserId =?#{principal.userId}"
                    + " AND o.visitMonth =:#{#month} AND o.visitYear =:#{#year}"
            //,nativeQuery = false
    )
    @Nullable
    public List<SpPtlVCompTaAnnDiary> findByMonthAndYear(Pageable pageable, @Param("month") Integer month, @Param("year") Integer year);

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
            value = "SELECT count(o) FROM SpPtlVCompTaAnnDiary o"
                    + " WHERE o.technicianRegrequestUserId =:#{#idUserId}"
                    + " AND("
                    + " :#{#epochStart} <= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) > o.visitDateEpochSec"
                    + " OR"
                    + " :#{#epochStart} >= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) <= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + " OR"
                    + " :#{#epochStart} < o.visitDateEpochSec + o.visitDurationMinutes*60 AND :#{#epochStart} + ((:#{#durationMins})*60) >= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + ")"
            ,nativeQuery = false
    )
    public Long countTaVisits(@Param("idUserId") Long idUserId, @Param("epochStart") Long epochStart, @Param("durationMins") Long durationMins);

    @Query(
            value = "SELECT o.id FROM SpPtlVCompTaAnnDiary o"
                    + " WHERE o.technicianRegrequestUserId =:#{#idUserId}"
                    + " AND("
                    + " :#{#epochStart} <= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) > o.visitDateEpochSec"
                    + " OR"
                    + " :#{#epochStart} >= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) <= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + " OR"
                    + " :#{#epochStart} < o.visitDateEpochSec + o.visitDurationMinutes*60 AND :#{#epochStart} + ((:#{#durationMins})*60) >= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + ")"
            ,nativeQuery = false
    )
    public List<Long> taVisitConflict(@Param("idUserId") Long idUserId, @Param("epochStart") Long epochStart, @Param("durationMins") Long durationMins);

    /*@Query(
            value = "SELECT SP_PTL_COMP_TA_ANN.ID"
            + " FROM SP_PTL_V_COMP_TA_ANN_DIARY INNER JOIN SP_PTL_COMP_TA_ANN"
            + " ON SP_PTL_V_COMP_TA_ANN_DIARY.COMP_TA_ANN_ID = SP_PTL_COMP_TA_ANN.ID"
            + " WHERE SP_PTL_V_COMP_TA_ANN_DIARY.TECHNICIAN_REGREQUEST_USER_ID =?1 AND ("
            + " ?2 <= SP_PTL_V_COMP_TA_ANN_DIARY.VISIT_DATE_EPOCH_SEC AND ?2 + ((?3)*60) > SP_PTL_V_COMP_TA_ANN_DIARY.VISIT_DATE_EPOCH_SEC"
            + " OR"
            + " ?2 >= SP_PTL_V_COMP_TA_ANN_DIARY.VISIT_DATE_EPOCH_SEC AND ?2 + ((?3)*60) <= SP_PTL_V_COMP_TA_ANN_DIARY.VISIT_DATE_EPOCH_SEC + SP_PTL_V_COMP_TA_ANN_DIARY.VISIT_DURATION_MINUTES*60"
            + " OR"
            + " ?2 < SP_PTL_V_COMP_TA_ANN_DIARY.VISIT_DATE_EPOCH_SEC + SP_PTL_V_COMP_TA_ANN_DIARY.VISIT_DURATION_MINUTES*60 AND ?2 + ((?3)*60) >= SP_PTL_V_COMP_TA_ANN_DIARY.VISIT_DATE_EPOCH_SEC + SP_PTL_V_COMP_TA_ANN_DIARY.VISIT_DURATION_MINUTES*60"
            + ") AND SP_PTL_COMP_TA_ANN.REQ_STATUS in (5,6)"
            ,nativeQuery = true
    )
    public List<Long> taVisitConflict(Long idUserId, Long epochStart, Long durationMins);*/


    @Query(
            value = "SELECT o.id FROM SpPtlVCompTaAnnDiary o"
                    + " WHERE o.technicianRegrequestUserId =:#{#idUserId}"
                    + " AND("
                    + " :#{#epochStart} <= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) > o.visitDateEpochSec"
                    + " OR"
                    + " :#{#epochStart} >= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) <= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + " OR"
                    + " :#{#epochStart} < o.visitDateEpochSec + o.visitDurationMinutes*60 AND :#{#epochStart} + ((:#{#durationMins})*60) >= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + ")"
                    + " AND"
                    + "  o.compTaAnnId != :#{#idOfAnn} "
            ,nativeQuery = false
    )
    public List<Long> taVisitConflictNotOne(@Param("idUserId") Long idUserId, @Param("epochStart") Long epochStart, @Param("durationMins") Long durationMins, @Param("idOfAnn") Long idOfAnn);

    @Query(
            value = "SELECT sum(o.visitDurationMinutes) FROM SpPtlVCompTaAnnDiary o"
                    + " WHERE o.compTaAnnId =:#{#taAnnId}"
            ,nativeQuery = false
    )
    public Long sumOfTaAnnMinutes(@Param("taAnnId") Long taAnnId);
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
    public SpPtlVCompTaAnnDiary save(SpPtlVCompTaAnnDiary entity);
}