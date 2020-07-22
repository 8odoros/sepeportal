package sepe.domain.company;

import javax.persistence.*;

/**
 * Created by Nikos on 6/18/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_SHIP", schema = "SP_PTL", catalog = "")
public class SpPtlCompShip {
    private Long id;
    private Long companyId;
    private String shipCapacity;
    private String shipImo;
    private String shipName;
    private String shipType;


    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COMPANY_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }


    @Basic
    @Column(name = "SHIP_CAPACITY", nullable = true, insertable = true, updatable = true, length = 200)
    public String getShipCapacity() {
        return shipCapacity;
    }

    public void setShipCapacity(String shipCapacity) {
        this.shipCapacity = shipCapacity;
    }

    @Basic
    @Column(name = "SHIP_IMO", nullable = true, insertable = true, updatable = true, length = 200)
    public String getShipImo() {
        return shipImo;
    }

    public void setShipImo(String shipImo) {
        this.shipImo = shipImo;
    }

    @Basic
    @Column(name = "SHIP_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    @Basic
    @Column(name = "SHIP_TYPE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompShip that = (SpPtlCompShip) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (shipCapacity != null ? !shipCapacity.equals(that.shipCapacity) : that.shipCapacity != null) return false;
        if (shipImo != null ? !shipImo.equals(that.shipImo) : that.shipImo != null) return false;
        if (shipName != null ? !shipName.equals(that.shipName) : that.shipName != null) return false;
        if (shipType != null ? !shipType.equals(that.shipType) : that.shipType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (shipCapacity != null ? shipCapacity.hashCode() : 0);
        result = 31 * result + (shipImo != null ? shipImo.hashCode() : 0);
        result = 31 * result + (shipName != null ? shipName.hashCode() : 0);
        result = 31 * result + (shipType != null ? shipType.hashCode() : 0);
        return result;
    }
}
