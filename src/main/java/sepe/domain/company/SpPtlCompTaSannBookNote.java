package sepe.domain.company;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/13/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_TA_SANN_BOOK_NOTE", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaSannBookNote {
    private Long id;
    private Long companyId;
    //private Long compTaSannBookId;
    private Integer read;
    private Timestamp readDate;
    private String notes;
    private Timestamp dateCreated;
    private Long compTaSannId;

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
    @Column(name = "COMPANY_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /*
    @Basic
    @Column(name = "COMP_TA_SANN_BOOK_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompTaSannBookId() {
        return compTaSannBookId;
    }

    public void setCompTaSannBookId(Long compTaSannBookId) {
        this.compTaSannBookId = compTaSannBookId;
    }
*/

    @Basic
    @Column(name = "READ", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    @Basic
    @Column(name = "READ_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getReadDate() {
        return readDate;
    }

    public void setReadDate(Timestamp readDate) {
        this.readDate = readDate;
    }

    @Basic
    @Column(name = "NOTES", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "DATE_CREATED", nullable = true, insertable = true, updatable = true)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "COMP_TA_SANN_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompTaSannId() {
        return compTaSannId;
    }

    public void setCompTaSannId(Long compTaSannId) {
        this.compTaSannId = compTaSannId;
    }

    private String authorName;

    @Basic
    @Column(name = "AUTHOR_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompTaSannBook compTaSannBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_TA_SANN_BOOK_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompTaSannBook getCompTaSannBook() {
        return this.compTaSannBook;
    }

    public void setCompTaSannBook(SpPtlCompTaSannBook compTaSannBook) {
        this.compTaSannBook = compTaSannBook;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaSannBookNote that = (SpPtlCompTaSannBookNote) o;

        //if (compTaSannBookId != that.compTaSannBookId) return false;
        if (companyId != that.companyId) return false;
        if (id != that.id) return false;
        if (compTaSannId != null ? !compTaSannId.equals(that.compTaSannId) : that.compTaSannId != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (read != null ? !read.equals(that.read) : that.read != null) return false;
        if (readDate != null ? !readDate.equals(that.readDate) : that.readDate != null) return false;
        if (authorName != null ? !authorName.equals(that.authorName) : that.authorName != null) return false;

        return true;
    }

    /*@Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        //result = 31 * result + (int) (compTaSannBookId ^ (compTaSannBookId >>> 32));
        result = 31 * result + (read != null ? read.hashCode() : 0);
        result = 31 * result + (readDate != null ? readDate.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (compTaSannId != null ? compTaSannId.hashCode() : 0);
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        return result;
    }*/
}
