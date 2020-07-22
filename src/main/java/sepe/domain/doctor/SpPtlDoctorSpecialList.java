package sepe.domain.doctor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by dimitrisf on 20/2/2020.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_DOCTOR_SPECIAL_LIST", schema = "SP_PTL", catalog = "")
public class SpPtlDoctorSpecialList {
    private Long id;

    @Id
    @GeneratedValue
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String surname;

    @Basic
    @javax.persistence.Column(name = "SURNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String fathername;

    @Basic
    @javax.persistence.Column(name = "FATHERNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    private String speciality;

    @Basic
    @javax.persistence.Column(name = "SPECIALITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    private String medicalAssoc;

    @Basic
    @javax.persistence.Column(name = "MEDICAL_ASSOC", nullable = true, insertable = true, updatable = true, length = 100)
    public String getMedicalAssoc() {
        return medicalAssoc;
    }

    public void setMedicalAssoc(String medicalAssoc) {
        this.medicalAssoc = medicalAssoc;
    }

    private String am;

    @Basic
    @javax.persistence.Column(name = "AM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    private String afm;

    @Basic
    @javax.persistence.Column(name = "AFM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    private String amka;

    @Basic
    @javax.persistence.Column(name = "AMKA", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlDoctorSpecialList that = (SpPtlDoctorSpecialList) o;

        if (id != that.id) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (fathername != null ? !fathername.equals(that.fathername) : that.fathername != null) return false;
        if (speciality != null ? !speciality.equals(that.speciality) : that.speciality != null) return false;
        if (medicalAssoc != null ? !medicalAssoc.equals(that.medicalAssoc) : that.medicalAssoc != null) return false;
        if (am != null ? !am.equals(that.am) : that.am != null) return false;
        if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
        if (amka != null ? !amka.equals(that.amka) : that.amka != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fathername != null ? fathername.hashCode() : 0);
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + (medicalAssoc != null ? medicalAssoc.hashCode() : 0);
        result = 31 * result + (am != null ? am.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        result = 31 * result + (amka != null ? amka.hashCode() : 0);
        return result;
    }
}

