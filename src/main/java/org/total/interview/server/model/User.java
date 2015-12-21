package org.total.interview.server.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@Table(
        name = "User",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userId"),
                @UniqueConstraint(columnNames = "userName")
        }
)
public class User implements Serializable {

    private long userId;
    private String userName;
    private String password;
    private Set<Role> roles;

    public User() {}

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.roles = new HashSet<Role>();
    }

    public User(long userId, String userName, String password, Set<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public User(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", unique = true, nullable = false)
    public long getUserId() {
        return userId;
    }

    @XmlElement
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "userName", unique = true, nullable = false)
    public String getUserName() {
        return userName;
    }

    @XmlElement
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Role",
            joinColumns = {
                    @JoinColumn(name = "userId", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "roleId", nullable = false)
            }
    )
    public Set<Role> getRoles() {
        if (this.roles == null) {
            this.roles = new HashSet<Role>();
        }
        return roles;
    }

    @XmlElementWrapper(name="roles")
    @XmlElement(name="role")
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (roles != null ? !roles.equals(user.roles) : user.roles != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}
