package sepe.repository.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sepe.domain.company.TEmployerBranchIKAComp;

import javax.annotation.Nullable;

public interface TEmployerBranchIKACompRepo extends CrudRepository<TEmployerBranchIKAComp, String> {

    /*
        Called from CurrentUserDetailsService to retrieve all the branch ids
        for the current company during login
     */
    @Query(
            value = "SELECT o from TEmployerBranchIKAComp o"
                    + " WHERE o.rgEmpEmployerId = ?1",
            nativeQuery = false
    )
    @Nullable
    public TEmployerBranchIKAComp findCompBranchesByCompanyAme(String ame);

}