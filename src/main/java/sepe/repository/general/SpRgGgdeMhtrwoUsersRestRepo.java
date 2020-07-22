package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sepe.domain.general.SpRgGgdeMhtrwoUsers;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Marios on 1/25/2016.
 */
@Repository
public interface SpRgGgdeMhtrwoUsersRestRepo extends CrudRepository<SpRgGgdeMhtrwoUsers, Long> {

    @Override
    @RestResource(exported = false)
    public List<SpRgGgdeMhtrwoUsers> findAll();

    @Override
    @RestResource(exported = false)
    public long count();

    @Override
    @RestResource(exported = false)
    public void delete(Long id);


    @Override
    @RestResource(exported = true)
    public SpRgGgdeMhtrwoUsers findOne(Long id);

    @Query(
            value = "SELECT o FROM SpRgGgdeMhtrwoUsers o "
                    + "WHERE  o.seqId=:seqId"
    )
    @Nullable
    public SpRgGgdeMhtrwoUsers findBySeqId( @Param("seqId") Integer seqId);

}