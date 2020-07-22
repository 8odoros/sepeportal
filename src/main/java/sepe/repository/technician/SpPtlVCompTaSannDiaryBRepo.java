package sepe.repository.technician;

        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.PagingAndSortingRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;
        import org.springframework.data.rest.core.annotation.RestResource;
        import sepe.domain.technician.SpPtlVCompTaSannDiary;
        import sepe.domain.technician.SpPtlVCompTaSannDiaryB;

        import javax.annotation.Nullable;
        import java.util.List;

/**
 * Created by Nikos on 6/14/2015.
 */
// http://localhost:7001/vCompTaSannDiary
// http://localhost:7001/vCompTaSannDiary/search/findByCompTaSannId?compTaSannId=1350
// http://localhost:7001/vCompTaSannDiary/search/findOne?id=1353
// http://localhost:7001/vCompTaSannDiary/search/countTaVisits?idUserId=584&epochStart=1433934000&durationMins=1
// http://localhost:7001/vCompTaSannDiary/search/findByMonthAndYear?month=6&year=2015
// http://localhost:7001/vCompTaSannDiary/search/taVisitConflict?idUserId=584&epochStart=1433934000&durationMins=1
@RepositoryRestResource(collectionResourceRel = "vCompTaSannDiaryB", path = "/vCompTaSannDiaryB")
public interface SpPtlVCompTaSannDiaryBRepo extends PagingAndSortingRepository<SpPtlVCompTaSannDiaryB, Long> {

    /*
    * IE requests all his program [for all announcements/companies]
    */
    @Query(
            value = "SELECT o FROM SpPtlVCompTaSannDiaryB o"
                    + " WHERE o.taRegrequestUserId =?#{principal.userId}"
            //,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompTaSannDiaryB> findAll(Pageable pageable);

    /*
    * TA requests his program for a specific announcement
    */
    @Query(
            value = "SELECT o FROM SpPtlVCompTaSannDiaryB o"
                    + " WHERE o.taRegrequestUserId =?#{principal.userId}"
                    + " AND o.compTaSannId =:#{#compTaSannId}"
            //,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompTaSannDiaryB> findByCompTaSannId(Pageable pageable, @Param("compTaSannId") Long compTaSannId);


    @Query(
            value = "SELECT o FROM SpPtlVCompTaSannDiaryB o"
                    + " WHERE o.taRegrequestUserId =?#{principal.userId}"
                    + " AND o.id =:#{#id}"
            //,nativeQuery = false
    )
    @Nullable
    public SpPtlVCompTaSannDiaryB findOne(@Param("id") Long id);


    @Query(
            value = "SELECT o.id FROM SpPtlVCompTaSannDiaryB o"
                    + " WHERE o.taRegrequestUserId =:#{#idUserId}"
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
    public SpPtlVCompTaSannDiaryB save(SpPtlVCompTaSannDiaryB entity);
}