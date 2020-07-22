package sepe.repository.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sepe.domain.company.TCompany;
import sepe.domain.company.TEmployerBranchIKA;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

//http://localhost:7001/TCompany/search/findBySession
@RepositoryRestResource(collectionResourceRel = "TCompany", path = "/TCompany")
public interface CompanyRepository extends CrudRepository<TCompany, Long> {

    @Nullable
    public TCompany findByUserId(
            @Param("userId") @Nonnull Long userId
    );

    @Nullable
    public TCompany findByAfm(
            @Param("afm") @Nonnull String afm
    );

    /*@Nullable
    public TCompany findByAfmAndAme(
            @Param("afm") @Nonnull String afm,
            @Param("ame") @Nonnull String ame
    );*/

    @Query(
            value = "SELECT o from TCompany o"
                    + " WHERE o.afm =:#{#afm}"
                    + " AND o.ame =:#{#ame}",
            nativeQuery = false
    )
    @Nullable
    public TCompany findTCompanyByAfmAndAme(@Param("afm") String afm, @Param("ame") String ame);

    @Override
    public TCompany findOne(@Param("id") @Nonnull  Long id);
}
