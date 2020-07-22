package sepe.repository.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.employee.TEmployee;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Transactional( propagation = Propagation.REQUIRES_NEW )
public interface EmployeeRepository extends CrudRepository<TEmployee, Long> {

    @Nullable
    public TEmployee findByUserId(
            @Nonnull Long userID
    );

    @Nullable
    public TEmployee findByAfm(
            @Nonnull String Afm
    );

/*    @Query(
            value = "SELECT o FROM EmployeeRepository o "
                    + "WHERE  o.afm=:afm"
    )
    @Nullable
    public EmployeeRepository findByAfm( @Param("afm") String afm);*/
}
