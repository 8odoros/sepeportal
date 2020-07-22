package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 2/13/2015.
 */
@Entity
@Table(name = "SP_PTL_V_DOCUMENT_INDEX", schema = "SP_PTL", catalog = "")
public class DocumentV {
    private Long userId;
    private Long documentId;
    private Long docId;

    @Id
    @GeneratedValue
    @Column(name = "DOCUMENTID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Basic
    @Column(name = "DOCID", nullable = true, insertable = true, updatable = true, length = 100)
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {this.docId = docId;}


    @Basic
    @Column(name = "USERID", nullable = true, insertable = true, updatable = true, length = 100)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}