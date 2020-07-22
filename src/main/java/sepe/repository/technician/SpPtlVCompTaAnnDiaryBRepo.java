package sepe.repository.technician;

        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.PagingAndSortingRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;
        import org.springframework.data.rest.core.annotation.RestResource;
        import sepe.domain.technician.SpPtlVCompTaAnnDiary;
        import sepe.domain.technician.SpPtlVCompTaAnnDiaryB;

        import javax.annotation.Nullable;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.LinkedList;
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
@RepositoryRestResource(collectionResourceRel = "vCompTaAnnDiaryB", path = "/vCompTaAnnDiaryB")
public interface SpPtlVCompTaAnnDiaryBRepo extends PagingAndSortingRepository<SpPtlVCompTaAnnDiaryB, Long> {

    /*
    * IE requests all his program [for all announcements/companies]
    */
    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnnDiaryB o"
                    + " WHERE o.technicianRegrequestUserId =?#{principal.userId}"
            //,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompTaAnnDiaryB> findAll(Pageable pageable);

    /*
    * IE requests his program for a specific announcement
    */
    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnnDiaryB o"
                    + " WHERE o.technicianRegrequestUserId =?#{principal.userId}"
                    + " AND o.compTaAnnId =:#{#compTaAnnId}"
            //,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompTaAnnDiaryB> findByCompTaAnnId(Pageable pageable, @Param("compTaAnnId") Long compTaAnnId);


    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnnDiaryB o"
                    + " WHERE o.technicianRegrequestUserId =?#{principal.userId}"
                    + " AND o.id =:#{#id}"
            //,nativeQuery = false
    )
    @Nullable
    public SpPtlVCompTaAnnDiaryB findOne(@Param("id") Long id);


    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnnDiaryB o"
                    + " WHERE o.technicianRegrequestUserId =?#{principal.userId}"
                    + " AND o.visitMonth =:#{#month} AND o.visitYear =:#{#year}"
            //,nativeQuery = false
    )
    @Nullable
    public List<SpPtlVCompTaAnnDiaryB> findByMonthAndYear(Pageable pageable, @Param("month") Integer month, @Param("year") Integer year);

    /*
    * Epoch is calculated in seconds.
    *
    * ,(ROUND((TO_DATE(to_char(VISIT_DATE,'DD/MM/YYYY HH24:MI'),'DD/MM/YYYY HH24:MI') - TO_DATE('01/01/1970 00:00','DD/MM/YYYY HH24:MI')) * 60 * 60 * 24)) AS VISIT_DATE_EPOCH_SEC
    *
    */

    /*@Query(
            value = "SELECT o FROM SpPtlVCompTaAnnDiaryB o, SpPtlCompTaAnnDiaryEntry b, SpPtlCompTaAnn c WHERE b.id = o.id AND c.id = o.compTaAnnId AND c.reqStatus = 6 AND b.isSubmitted = 2 AND o.visitDateEpochSec >= :#{#dateStart} AND o.visitDateEpochSec <= :#{#dateEnd} ORDER BY o.visitDate ASC"
            ,nativeQuery = false
    )
    public List<SpPtlVCompTaAnnDiaryB> taVisitConflict1(@Param("dateStart") Long dateStart, @Param("dateEnd") Long dateEnd);

    @Query(
            value = "SELECT o.visitDateEpochSec FROM SpPtlVCompTaAnnDiaryB o, SpPtlCompTaAnnDiaryEntry b, SpPtlCompTaAnn c WHERE b.id = o.id AND c.id = o.compTaAnnId AND c.reqStatus = 6 AND b.isSubmitted = 2 AND o.visitDateEpochSec >= :#{#dateStart} AND o.visitDateEpochSec <= :#{#dateEnd} ORDER BY o.visitDate ASC"
            ,nativeQuery = false
    )
    public List<Long> taVisitConflict2(@Param("dateStart") Long dateStart, @Param("dateEnd") Long dateEnd);*/

    @Query(
            value = "SELECT o.technicianRegrequestUserId FROM SpPtlVCompTaAnnDiaryB o WHERE o.visitDateEpochSec >= :#{#dateStart} AND o.visitDateEpochSec <= :#{#dateEnd} ORDER BY o.visitDate ASC"
            ,nativeQuery = false
    )
    public List<Long> taVisitConflict1(@Param("dateStart") Long dateStart, @Param("dateEnd") Long dateEnd);

    @Query(
            value = "SELECT o.compTaAnnId FROM SpPtlVCompTaAnnDiaryB o WHERE o.visitDateEpochSec >= :#{#dateStart} AND o.visitDateEpochSec <= :#{#dateEnd} ORDER BY o.visitDate ASC"
            ,nativeQuery = false
    )
    public List<Long> taVisitConflict2(@Param("dateStart") Long dateStart, @Param("dateEnd") Long dateEnd);

    @Query(
            value = "SELECT o.visitDurationMinutes FROM SpPtlVCompTaAnnDiaryB o WHERE o.visitDateEpochSec >= :#{#dateStart} AND o.visitDateEpochSec <= :#{#dateEnd} ORDER BY o.visitDate ASC"
            ,nativeQuery = false
    )
    public List<Integer> taVisitConflict3(@Param("dateStart") Long dateStart, @Param("dateEnd") Long dateEnd);

    @Query(
            value = "SELECT o.visitDate FROM SpPtlVCompTaAnnDiaryB o WHERE o.visitDateEpochSec >= :#{#dateStart} AND o.visitDateEpochSec <= :#{#dateEnd} ORDER BY o.visitDate ASC"
            ,nativeQuery = false
    )
    public List<Date> taVisitConflict4(@Param("dateStart") Long dateStart, @Param("dateEnd") Long dateEnd);

    @Query(
            value = "SELECT o.visitTime FROM SpPtlVCompTaAnnDiaryB o WHERE o.visitDateEpochSec >= :#{#dateStart} AND o.visitDateEpochSec <= :#{#dateEnd} ORDER BY o.visitDate ASC"
            ,nativeQuery = false
    )
    public List<String> taVisitConflict5(@Param("dateStart") Long dateStart, @Param("dateEnd") Long dateEnd);


    /*@Query(
            value = "SELECT o.id FROM SpPtlVCompTaAnnDiaryB o"
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
    public List<Long> taVisitConflict4(@Param("idUserId") Long idUserId, @Param("epochStart") Long epochStart, @Param("durationMins") Long durationMins);*/

    /*@Query(
            value = "SELECT o.id FROM SpPtlVCompTaAnnDiaryB o, SpPtlCompTaAnnDiaryEntry b"
                    + " WHERE o.technicianRegrequestUserId =:#{#idUserId}"
                    + " AND("
                    + " b.id = o.id)"
                    + " AND("
                    + " b.isSubmitted IS NULL OR b.isSubmitted = 2 )"
                    + " AND("
                    + " :#{#epochStart} <= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) > o.visitDateEpochSec"
                    + " OR"
                    + " :#{#epochStart} >= o.visitDateEpochSec AND :#{#epochStart} + ((:#{#durationMins})*60) <= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + " OR"
                    + " :#{#epochStart} < o.visitDateEpochSec + o.visitDurationMinutes*60 AND :#{#epochStart} + ((:#{#durationMins})*60) >= o.visitDateEpochSec + o.visitDurationMinutes*60"
                    + ")"
            ,nativeQuery = false
    )
    public List<Long> taVisitConflict5(@Param("idUserId") Long idUserId, @Param("epochStart") Long epochStart, @Param("durationMins") Long durationMins);*/

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
    public SpPtlVCompTaAnnDiaryB save(SpPtlVCompTaAnnDiaryB entity);
}