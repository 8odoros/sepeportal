package sepe.domain.general;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Evangelos on 8/3/2015.
 */
@Cacheable
@Entity
@Table(name = "SP_PTL_NOTIFICATIONS_ACCOUNT", schema = "SP_PTL", catalog = "")
public class TNotificationsAccountEntity {
    private Long id;
    private Long docId;
    private Long attDocId;
    private String title;
    private String message;
    private String sender;
    private Long priority;
    private Long viewed;
    private Long accountId;
    private Timestamp dateTime;


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
    @Column(name = "DOC_ID")
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    @Basic
    @Column(name = "ATT_DOC_ID")
    public Long getAttDocId() {
        return attDocId;
    }

    public void setAttDocId(Long attDocId) {
        this.attDocId = attDocId;
    }

    @Basic
    @Column(name = "ACCOUNT_ID")
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "SENDER")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "PRIORITY")
    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "DATE_TIME")
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "VIEWED")
    public Long getViewed() {
        return viewed;
    }

    public void setViewed(Long viewed) {
        this.viewed = viewed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TNotificationsAccountEntity that = (TNotificationsAccountEntity) o;

        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (attDocId != null ? !attDocId.equals(that.attDocId) : that.attDocId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (viewed != null ? !viewed.equals(that.viewed) : that.viewed != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (attDocId != null ? attDocId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (viewed != null ? viewed.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }
}
