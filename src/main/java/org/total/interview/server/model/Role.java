package org.total.interview.server.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pavlo.fandych on 12/3/2015.
 */

@Entity
@Table(
        name = "Role",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "roleId"),
                @UniqueConstraint(columnNames = "roleTitle")
        }
)
public class Role implements Serializable {

    private long roleId;
    private String roleTitle;
    private Set<User> users;

    public Role() {

    }

    public Role(String roleTitle) {
        this.roleTitle = roleTitle;
        this.users = new HashSet<User>();
    }

    public Role(long roleId, String roleTitle, Set<User> users) {
        this.roleId = roleId;
        this.roleTitle = roleTitle;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleId", unique = true, nullable = false)
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Column(name = "roleTitle", unique = true, nullable = false)
    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles", cascade = CascadeType.ALL)
    public Set<User> getUsers() {
        if (this.users == null) {
            this.users = new HashSet<User>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleTitle='" + roleTitle + '\'' +
                '}';
    }

}
