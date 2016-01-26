package netcracker.app.wf.back.model;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", catalog = "scheduler_db")
public class User {

    private int id;
    private String login;
    private String password;
    private String name;
    private String email;
    private String address;
    private String country;
    private String javaSkills;
    private Set<Task> tasks;
    private Set<Role> roles;

    @Id
    @Column(name = "id" , insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @Column(name = "java_skills")
    public String getJavaSkills() {
        return javaSkills;
    }

    public void setJavaSkills(String javaSkills) {
        this.javaSkills = javaSkills;
    }

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        if(tasks != null) {
            this.tasks = new HashSet<>();
            try {
                for (Task task : this.tasks)
                    task.setUser(this);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.tasks = tasks;
    }

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        if (roles != null) {
            try {
                this.roles = new HashSet<>();
                for (Role role : this.roles)
                    role.setUser(this);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (this.roles == null)
            this.roles = new HashSet<>();
        this.roles.add(role);
    }

    public void addTask(Task task) {
        if (this.tasks == null)
            this.tasks = new HashSet<>();
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
