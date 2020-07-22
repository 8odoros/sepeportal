package sepe.repository.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.TEmployerBranchIKA;

import javax.annotation.Nullable;
import java.util.List;

//http://localhost:7001/TEmployerBranchIKA/search/findByCompanyAme?ame=9309574198
//http://localhost:7001/TEmployerBranchIKA/search/findByCompanyAme?ame=2222222222
// http://localhost:7001/TEmployerBranchIKA/search/findByCompanyAme?ame=AMEIKA134
//http://localhost:7001/TEmployerBranchIKA/search/findByCompanyAfm?afm=000000988
//http://localhost:7001/TEmployerBranchIKA/search/findByCompanyAfm?afm=111111111
//http://localhost:7001/TEmployerBranchIKA/search/findByCompanyAfm?afm=222222222

//http://localhost:7001/TEmployerBranchIKA/search/findBySession
@RepositoryRestResource(collectionResourceRel = "TEmployerBranchIKA", path = "/TEmployerBranchIKA")
public interface TEmployerBranchIKARepo extends CrudRepository<TEmployerBranchIKA, String> {

    /*
        @Query(
                value = "SELECT"
                        + " RG_EBR_EMPLOYER_ID ||'['|| RG_EBR_BRANCH_ID ||']' AS ID,"
                        + " RG_EBR_EMPLOYER_ID,"
                        + " RG_EBR_EMP_SEPE_ID,"
                        + " RG_EBR_BRANCH_ID,"
                        + " RG_EBR_ADDRESS_STREET || ' ' || RG_EBR_NO as RG_EBR_ADDRESS_STREET,"
                        + " RG_EBR_ZIP_CODE,"
                        + " RG_EBR_KALLIKRATIS,"
                        + " RG_EBR_PHONE_NUMBER,"
                        + " RG_EBR_SEC_STAKOD_1,"
                        + " RG_EBR_SEC_STAKOD_2,"
                        + " RG_EBR_SEC_STAKOD_3,"
                        + " RG_EBR_SEC_STAKOD_4,"
                        + " RG_EBR_SEC_STAKOD_5,"
                        + " RG_EMP_NAME,"
                        + " RG_EMP_FULLNAME,"
                        + " RG_EMP_TAXATION_OFFICE_CODE,"
                        + " RG_EMP_TAXATION_NUMBER,"
                        + " RG_EMP_LEGAL_CATEGORY_CODE,"
                        + " RG_EMP_PHYSICAL_FLAG,"
                        + " RG_EMP_DISCREET_TITLE"
                        + " FROM RG_VW_EMP_BRANCHES@SP_REG_LINK  b Join RG_VW_EMPLOYER@SP_REG_LINK c ON b.RG_EBR_EMPLOYER_ID=c.RG_EMP_EMPLOYER_ID"
                        + " WHERE c.RG_EMP_TAXATION_NUMBER =:#{#afm}",
                nativeQuery = true
        )
        @Nullable
        public List<TEmployerBranchIKA> findByCompanyAfm(@Param("afm") String afm);
    */
    @Query(
            value = "SELECT o from TEmployerBranchIKA o"
                    + " WHERE o.rgEbrTaxationNumber =:#{#afm}",
            nativeQuery = false
    )
    @Nullable
    public List<TEmployerBranchIKA> findByCompanyAfm(@Param("afm") String afm);


    /*
        @Query(
                value = "SELECT"
                        + " RG_EBR_EMPLOYER_ID ||'['|| RG_EBR_BRANCH_ID ||']' AS ID,"
                        + " RG_EBR_EMPLOYER_ID,"
                        + " RG_EBR_EMP_SEPE_ID,"
                        + " RG_EBR_BRANCH_ID,"
                        + " RG_EBR_ADDRESS_STREET || ' ' || RG_EBR_NO as RG_EBR_ADDRESS_STREET,"
                        + " RG_EBR_ZIP_CODE,"
                        + " RG_EBR_KALLIKRATIS,"
                        + " RG_EBR_PHONE_NUMBER,"
                        + " RG_EBR_SEC_STAKOD_1,"
                        + " RG_EBR_SEC_STAKOD_2,"
                        + " RG_EBR_SEC_STAKOD_3,"
                        + " RG_EBR_SEC_STAKOD_4,"
                        + " RG_EBR_SEC_STAKOD_5,"
                        + " RG_EMP_NAME,"
                        + " RG_EMP_FULLNAME,"
                        + " RG_EMP_TAXATION_OFFICE_CODE,"
                        + " RG_EMP_TAXATION_NUMBER,"
                        + " RG_EMP_LEGAL_CATEGORY_CODE,"
                        + " RG_EMP_PHYSICAL_FLAG,"
                        + " RG_EMP_DISCREET_TITLE"
                        + " FROM RG_VW_EMP_BRANCHES@SP_REG_LINK  b Join RG_VW_EMPLOYER@SP_REG_LINK c ON b.RG_EBR_EMPLOYER_ID=c.RG_EMP_EMPLOYER_ID"
                        + " WHERE c.RG_EMP_EMPLOYER_ID =:#{#ame}",
                nativeQuery = true
        )
        @Nullable
        public List<TEmployerBranchIKA> findByCompanyAme(@Param("ame") String ame);
    */
    @Query(
            value = "SELECT o from TEmployerBranchIKA o"
                    + " WHERE o.rgEmpEmployerId =:#{#ame}",
            nativeQuery = false
    )
    @Nullable
    public List<TEmployerBranchIKA> findByCompanyAme(@Param("ame") String ame);



    @Query(
/*
            value = "SELECT * from SP_PTL_V_BRANCH_IKA"
                    + " WHERE RG_EMP_TAXATION_NUMBER =?#{principal.company.afm}"
                    + " AND ("
                    + " ?#{principal.branchIds} LIKE '%,' || RG_EBR_BRANCH_ID || ',%'"
                    + " OR "
                    + " ?#{principal.branchIds} LIKE RG_EBR_BRANCH_ID  || ',%'"
                    + " OR "
                    + " ?#{principal.branchIds} LIKE '%,' || RG_EBR_BRANCH_ID "
                    + " OR "
                    + " ?#{principal.branchIds} LIKE RG_EBR_BRANCH_ID"
                    + " )",
            nativeQuery = true
*/
            /*value = "SELECT DISTINCT a FROM SpPtlCompanyAccident a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compEbrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3 AND p.compId=a.companyId ))"*/


            value = "SELECT DISTINCT o from TEmployerBranchIKA o, TCompanyUserPrivilages p, TUser u "
                    + " WHERE o.rgEbrTaxationNumber =?#{principal.company.afm}"
                    + " AND"
                    + " o.rgEbrEmployerId =?#{principal.company.ame}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , o.rgEbrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3 AND o.rgEbrTaxationNumber =?#{principal.company.afm} AND o.rgEbrEmployerId =?#{principal.company.ame} ))  order by o.rgEbrBranchId ",
                    /*
                    " UPPER(?#{principal.branchIds}) LIKE CONCAT('%,' , o.rgEbrBranchId , ',%')

                    + " AND ("
                    + " UPPER(?#{principal.branchIds} LIKE CONCAT('%,' , o.rgEbrBranchId , ',%')"
                    + " OR "
                    + " UPPER(?#{principal.branchIds} LIKE CONCAT(o.rgEbrBranchId  , ',%')"
                    + " OR "
                    + " UPPER(?#{principal.branchIds} LIKE CONCAT('%,' , o.rgEbrBranchId )"
                    + " OR "
                    + " UPPER(?#{principal.branchIds} LIKE o.rgEbrBranchId "
                    + " )",
                    */
            nativeQuery = false
    )
    @Nullable
    public List<TEmployerBranchIKA> findBySession();

    @Query(
            value = "SELECT DISTINCT o from TEmployerBranchIKA o, TCompanyUserPrivilages p, TUser u "
                    + " WHERE o.rgEbrTaxationNumber =?#{principal.company.afm}"
                    + " AND"
                    + " o.rgEbrEmployerId =?#{principal.company.ame}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND "
                    + " UPPER(o.rgEbrAddressStreet) like UPPER(CONCAT('%',:#{#descr},'%'))"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , o.rgEbrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3 AND o.rgEbrTaxationNumber =?#{principal.company.afm} AND o.rgEbrEmployerId =?#{principal.company.ame} ))  order by o.rgEbrBranchId ",
            nativeQuery = false
    )
    @Nullable
    public List<TEmployerBranchIKA> findByDescr(@Param("descr") String descr);

    /*
        @Query(
                value = "SELECT"
                        + " RG_EBR_EMPLOYER_ID ||'['|| RG_EBR_BRANCH_ID ||']' AS ID,"
                        + " RG_EBR_EMPLOYER_ID,"
                        + " RG_EBR_EMP_SEPE_ID,"
                        + " RG_EBR_BRANCH_ID,"
                        + " RG_EBR_ADDRESS_STREET || ' ' || RG_EBR_NO as RG_EBR_ADDRESS_STREET,"
                        + " RG_EBR_ZIP_CODE,"
                        + " RG_EBR_KALLIKRATIS,"
                        + " RG_EBR_PHONE_NUMBER,"
                        + " RG_EBR_SEC_STAKOD_1,"
                        + " RG_EBR_SEC_STAKOD_2,"
                        + " RG_EBR_SEC_STAKOD_3,"
                        + " RG_EBR_SEC_STAKOD_4,"
                        + " RG_EBR_SEC_STAKOD_5,"
                        + " RG_EMP_NAME,"
                        + " RG_EMP_FULLNAME,"
                        + " RG_EMP_TAXATION_OFFICE_CODE,"
                        + " RG_EMP_TAXATION_NUMBER,"
                        + " RG_EMP_LEGAL_CATEGORY_CODE,"
                        + " RG_EMP_PHYSICAL_FLAG,"
                        + " RG_EMP_DISCREET_TITLE"
                        + " FROM RG_VW_EMP_BRANCHES@SP_REG_LINK  b Join RG_VW_EMPLOYER@SP_REG_LINK c ON b.RG_EBR_EMPLOYER_ID=c.RG_EMP_EMPLOYER_ID"
                        + " WHERE c.RG_EMP_EMPLOYER_ID =:#{#ame}"
                        + " AND b.RG_EBR_MAIN_BRANCH_FLAG = 1",
                nativeQuery = true
        )
        @Nullable
        public TEmployerBranchIKA findCompanyMainBranchByAme(@Param("ame") String ame);
        */
    @Query(
            value = "SELECT o from TEmployerBranchIKA o"
                    + " WHERE o.rgEmpEmployerId =:#{#ame}"
                    + " AND o.rgEbrMainBranchFlag = 1",
            nativeQuery = false
    )
    @Nullable
    public TEmployerBranchIKA findCompanyMainBranchByAme(@Param("ame") String ame);

    @Query(
            value = "SELECT o from TEmployerBranchIKA o"
                    + " WHERE o.rgEbrTaxationNumber =:#{#afm}"
                    + " AND o.rgEbrMainBranchFlag = 1",
            nativeQuery = false
    )
    @Nullable
    public TEmployerBranchIKA findCompanyMainBranchByAfm(@Param("afm") String afm);

    @Query(
            value = "SELECT o from TEmployerBranchIKA o"
                    + " WHERE o.rgEbrTaxationNumber =:#{#afm}"
                    + " AND o.rgEmpEmployerId =:#{#ame}"
                    + " AND o.rgEbrMainBranchFlag = 1",
            nativeQuery = false
    )
    @Nullable
    public TEmployerBranchIKA findCompanyMainBranchByAfmAndAme(@Param("afm") String afm, @Param("ame") String ame);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    @Override
    @RestResource(exported = false)
    public TEmployerBranchIKA findOne(String id);

    @Override
    @RestResource(exported = false)
    public List<TEmployerBranchIKA> findAll();

    @Override
    @RestResource(exported = false)
    public long count();

    @Override
    @RestResource(exported = false)
    public void delete(String id);

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
    public boolean exists(String id);

//@Override
//@RestResource(exported = false)
//public List<OBJ_TYPE> findAll(List<ID_TYPE> ids);

//@Override
//@RestResource(exported = false)
//public List<OBJ_TYPE> save(List<OBJ_TYPE> entities);

    @Override
    @RestResource(exported = false)
    public TEmployerBranchIKA save(TEmployerBranchIKA entity);

}