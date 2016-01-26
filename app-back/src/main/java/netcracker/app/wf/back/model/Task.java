
package netcracker.app.wf.back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "tasks",  catalog = "scheduler_db")
public class Task {
    
    private int id;
    private String title;
    private String description;
    @JsonIgnore
    private Date notificationDate;

    @JsonIgnore
    private User user;

    @Id
    @Column(name = "id" , insertable = false, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "notification_date")
    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    @ManyToOne
    @JoinColumn(name ="user_id" )
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        if (user != null) {
            this.user = user;
        }
    }
}
