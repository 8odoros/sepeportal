package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikos on 5/27/2015.
 */
//SP_RT_GEN_REQUEST@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_GEN_REQUEST", schema = "", catalog = "")
public class SpVwGenreq {
    private Long spGreqId;
    private String spGreqTitle;
    private String spGreqHelpText;
    private Long spGreqTemplDocId;
    private Long spGreqActive;
    private Long spGreqHelplDocId;
    private Long spGreqTe;
    private Long spGreqKe;
    private String spGreqUsertypes;
    private Long spGreqRequiredDoc;
    private Long spGreqConfirmText;

    @Id
    @GeneratedValue
    @Column(name = "SP_GREQ_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqId() {
        return spGreqId;
    }
    public void setSpGreqId(Long spGreqId) {
        this.spGreqId = spGreqId;
    }

    @Basic
    @Column(name = "SP_GREQ_TITLE", nullable = false, insertable = false, updatable = false, length = 150)
    public String getSpGreqTitle() {
        return spGreqTitle;
    }
    public void setSpGreqTitle(String spGreqTitle) {
        this.spGreqTitle = spGreqTitle;
    }

    @Basic
    @Column(name = "SP_GREQ_HELP_TEXT", nullable = false, insertable = false, updatable = false, length = 3000)
    public String getSpGreqHelpText() {
        return spGreqHelpText;
    }
    public void setSpGreqHelpText(String spGreqHelpText) {
        this.spGreqHelpText = spGreqHelpText;
    }

    @Basic
    @Column(name = "SP_GREQ_TEMPL_DOC_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqTemplDocId() {
        return spGreqTemplDocId;
    }
    public void setSpGreqTemplDocId(Long spGreqTemplDocId) {
        this.spGreqTemplDocId = spGreqTemplDocId;
    }

    @Basic
    @Column(name = "SP_GREQ_ACTIVE", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqActive() {
        return spGreqActive;
    }
    public void setSpGreqActive(Long spGreqActive) {
        this.spGreqActive = spGreqActive;
    }

    @Basic
    @Column(name = "SP_GREQ_HELP_DOC_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqHelplDocId() {
        return spGreqHelplDocId;
    }
    public void setSpGreqHelplDocId(Long spGreqHelplDocId) {
        this.spGreqHelplDocId = spGreqHelplDocId;
    }

    @Basic
    @Column(name = "SP_GREQ_TE", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqTe() {
        return spGreqTe;
    }
    public void setSpGreqTe(Long spGreqTe) {
        this.spGreqTe = spGreqTe;
    }

    @Basic
    @Column(name = "SP_GREQ_KE", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqKe() {
        return spGreqKe;
    }
    public void setSpGreqKe(Long spGreqKe) {
        this.spGreqKe = spGreqKe;
    }

    @Basic
    @Column(name = "SP_GREQ_USERTYPES", nullable = false, insertable = false, updatable = false, length = 20)
    public String getSpGreqUsertypes() {
        return spGreqUsertypes;
    }
    public void setSpGreqUsertypes(String spGreqUsertypes) {
        this.spGreqUsertypes = spGreqUsertypes;
    }

    @Basic
    @Column(name = "SP_GREQ_REQUIRED_DOC", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqRequiredDoc() {
        return spGreqRequiredDoc;
    }
    public void setSpGreqRequiredDoc(Long spGreqRequiredDoc) {
        this.spGreqRequiredDoc = spGreqRequiredDoc;
    }

    @Basic
    @Column(name = "SP_GREQ_CONFIRM_TEXT", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqConfirmText() {
        return spGreqConfirmText;
    }
    public void setSpGreqConfirmText(Long spGreqConfirmText) {
        this.spGreqConfirmText = spGreqConfirmText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpVwGenreq obj = (SpVwGenreq) o;

        if (spGreqId != obj.spGreqId) return false;
        if (spGreqTitle != null ? !spGreqTitle.equals(obj.spGreqTitle) : obj.spGreqTitle != null) return false;
        if (spGreqHelpText != null ? !spGreqHelpText.equals(obj.spGreqHelpText) : obj.spGreqHelpText != null) return false;
        if (spGreqTemplDocId != null ? !spGreqTemplDocId.equals(obj.spGreqTemplDocId) : obj.spGreqTemplDocId != null) return false;
        if (spGreqActive != null ? !spGreqActive.equals(obj.spGreqActive) : obj.spGreqActive != null) return false;
        if (spGreqHelplDocId != null ? !spGreqHelplDocId.equals(obj.spGreqHelplDocId) : obj.spGreqHelplDocId != null) return false;
        if (spGreqTe != null ? !spGreqTe.equals(obj.spGreqTe) : obj.spGreqTe != null) return false;
        if (spGreqKe != null ? !spGreqKe.equals(obj.spGreqKe) : obj.spGreqKe != null) return false;
        if (spGreqUsertypes != null ? !spGreqUsertypes.equals(obj.spGreqUsertypes) : obj.spGreqUsertypes != null) return false;
        if (spGreqRequiredDoc != null ? !spGreqRequiredDoc.equals(obj.spGreqRequiredDoc) : obj.spGreqRequiredDoc != null) return false;
        if (spGreqConfirmText != null ? !spGreqConfirmText.equals(obj.spGreqConfirmText) : obj.spGreqConfirmText != null) return false;
        return true;
    }


    @Override
    public int hashCode() {
        int result = (int) (spGreqId ^ (spGreqId >>> 32));
        result = 31 * result + (spGreqTitle != null ? spGreqTitle.hashCode() : 0);
        result = 31 * result + (spGreqHelpText != null ? spGreqHelpText.hashCode() : 0);
        result = 31 * result + (spGreqTemplDocId != null ? spGreqTemplDocId.hashCode() : 0);
        result = 31 * result + (spGreqActive != null ? spGreqActive.hashCode() : 0);
        result = 31 * result + (spGreqHelplDocId != null ? spGreqHelplDocId.hashCode() : 0);
        result = 31 * result + (spGreqTe != null ? spGreqTe.hashCode() : 0);
        result = 31 * result + (spGreqKe != null ? spGreqKe.hashCode() : 0);
        result = 31 * result + (spGreqUsertypes != null ? spGreqUsertypes.hashCode() : 0);
        result = 31 * result + (spGreqRequiredDoc != null ? spGreqRequiredDoc.hashCode() : 0);
        result = 31 * result + (spGreqConfirmText != null ? spGreqConfirmText.hashCode() : 0);
        return result;
    }
}