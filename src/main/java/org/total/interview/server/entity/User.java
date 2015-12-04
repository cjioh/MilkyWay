package org.total.interview.server.entity;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by pavlo.fandych on 12/3/2015.
 */

@Entity
@Table(
        name = "User",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userId"),
                @UniqueConstraint(columnNames = "userName")
        }
)
public class User {

    private Integer userId;
    private String userName;
    private Set<Role> roles;

    public User() {

    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(Integer userId, String userName, Set<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.roles = roles;
    }

    public User(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.roles = user.getRoles();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", unique = true, nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "userName", unique = true, nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
