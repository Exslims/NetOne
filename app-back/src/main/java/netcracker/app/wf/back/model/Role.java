package netcracker.app.wf.back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "roles", catalog = "scheduler_db")
public class Role {

    private int id;
    private String name;

    @JsonIgnore
    private User user;

    @Id
    @Column(name = "id", insertable = false , updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name ="user_id" )
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user != null)
            this.user = user;
    }
}
