package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/16/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_TA_ANN_BOOK_NOTE", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaAnnBookNote {
    private Long id;
    private Long companyId;
    //private Long compTaAnnBookId;
    private Integer read;
    private Timestamp readDate;
    private String notes;
    private Timestamp dateCreated;
    //private Long compTaAnnId;
    private Long authorUserId;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /*
    @Basic
    @Column(name = "COMP_TA_ANN_BOOK_ID")
    public Long getCompTaAnnBookId() {
        return compTaAnnBookId;
    }

    public void setCompTaAnnBookId(Long compTaAnnBookId) {
        this.compTaAnnBookId = compTaAnnBookId;
    }
    */

    @Basic
    @Column(name = "READ")
    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    @Basic
    @Column(name = "READ_DATE")
    public Timestamp getReadDate() {
        return readDate;
    }

    public void setReadDate(Timestamp readDate) {
        this.readDate = readDate;
    }

    @Basic
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "DATE_CREATED")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    /*
    @Basic
    @Column(name = "COMP_TA_ANN_ID")
    public Long getCompTaAnnId() {
        return compTaAnnId;
    }

    public void setCompTaAnnId(Long compTaAnnId) {
        this.compTaAnnId = compTaAnnId;
    }
*/
    private String authorName;

    @Basic
    @Column(name = "AUTHOR_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Basic
    @Column(name = "AUTHOR_USER_ID", nullable = true, insertable = true, updatable = true, length = 19)
    public Long getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(Long authorUserId) {
        this.authorUserId = authorUserId;
    }
    /////////////////////////////////////////////////
    // /*
    private SpPtlCompTaAnnBook compTaAnnBook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_TA_ANN_BOOK_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompTaAnnBook getCompTaAnnBook() {
        return this.compTaAnnBook;
    }

    public void setCompTaAnnBook(SpPtlCompTaAnnBook compTaAnnBook) {
        this.compTaAnnBook = compTaAnnBook;
    }
    // */
    /////////////////////////////////////////////////

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompTaAnn compTaAnn;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="COMP_TA_ANN_ID", nullable=false)
    public SpPtlCompTaAnn getCompTaAnn() {
        return this.compTaAnn;
    }

    public void setCompTaAnn(SpPtlCompTaAnn compTaAnn) {
        this.compTaAnn = compTaAnn;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaAnnBookNote that = (SpPtlCompTaAnnBookNote) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        //if (compTaAnnBookId != that.compTaAnnBookId) return false;
        if (read != null ? !read.equals(that.read) : that.read != null) return false;
        if (readDate != null ? !readDate.equals(that.readDate) : that.readDate != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (authorName != null ? !authorName.equals(that.authorName) : that.authorName != null) return false;
        if (authorUserId != that.authorUserId) return false;
        //if (compTaAnnId != null ? !compTaAnnId.equals(that.compTaAnnId) : that.compTaAnnId != null) return false;

        return true;
    }

    /*@Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        //result = 31 * result + (int) (compTaAnnBookId ^ (compTaAnnBookId >>> 32));
        result = 31 * result + (read != null ? read.hashCode() : 0);
        result = 31 * result + (readDate != null ? readDate.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        //result = 31 * result + (compTaAnnId != null ? compTaAnnId.hashCode() : 0);
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        return result;
    }*/
}
